package net.teengamingnights.assemblymod.sql.datatypes.impl.number;

import net.teengamingnights.assemblymod.sql.datatypes.Datatype;
import org.apache.commons.lang.math.IntRange;
import org.apache.commons.lang.math.LongRange;

public class BigInt extends Datatype<Long> {

    private int digits;

    public BigInt(int digits) {
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
    public Class<Long> getJavaType() {
        return long.class;
    }

    @Override
    public boolean matchesConstraints(Long toTest) {
        return new LongRange(-9223372036854775808L, 9223372036854775807L).containsLong(toTest) &&
                toTest.toString().replace("-", "").length() <= digits;
    }
}
