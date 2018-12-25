package net.teengamingnights.assemblymod.sql

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.transactions.TransactionManager

//Support upserts
fun <T : Table> T.upsert(
    uniqueColumns: List<Column<*>>,
    body: T.(UpsertStatement<Number>) -> Unit
): UpsertStatement<Number> = UpsertStatement<Number>(this, uniqueColumns).apply {
    body(this)
    execute(TransactionManager.current())
}

class UpsertStatement<Key : Any>(table: Table, val onDupUpdate: List<Column<*>>) : InsertStatement<Key>(table, false) {
    override fun prepareSQL(transaction: Transaction): String {
        val onUpdateSQL = if (onDupUpdate.isNotEmpty()) {
            " ON DUPLICATE KEY UPDATE " + onDupUpdate.joinToString {
                "${transaction.identity(it)}=VALUES(${transaction.identity(
                    it
                )})"
            }
        } else ""
        return super.prepareSQL(transaction) + onUpdateSQL
    }
}

infix fun<T> Query.get(column: Column<T>) = map { it[column] }
