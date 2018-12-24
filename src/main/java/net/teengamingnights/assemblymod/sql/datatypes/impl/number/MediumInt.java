package net.teengamingnights.assemblymod.sql.datatypes.impl.number;

import net.teengamingnights.assemblymod.sql.datatypes.Datatype;
import org.apache.commons.lang.math.IntRange;

public class MediumInt extends Datatype<Integer> {

    private int digits;

    public MediumInt(int digits) {
        this.digits = digits;
    }

    @Override
    public String getName() {
        return "MEDIUMINT";
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
        return new IntRange(-8388608, 8388607).containsInteger(toTest) &&
                toTest.toString().replace("-", "").length() <= digits;
    }
}
