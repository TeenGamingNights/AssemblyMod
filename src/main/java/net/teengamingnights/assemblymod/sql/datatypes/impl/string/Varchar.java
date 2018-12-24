package net.teengamingnights.assemblymod.sql.datatypes.impl.string;

import net.teengamingnights.assemblymod.sql.datatypes.Datatype;
import org.apache.commons.lang.math.IntRange;

public class Varchar extends Datatype<String> {

    private int length;

    public Varchar(int length) {
        if (length > 255 || length < 0) {
            throw new IllegalArgumentException("VARCHAR datatype must have a size in the range [0-255]");
        }

        this.length = length;
    }

    @Override
    public String getName() {
        return "VARCHAR";
    }

    @Override
    public boolean takesParameters() {
        return true;
    }

    @Override
    public Class<String> getJavaType() {
        return String.class;
    }

    @Override
    public boolean matchesConstraints(String toTest) {
        return new IntRange(0, length).containsInteger(toTest.length());
    }
}
