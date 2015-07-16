package ch.sama.sql.dbo.result.obj;

@Entity
public class TestObject {
    @Column(name = "sValue")
    private String stringVal;

    @Column(name = "iValue")
    private int intVal;

    @Column(name = "dValue")
    private double doubleVal;

    public void setStringVal(String s) {
        stringVal = s;
    }

    public void setIntVal(int i) {
        intVal = i;
    }

    public void setDoubleVal(double f) {
        doubleVal = f;
    }

    public String getStringVal() {
        return stringVal;
    }

    public int getIntVal() {
        return intVal;
    }

    public double getDoubleVal() {
        return doubleVal;
    }
}
