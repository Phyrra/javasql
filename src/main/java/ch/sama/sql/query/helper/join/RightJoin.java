package ch.sama.sql.query.helper.join;

public class RightJoin extends JoinTypeDirection {
    public String getString() {
        return "RIGHT";
    }

    public OuterJoin outer() {
        return new OuterJoin(this);
    }
}
