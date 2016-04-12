package ch.sama.sql.dbo.result.obj;

import ch.sama.sql.jpa.Column;
import ch.sama.sql.jpa.Entity;

@Entity
public class TestObject {
    @Column(name = "sValue")
    private String stringVal;

    @Column(name = "iValue")
    private int intVal;

    @Column(name = "dValue")
    private double doubleVal;

    @Column(name = "bValue")
    private boolean boolVal;

    public void setStringVal(String s) {
        stringVal = s;
    }

    public void setIntVal(int i) {
        intVal = i;
    }

    public void setDoubleVal(double f) {
        doubleVal = f;
    }

    public void setBoolVal(boolean b) {
        boolVal = b;
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

    public boolean getBoolVal() {
        return boolVal;
    }
}
