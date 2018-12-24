package net.teengamingnights.assemblymod.sql.datatypes.impl.date;

import net.teengamingnights.assemblymod.sql.datatypes.Datatype;

public class Time extends Datatype<java.sql.Time> {

    @Override
    public String getName() {
        return "TIME";
    }

    @Override
    public boolean takesParameters() {
        return false;
    }

    @Override
    public Class<java.sql.Time> getJavaType() {
        return java.sql.Time.class;
    }

    @Override
    public boolean matchesConstraints(java.sql.Time toTest) {
        return true;
    }
}
