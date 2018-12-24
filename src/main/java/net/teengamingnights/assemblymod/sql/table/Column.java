package net.teengamingnights.assemblymod.sql.table;

import net.teengamingnights.assemblymod.sql.datatypes.Datatype;

public class Column<T> {

    private final String name;
    private final Datatype<T> datatype;
    private final boolean isPrimaryKey;
    private final boolean isUnique;

    public Column(String name, Datatype<T> datatype) {
        this.name = name;
        this.datatype = datatype;
        this.isPrimaryKey = false;
        this.isUnique = false;
    }

    public Column(String name, Datatype<T> datatype, boolean isPrimaryKey, boolean isUnique) {
        this.name = name;
        this.datatype = datatype;
        this.isPrimaryKey = isPrimaryKey;
        this.isUnique = isUnique;
    }
}
