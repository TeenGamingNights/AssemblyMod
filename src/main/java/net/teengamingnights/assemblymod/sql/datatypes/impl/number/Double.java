package net.teengamingnights.assemblymod.sql.datatypes.impl.number;

import net.teengamingnights.assemblymod.sql.datatypes.Datatype;

public class Double extends Datatype<java.lang.Double> {

    private int digits;
    private int decimalPlaces;

    public Double(int digits, int decimalPlaces) {
        this.digits = digits;
        this.decimalPlaces = decimalPlaces;
    }

    @Override
    public String getName() {
        return "DOUBLE";
    }

    @Override
    public boolean takesParameters() {
        return true;
    }

    @Override
    public Class<java.lang.Double> getJavaType() {
        return double.class;
    }

    @Override
    public boolean matchesConstraints(java.lang.Double toTest) {
        return toTest.toString().replace("-", "").replace(".", "").length() <= digits &&
                toTest.toString().split(".")[1].length() <= decimalPlaces;
    }
}
