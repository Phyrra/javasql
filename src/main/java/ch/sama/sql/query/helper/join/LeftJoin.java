package ch.sama.sql.query.helper.join;

public class LeftJoin extends JoinTypeDirection {
    public String getString() {
        return "LEFT";
    }

    public OuterJoin outer() {
        return new OuterJoin(this);
    }
}
