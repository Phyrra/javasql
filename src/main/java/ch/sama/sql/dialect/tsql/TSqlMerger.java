package ch.sama.sql.dialect.tsql;

import ch.sama.sql.csv.CSVRow;
import ch.sama.sql.csv.CSVSet;
import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.IType;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.exception.BadParameterException;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.dialect.tsql.type.TYPE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TSqlMerger {
    public static class Pair {
        private Field f;
        private Value v;
        
        Pair(Field f, Value v) {
            if (f.getDataType() == null) {
                throw new BadParameterException("Field [" + f.getName() + "] has no type");
            }
            
            this.f = f;
            this.v = v;
        }
        
        public Field getField() {
            return f;
        }
        
        public Value getValue() {
            return v;
        }
    }
    
    public static class MergeQuery {
        private Table table;
        
        MergeQuery(Table table) {
            this.table = table;
        }
        
        Table getTable() {
            return table;
        }
        
        @SafeVarargs
        public final MergeValues values(List<Pair>... values) {
            List<List<Pair>> list = new ArrayList<List<Pair>>();
            Collections.addAll(list, values);
            
            return new MergeValues(this, list);
        }

        public MergeValues values(List<List<Pair>> values) {
            List<List<Pair>> list = values.stream().collect(Collectors.toList());

            return new MergeValues(this, list);
        }

        public MergeValues values(CSVSet csv) {
            if (!csv.hasTitle()) {
                throw new BadParameterException("CSV has no Title row");
            }

            TSqlMerger guesser = new TSqlMerger();
            List<List<Pair>> list = new ArrayList<List<Pair>>();

            List<String> fieldNames = csv.getTitle().toList();

            for (CSVRow row : csv) {
                List<Pair> pairs = new ArrayList<Pair>();

                for (int i = 0; i < row.size(); ++i) { // Empty String will be turned into NULL
                    String obj = row.get(i);
                    pairs.add(guesser.value(fieldNames.get(i), obj.length() == 0 ? null : obj));
                }

                list.add(pairs);
            }

            return new MergeValues(this, list);
        }

        // TODO: Function to provide data types for CSV?
    }

    public static class MergeValues {
        private List<List<Pair>> values;
        private MergeQuery parent;
        
        MergeValues(MergeQuery parent, List<List<Pair>> values) {
            if (values.size() == 0) {
                throw new BadParameterException("No values provided");
            }
            
            int length = values.get(0).size();
            for (List<Pair> row : values) {
                if (row.size() != length) {
                    throw new BadParameterException("All rows must have same size");
                }
            }

            this.parent = parent;
            this.values = values;
        }
        
        MergeQuery getParent() {
            return parent;
        }

        List<List<Pair>> getValues() {
            return values;
        }
        
        public final MatchFields matching(Field... fields) {
            List<String> list = new ArrayList<String>();
            for (Field field : fields) {
                list.add(field.getName());
            }
            
            return new MatchFields(this, list);
        }
        
        public final MatchFields matching(String... fields) {
            List<String> list = new ArrayList<String>();
            Collections.addAll(list, fields);
            
            return new MatchFields(this, list);
        }
    }

    public static class MatchFields {
        private List<String> fields;
        private MergeValues parent;
        
        MatchFields(MergeValues parent, List<String> fields) {
            // Not possible, since the matching methods would be ambiguous
            if (fields.size() == 0) {
                throw new BadParameterException("Must match at least one field");
            }
            
            this.parent = parent;
            this.fields = fields;
        }
        
        MergeValues getParent() {
            return parent;
        }

        List<String> getFields() {
            return fields;
        }

        public final OmitFields omit() {
            return new OmitFields(this, new ArrayList<String>());
        }

        public final OmitFields omit(String... fields) {
            List<String> list = new ArrayList<String>();
            Collections.addAll(list, fields);
            
            return new OmitFields(this, list);
        }

        public final OmitFields omit(Field... fields) {
            List<String> list = new ArrayList<String>();
            for (Field field : fields) {
                list.add(field.getName());
            }

            return new OmitFields(this, list);
        }
    }

    public static class OmitFields {
        private IQueryRenderer renderer;
        private List<String> fields;
        private MatchFields parent;
        
        OmitFields(MatchFields parent, List<String> fields) {
            if (fields.size() == parent.getParent().getValues().get(0).size()) {
                throw new BadParameterException("Cannot omit everything");
            }

            this.parent = parent;
            this.fields = fields;
            this.renderer = new TSqlQueryRenderer();
        }

        MatchFields getParent() {
            return parent;
        }
        
        private boolean omitField(String field) {
            return fields.contains(field);
        }

        private List<Pair> evalBestMatch(List<List<Pair>> values) {
            List<Pair> result = new ArrayList<Pair>();

            int maxI = values.size();
            int maxJ = values.get(0).size();
            
            for (int j = 0; j < maxJ; ++j) {
                Pair p = values.get(0).get(j);
                for (int i = 0; i < maxI - 1 && TYPE.isEqualType(TYPE.NO_TYPE, p.getField().getDataType()); ++i, p = values.get(i).get(j)) { } // omg..
                
                if (TYPE.isEqualType(TYPE.NO_TYPE, p.getField().getDataType())) {
                    p.getField().setDataType(TYPE.BIT_TYPE); // None found, fallback to bit
                }
                
                result.add(p);
            }
            
            return result;
        }
        
        public String getSql() {
            StringBuilder builder = new StringBuilder();
            String prefix;
            
            MatchFields matchFields = getParent();
            MergeValues mergeValues = matchFields.getParent();
            MergeQuery mergeQuery = mergeValues.getParent();
            
            Table table = mergeQuery.getTable();
            List<List<Pair>> values = mergeValues.getValues();
            List<Pair> fields = evalBestMatch(values);
            List<String> matchers = matchFields.getFields();
            
            builder.append("DECLARE @table TABLE (\n");
            
            prefix = "";
            for (Pair pair : fields) {
                Field field = pair.getField();
                String name = field.getName();
                IType type = field.getDataType();
                
                builder.append(prefix);
                builder.append(name);
                builder.append(" ");
                builder.append(type.getString());
                
                prefix = ",\n";
            }
            
            builder.append("\n);\n");
            builder.append("INSERT INTO @table\n");
            
            prefix = "";
            for (List<Pair> row : values) {
                builder.append(prefix);
                
                builder.append("SELECT ");
                
                String listPrefix = "";
                for (Pair pair : row) {
                    Value val = pair.getValue();
                    
                    builder.append(listPrefix);
                    builder.append(val.getValue());
                    
                    listPrefix = ", ";
                }
                
                prefix = " UNION ALL\n";
            }
            
            builder.append(";\n");
            builder.append("MERGE INTO ");
            builder.append(renderer.render(table));
            builder.append(" AS [old]\n");
            builder.append("USING @table AS [new] ON (\n");
            
            prefix = "";
            for (String field : matchers) {
                builder.append(prefix);
                builder.append("[old].[");
                builder.append(field);
                builder.append("] = [new].[");
                builder.append(field);
                builder.append("]");
                
                prefix = " AND ";
            }
            
            builder.append("\n)\n");
            builder.append("WHEN MATCHED THEN\n");
            builder.append("UPDATE SET ");
            
            prefix = "";
            for (Pair pair : fields) {
                String name = pair.getField().getName();
                
                if (!omitField(name)) {
                    builder.append(prefix);
                    builder.append("[");
                    builder.append(name);
                    builder.append("] = [new].[");
                    builder.append(name);
                    builder.append("]");

                    prefix = ", ";
                }
            }
            
            builder.append("\n");
            builder.append("WHEN NOT MATCHED BY TARGET THEN\n");
            builder.append("INSERT (");

            prefix = "";
            for (Pair pair : fields) {
                String name = pair.getField().getName();

                if (!omitField(name)) {
                    builder.append(prefix);
                    builder.append("[");
                    builder.append(name);
                    builder.append("]");

                    prefix = ", ";
                }
            }

            builder.append(") VALUES (");

            prefix = "";
            for (Pair pair : fields) {
                String name = pair.getField().getName();

                // TODO
                // I think it makes sense to repeat the matcher fields here
                //  case: merge (ROW_INT) into table match on (ROW_INT)
                //  the setter would be empty otherwise (-> bad query)
                if (!omitField(name)) {
                    builder.append(prefix);
                    builder.append("[new].[");
                    builder.append(name);
                    builder.append("]");

                    prefix = ", ";
                }
            }
            
            builder.append(")\n");
            builder.append("OUTPUT ");

            prefix = "";
            for (Pair pair : fields) {
                String name = pair.getField().getName();
                
                builder.append(prefix);
                builder.append("INSERTED.[");
                builder.append(name);
                builder.append("]");

                prefix = ", ";
            }
            
            builder.append(";");
            
            return builder.toString();
        }
    }

    public static final String NORM_DATE = "^\\d{1,2}\\.\\d{1,2}\\.\\d{4}$";
    public static final String NORM_DATE_TIME = "^\\d{1,2}\\.\\d{1,2}\\.\\d{4}\\s\\d{2}:\\d{2}(:\\d{2})?$";
    public static final String ISO_DATE = "^\\d{4}-\\d{2}-\\d{2}$";
    public static final String ISO_DATE_TIME = "^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}(:\\d{2})?$";
    public static final String INT = "^\\d+$";
    public static final String FLOAT = "^(\\d+\\.\\d*|\\d*\\.\\d+)$";
    
    public TSqlMerger() { }
    
    public MergeQuery merge(Table table) {
        return new MergeQuery(table); 
    }
    
    public MergeQuery merge(String table) {
        return merge(new Table(table));
    }

    public List<Pair> row(Pair... values) {
        List<Pair> row = new ArrayList<Pair>();
        Collections.addAll(row, values);
        
        return row;
    }
    
    public Pair value(Field f, Value v) {
        return new Pair(f, v);
    }

    private String[] getDateParts(String s) {
        String[] parts = s.split("\\.");

        for (int i = 0; i < parts.length; ++i) {
            if (parts[i].length() < 2) {
                parts[i] = "0" + parts[i];
            }
        }

        return parts;
    }

    /**
     * Do you like guessing? 
     * @param field with type to be guessed
     * @param o to be guessed
     * @return field-value pair
     */
    public Pair value(String field, Object o) {
        TSqlValueFactory value = new TSqlQueryBuilder().value();
        Field f = new Field(field);
        
        if (o == null) {
            f.setDataType(TYPE.NO_TYPE);
            return new Pair(f, value.value(Value.VALUE.NULL));
        }

        if (o instanceof Boolean) {
            f.setDataType(TYPE.BIT_TYPE);
            
            return new Pair(f, value.bool((boolean) o));
        }
        
        if (o instanceof Integer || o instanceof Short || o instanceof Long) {
            f.setDataType(TYPE.INT_TYPE);

            if (o instanceof Integer) {
                return new Pair(f, value.numeric((int) o));
            } else if (o instanceof Short) {
                return new Pair(f, value.numeric((int) ((short) o)));
            } else {
                return new Pair(f, value.numeric((int) ((long) o)));
            }
        }
        
        if (o instanceof Double || o instanceof Float) {
            f.setDataType(TYPE.FLOAT_TYPE);
            
            if (o instanceof Double) {
                return new Pair(f, value.numeric((double) o));
            } else {
                return new Pair(f, value.numeric((float) o));
            }
        }
        
        if (o instanceof Date) {
            f.setDataType(TYPE.DATETIME_TYPE);
            return new Pair(f, value.date((Date) o));
        }
        
        if (o instanceof String) {
            String s = (String) o;
            
            if (s.length() == 0 || s.toLowerCase().equals("null")) {
                f.setDataType(TYPE.NO_TYPE);
                return new Pair(f, value.value(Value.VALUE.NULL));
            }
            
            if (s.matches(INT)) {
                f.setDataType(TYPE.INT_TYPE);
                return new Pair(f, value.numeric(Integer.parseInt(s)));
            }
            
            if (s.matches(FLOAT)) {
                f.setDataType(TYPE.FLOAT_TYPE);
                return new Pair(f, value.numeric(Double.parseDouble(s)));
            }
            
            if (s.matches(NORM_DATE)) {
                f.setDataType(TYPE.DATETIME_TYPE);
                
                String[] parts = getDateParts(s);
                
                return new Pair(
                        f,
                        value.function(
                                "CONVERT",
                                value.type(TYPE.DATETIME_TYPE),
                                value.string(parts[2] + "-" + parts[1] + "-" + parts[0] + " 00:00:00"),
                                value.numeric(21)
                        )
                );
            }

            if (s.matches(NORM_DATE_TIME)) {
                f.setDataType(TYPE.DATETIME_TYPE);

                String[] dt = s.split(" ");
                String[] parts = getDateParts(dt[0]);

                return new Pair(
                        f,
                        value.function(
                                "CONVERT",
                                value.type(TYPE.DATETIME_TYPE),
                                value.string(parts[2] + "-" + parts[1] + "-" + parts[0] + " " + dt[1]),
                                value.numeric(21)
                        )
                );
            }
            
            if (s.matches(ISO_DATE)) {
                f.setDataType(TYPE.DATETIME_TYPE);
                
                return new Pair(
                        f,
                        value.function(
                                "CONVERT",
                                value.type(TYPE.DATETIME_TYPE),
                                value.string(s + " 00:00:00"),
                                value.numeric(21)
                        )
                );
            }

            if (s.matches(ISO_DATE_TIME)) {
                f.setDataType(TYPE.DATETIME_TYPE);

                return new Pair(
                        f,
                        value.function(
                                "CONVERT",
                                value.type(TYPE.DATETIME_TYPE),
                                value.string(s),
                                value.numeric(21)
                        )
                );
            }
            
            f.setDataType(TYPE.VARCHAR_MAX_TYPE);
            return new Pair(f, value.string(s));
        }
        
        throw new BadParameterException("Cannot guess {" + o.getClass().getName() + ": " + o.toString() + "}");
    }
}
