package net.teengamingnights.assemblymod.sql.datatypes.impl.string;

import net.teengamingnights.assemblymod.sql.datatypes.Datatype;
import org.apache.commons.lang.math.IntRange;
import org.apache.commons.lang.math.LongRange;

public class LongText extends Datatype<String> {

    @Override
    public String getName() {
        return "LONGTEXT";
    }

    @Override
    public boolean takesParameters() {
        return false;
    }

    @Override
    public Class<String> getJavaType() {
        return String.class;
    }

    @Override
    public boolean matchesConstraints(String toTest) {
        return new LongRange(0, 4294967295L).containsInteger(toTest.length());
    }
}
