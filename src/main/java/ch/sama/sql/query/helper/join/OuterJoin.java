package ch.sama.sql.query.helper.join;

public class OuterJoin extends JoinType {
    private JoinTypeDirection direction;

    public OuterJoin(JoinTypeDirection direction) {
        this.direction = direction;
    }

    public String getString() {
        return direction.getString() + " OUTER";
    }
}
