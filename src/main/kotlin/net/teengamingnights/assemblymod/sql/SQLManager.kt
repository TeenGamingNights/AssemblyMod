package net.teengamingnights.assemblymod.sql

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.teengamingnights.assemblymod.config.impl.Config
import net.teengamingnights.assemblymod.sql.tables.FactoryRecipesStore
import net.teengamingnights.assemblymod.sql.tables.FactoryStore
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.IOException
import javax.sql.DataSource

@ExperimentalCoroutinesApi
class SQLManager(val config: Config) : Runnable {

    private lateinit var ds: DataSource

    private val tables = listOf(
            FactoryRecipesStore,
            FactoryStore
    )

    @Throws(IOException::class)
    override fun run() {
        val dsConfig = HikariConfig()

        dsConfig.jdbcUrl = "jdbc:mysql://${config.database.host}:${config.database.port}/${config.database.database}"
        dsConfig.username = config.database.username
        dsConfig.password = config.database.password

        dsConfig.maximumPoolSize = config.database.pool.threads
        dsConfig.addDataSourceProperty("maxIdle", config.database.pool.maxIdle)

        dsConfig.addDataSourceProperty("autoReconnect", true)

        dsConfig.addDataSourceProperty("useJDBCCompliantTimezoneShift", true)
        dsConfig.addDataSourceProperty("serverTimezone", "UTC")
        dsConfig.addDataSourceProperty("useLegacyDatetimeCode", false)

        dsConfig.addDataSourceProperty("cachePrepStmts", true)
        dsConfig.addDataSourceProperty("prepStmtCacheSize", 250)
        dsConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 2048)

        ds = HikariDataSource(dsConfig)

        Database.connect(ds)

        transaction {
            SchemaUtils.createMissingTablesAndColumns(*tables.map(DataStore::instance).toTypedArray())
        }
    }
}