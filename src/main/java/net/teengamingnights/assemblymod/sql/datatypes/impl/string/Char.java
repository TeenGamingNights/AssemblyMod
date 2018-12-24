package net.teengamingnights.assemblymod.sql.datatypes.impl.string;

import net.teengamingnights.assemblymod.sql.datatypes.Datatype;

public class Char extends Datatype<String> {

    private int length;

    public Char(int length) {
        if (length > 255 || length < 0) {
            throw new IllegalArgumentException("CHAR datatype must have a size in the range [0-255]");
        }

        this.length = length;
    }

    @Override
    public String getName() {
        return "CHAR";
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
        return toTest.length() == length;
    }
}
