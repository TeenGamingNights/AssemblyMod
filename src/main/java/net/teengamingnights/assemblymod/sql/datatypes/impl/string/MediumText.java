package net.teengamingnights.assemblymod.sql.datatypes.impl.string;

import net.teengamingnights.assemblymod.sql.datatypes.Datatype;
import org.apache.commons.lang.math.IntRange;

public class MediumText extends Datatype<String> {

    @Override
    public String getName() {
        return "MEDIUMTEXT";
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
        return new IntRange(0, 16777215).containsInteger(toTest.length());
    }
}
