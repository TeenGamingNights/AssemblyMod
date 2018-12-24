package net.teengamingnights.assemblymod.sql.datatypes.impl.number;

import net.teengamingnights.assemblymod.sql.datatypes.Datatype;

import java.math.BigDecimal;

/*
 * Yes, Double is really the recommended type.
 */
public class Decimal extends Datatype<BigDecimal> {

    private int digits;
    private int decimalPlaces;

    public Decimal(int digits, int decimalPlaces) {
        this.digits = digits;
        this.decimalPlaces = decimalPlaces;
    }

    @Override
    public String getName() {
        return "DECIMAL";
    }

    @Override
    public boolean takesParameters() {
        return true;
    }

    @Override
    public Class<BigDecimal> getJavaType() {
        return BigDecimal.class;
    }

    @Override
    public boolean matchesConstraints(BigDecimal toTest) {
        return toTest.toString().replace("-", "").replace(".", "").length() <= digits &&
                toTest.toString().split(".")[1].length() <= decimalPlaces;
    }
}
