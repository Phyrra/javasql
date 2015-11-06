package ch.sama.sql.query.helper.join;

public abstract class JoinType {
    public static final JoinType INNER = new InnerJoin();
    public static final JoinType CROSS = new CrossJoin();

    public static final JoinTypeDirection LEFT = new LeftJoin();
    public static final JoinTypeDirection RIGHT = new RightJoin();
    public static final JoinTypeDirection FULL = new FullJoin();

    public static final JoinType LEFT_OUTER = LEFT.outer();
    public static final JoinType RIGHT_OUTER = RIGHT.outer();
    public static final JoinType FULL_OUTER = FULL.outer();

    public abstract String getString();
}
