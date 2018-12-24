package net.teengamingnights.assemblymod.sql.datatypes.impl.number;

import net.teengamingnights.assemblymod.sql.datatypes.Datatype;
import org.apache.commons.lang.math.IntRange;

/*
 * Yes, Double is really the recommended type.
 */
public class Float extends Datatype<Double> {

    private int digits;
    private int decimalPlaces;

    public Float(int digits, int decimalPlaces) {
        this.digits = digits;
        this.decimalPlaces = decimalPlaces;
    }

    @Override
    public String getName() {
        return "FLOAT";
    }

    @Override
    public boolean takesParameters() {
        return true;
    }

    @Override
    public Class<Double> getJavaType() {
        return double.class;
    }

    @Override
    public boolean matchesConstraints(Double toTest) {
        return toTest.toString().replace("-", "").replace(".", "").length() <= digits &&
                toTest.toString().split(".")[1].length() <= decimalPlaces;
    }
}
