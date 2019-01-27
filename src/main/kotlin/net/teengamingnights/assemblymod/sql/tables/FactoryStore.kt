package net.teengamingnights.assemblymod.sql.tables

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.teengamingnights.assemblymod.factory.IFactory
import net.teengamingnights.assemblymod.sql.DataStore
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.json.JSONObject
import java.util.*

object FactoryStore : DataStore {

    private object Store : Table("factories") {
        val id = uuid("FACTORYID").primaryKey().uniqueIndex()
        val center = text("CENTER")
        val type = integer("TYPEID")
        val health = double("HEALTH")
        val healthLossMultiplier = double("HEALTHLOSSMULTIPLIER")
    }

    override val instance: Table
        get() = Store

    fun createFactory(factory: IFactory) {
        GlobalScope.launch {
            transaction {
                Store.insert {
                    it[Store.id] = factory.id
                    it[Store.center] = Base64.getEncoder().encodeToString(JSONObject(factory.center.serialize()).toString().toByteArray())
                    it[Store.type] = factory.type.ordinal
                    it[Store.health] = factory.health
                    it[Store.healthLossMultiplier] = factory.healthLossMultiplier
                }
            }
        }
    }
}