package ch.sama.sql.query.helper.join;

public class FullJoin extends JoinTypeDirection {
    public String getString() {
        return "FULL";
    }

    public OuterJoin outer() {
        return new OuterJoin(this);
    }
}
