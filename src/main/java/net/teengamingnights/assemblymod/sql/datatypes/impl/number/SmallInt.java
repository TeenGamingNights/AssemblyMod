package net.teengamingnights.assemblymod.sql.datatypes.impl.number;

import net.teengamingnights.assemblymod.sql.datatypes.Datatype;
import org.apache.commons.lang.math.IntRange;

public class SmallInt extends Datatype<Short> {

    private int digits;

    public SmallInt(int digits) {
        this.digits = digits;
    }

    @Override
    public String getName() {
        return "SMALLINT";
    }

    @Override
    public boolean takesParameters() {
        return true;
    }

    @Override
    public Class<Short> getJavaType() {
        return short.class;
    }

    @Override
    public boolean matchesConstraints(Short toTest) {
        return new IntRange(-32768, 32767).containsNumber(toTest) &&
                toTest.toString().replace("-", "").length() <= digits;
    }
}
