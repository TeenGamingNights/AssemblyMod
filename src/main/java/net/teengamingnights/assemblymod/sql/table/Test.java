package net.teengamingnights.assemblymod.sql.table;

@TableName(tableName = "mytable")
public class Test extends Table {

    @Override
    public void registerColumns() {
        blob("").primaryKey().unique();
    }
}
