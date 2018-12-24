package net.teengamingnights.assemblymod.sql.datatypes;

public abstract class Datatype<T> {

    public abstract String getName();

    public abstract boolean takesParameters();

    public abstract Class<T> getJavaType();

    public abstract boolean matchesConstraints(T toTest);
}
