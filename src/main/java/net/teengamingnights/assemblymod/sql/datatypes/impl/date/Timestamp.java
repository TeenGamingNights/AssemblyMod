package net.teengamingnights.assemblymod.sql.datatypes.impl.date;

import net.teengamingnights.assemblymod.sql.datatypes.Datatype;

public class Timestamp extends Datatype<java.sql.Timestamp> {

    @Override
    public String getName() {
        return "TIMESTAMP";
    }

    @Override
    public boolean takesParameters() {
        return false;
    }

    @Override
    public Class<java.sql.Timestamp> getJavaType() {
        return java.sql.Timestamp.class;
    }

    @Override
    public boolean matchesConstraints(java.sql.Timestamp toTest) {
        return true;
    }
}
