package ch.sama.sql.dialect.tsql;

import ch.sama.sql.dbo.Field;
import ch.sama.sql.dbo.IType;
import ch.sama.sql.dbo.Table;
import ch.sama.sql.dialect.tsql.type.TYPE;
import ch.sama.sql.query.base.IQueryRenderer;
import ch.sama.sql.query.exception.BadParameterException;
import ch.sama.sql.query.exception.BadSqlException;
import ch.sama.sql.query.helper.Value;
import ch.sama.sql.query.helper.pattern.Dates;
import ch.sama.sql.query.helper.pattern.Numerics;

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
            
            MatchFields matchFields = getParent();
            MergeValues mergeValues = matchFields.getParent();
            MergeQuery mergeQuery = mergeValues.getParent();
            
            Table table = mergeQuery.getTable();
            List<List<Pair>> values = mergeValues.getValues();
            List<Pair> fields = evalBestMatch(values);
            List<String> matchers = matchFields.getFields();
            
            builder.append("DECLARE @table TABLE (\n");
            
            builder.append(
                    fields.stream()
                            .map(p -> {
                                Field field = p.getField();
                                String name = field.getName();
                                IType type = field.getDataType();

                                return name + " " + type.getString();
                            })
                            .collect(Collectors.joining(",\n"))
            );
            
            builder.append("\n);\n");
            builder.append("INSERT INTO @table\n");
            
            builder.append(
                    values.stream()
                            .map(r ->
                                    "SELECT " +
                                    r.stream()
                                            .map(p -> p.getValue().getValue())
                                            .collect(Collectors.joining(", "))
                            )
                            .collect(Collectors.joining(" UNION ALL\n"))
            );
            
            builder.append(";\n");
            builder.append("MERGE INTO ");
            builder.append(renderer.render(table));
            builder.append(" AS [old]\n");
            builder.append("USING @table AS [new] ON (\n");
            
            builder.append(
                    matchers.stream()
                            .map(f -> "[old].[" + f + "] = [new].[" + f + "]")
                            .collect(Collectors.joining(" AND "))
            );
            
            builder.append("\n)\n");
            builder.append("WHEN MATCHED THEN\n");
            builder.append("UPDATE SET ");
            
            builder.append(
                    fields.stream()
                            .filter(p -> !omitField(p.getField().getName()))
                            .map(p -> {
                                String name = p.getField().getName();

                                return "[" + name + "] = [new].[" + name + "]";
                            })
                            .collect(Collectors.joining(", "))
            );
            
            builder.append("\n");
            builder.append("WHEN NOT MATCHED BY TARGET THEN\n");
            builder.append("INSERT (");

            builder.append(
                    fields.stream()
                            .filter(p -> !omitField(p.getField().getName()))
                            .map(p -> "[" + p.getField().getName() + "]")
                            .collect(Collectors.joining(", "))
            );

            builder.append(") VALUES (");

            builder.append(
                    // TODO
                    // I think it makes sense to repeat the matcher fields here
                    //  case: merge (ROW_INT) into table match on (ROW_INT)
                    //  the setter would be empty otherwise (-> bad query)

                    fields.stream()
                            .filter(p -> !omitField(p.getField().getName()))
                            .map(p -> "[new].[" + p.getField().getName() + "]")
                            .collect(Collectors.joining(", "))
            );
            
            builder.append(");");
            
            return builder.toString();
        }
    }

    private static final TSqlValueFactory valueFactory = new TSqlValueFactory();
    
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

    public Pair value(Field f, Object o) {
        if (o == null) {
            return new Pair(f, TSqlValueFactory.NULL);
        }

        IType type = f.getDataType();
        String s = o.toString();

        if (TYPE.isEqualType(type, TYPE.CHAR_TYPE) || TYPE.isEqualType(type, TYPE.VARCHAR_TYPE) || TYPE.isEqualType(type, TYPE.NVARCHAR_TYPE) || TYPE.isEqualType(type, TYPE.TEXT_TYPE)) {
            return new Pair(f, valueFactory.string(s));
        }

        if (TYPE.isEqualType(type, TYPE.INT_TYPE)) {
            if (Numerics.isInteger(s)) {
                return new Pair(f, valueFactory.numeric(Integer.parseInt(s)));
            } else if (Numerics.isNumericalFloat(s)) {
                return new Pair(f, valueFactory.numeric((int) Double.parseDouble(s)));
            } else {
                throw new BadSqlException("Expected Int, got: " + s + " (" + o.getClass().getSimpleName() + ")");
            }
        }

        if (TYPE.isEqualType(type, TYPE.FLOAT_TYPE)) {
            if (Numerics.isInteger(s)) {
                return new Pair(f, valueFactory.numeric(Integer.parseInt(s)));
            } else if (Numerics.isNumericalFloat(s)) {
                return new Pair(f, valueFactory.numeric(Double.parseDouble(s)));
            } else {
                throw new BadSqlException("Expected Double, got: " + s + " (" + o.getClass().getSimpleName() + ")");
            }
        }

        if (TYPE.isEqualType(type, TYPE.BIT_TYPE)) {
            if (s.equals("0") || s.equals("0.0")) {
                return new Pair(f, valueFactory.bool(false));
            } else if (s.equals("1") || s.equals("1.0")) {
                return new Pair(f, valueFactory.bool(true));
            } else if (s.matches("(?i)true|false")) {
                return new Pair(f, valueFactory.bool(Boolean.parseBoolean(s)));
            } else {
                throw new BadSqlException("Expected Boolean, got: " + s + " (" + o.getClass().getSimpleName() + ")");
            }
        }

        if (TYPE.isEqualType(type, TYPE.DATETIME_TYPE)) {
            if (Dates.isKnownDate(s)) {
                return datePair(f, s);
            } else {
                throw new BadSqlException("Expected Date, got: " + s + " (" + o.getClass().getSimpleName() + ")");
            }
        }

        Pair pair = value(f.getName(), o);
        return new Pair(f, pair.getValue());
    }

    private Pair datePair(Field f, String s) {
        return new Pair(
                f,
                valueFactory.function(
                        "CONVERT",
                        valueFactory.type(TYPE.DATETIME_TYPE),
                        valueFactory.string(Dates.toIsoDateTime(s)),
                        valueFactory.numeric(21)
                )
        );
    }

    /**
     * Do you like guessing? 
     * @param field with type to be guessed
     * @param o to be guessed
     * @return field-value pair
     */
    public Pair value(String field, Object o) {
        Field f = new Field(field);
        
        if (o == null) {
            f.setDataType(TYPE.NO_TYPE);
            return new Pair(f, TSqlValueFactory.NULL);
        }

        if (o instanceof Boolean) {
            f.setDataType(TYPE.BIT_TYPE);
            
            return new Pair(f, valueFactory.bool((boolean) o));
        }
        
        if (o instanceof Integer || o instanceof Short || o instanceof Long) {
            f.setDataType(TYPE.INT_TYPE);

            if (o instanceof Integer) {
                return new Pair(f, valueFactory.numeric((int) o));
            } else if (o instanceof Short) {
                return new Pair(f, valueFactory.numeric((int) ((short) o)));
            } else {
                return new Pair(f, valueFactory.numeric((int) ((long) o)));
            }
        }
        
        if (o instanceof Double || o instanceof Float) {
            f.setDataType(TYPE.FLOAT_TYPE);
            
            if (o instanceof Double) {
                return new Pair(f, valueFactory.numeric((double) o));
            } else {
                return new Pair(f, valueFactory.numeric((float) o));
            }
        }
        
        if (o instanceof Date) {
            f.setDataType(TYPE.DATETIME_TYPE);
            return new Pair(f, valueFactory.date((Date) o));
        }
        
        if (o instanceof String) {
            String s = (String) o;
            
            if (s.length() == 0 || s.toLowerCase().equals("null")) {
                f.setDataType(TYPE.NO_TYPE);
                return new Pair(f, TSqlValueFactory.NULL);
            }
            
            if (Numerics.isInteger(s)) {
                f.setDataType(TYPE.INT_TYPE);
                return new Pair(f, valueFactory.numeric(Integer.parseInt(s)));
            }
            
            if (Numerics.isProgrammaticalFloat(s)) {
                f.setDataType(TYPE.FLOAT_TYPE);
                return new Pair(f, valueFactory.numeric(Double.parseDouble(s)));
            }
            
            if (Dates.isKnownDate(s)) {
                f.setDataType(TYPE.DATETIME_TYPE);
                return datePair(f, s);
            }
            
            f.setDataType(TYPE.VARCHAR_MAX_TYPE);
            return new Pair(f, valueFactory.string(s));
        }
        
        throw new BadParameterException("Cannot guess {" + o.getClass().getName() + ": " + o.toString() + "}");
    }
}
