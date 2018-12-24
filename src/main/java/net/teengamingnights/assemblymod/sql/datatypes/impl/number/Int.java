package net.teengamingnights.assemblymod.sql.datatypes.impl.number;

import net.teengamingnights.assemblymod.sql.datatypes.Datatype;
import org.apache.commons.lang.math.IntRange;

public class Int extends Datatype<Integer> {

    private int digits;

    public Int(int digits) {
        this.digits = digits;
    }

    @Override
    public String getName() {
        return "INT";
    }

    @Override
    public boolean takesParameters() {
        return true;
    }

    @Override
    public Class<Integer> getJavaType() {
        return int.class;
    }

    @Override
    public boolean matchesConstraints(Integer toTest) {
        return new IntRange(-2147483648, 2147483647).containsInteger(toTest) &&
                toTest.toString().replace("-", "").length() <= digits;
    }
}
