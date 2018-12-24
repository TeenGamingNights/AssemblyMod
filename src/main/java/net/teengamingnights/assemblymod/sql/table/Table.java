package net.teengamingnights.assemblymod.sql.table;

import net.teengamingnights.assemblymod.sql.datatypes.Datatype;
import net.teengamingnights.assemblymod.sql.datatypes.impl.string.Blob;

import java.util.ArrayList;
import java.util.List;

public abstract class Table {

    protected List<Column<String>> stringColumns = new ArrayList<>();

    public abstract void registerColumns();

    public Column<String> blob(String columnName) {
        Column<String> column = new Column<String>(columnName, new Blob()) {};
        stringColumns.add(column);
        return column;
    }

    private<T> Column<T> addColumn(String columnName, Datatype<T> datatype, Class<T> clazz) {
        Column<T> column;
        clazz.getGenericSuperclass()
        if(datatype.getJavaType() == String.class) column = new Column<String>(columnName, datatype);

        return column;
    }

    private Column<String> addStringColumn(Column<String> column) {
        stringColumns.add(column);
        return column;
    }
}
