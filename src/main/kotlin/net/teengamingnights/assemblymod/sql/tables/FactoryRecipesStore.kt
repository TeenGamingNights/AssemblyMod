package net.teengamingnights.assemblymod.sql.tables

import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.teengamingnights.assemblymod.factory.Factory
import net.teengamingnights.assemblymod.factory.recipes.Recipe
import net.teengamingnights.assemblymod.factory.recipes.Recipes
import net.teengamingnights.assemblymod.sql.DataStore
import net.teengamingnights.assemblymod.sql.get
import net.teengamingnights.assemblymod.utils.async
import net.teengamingnights.assemblymod.utils.launch
import net.teengamingnights.assemblymod.utils.onComplete
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.function.Consumer

@ExperimentalCoroutinesApi
object FactoryRecipesStore : DataStore {

    private object Store : Table("factoryrecipes") {
        val pairId = integer("ID").primaryKey().uniqueIndex().autoIncrement()
        val factoryId = uuid("FACTORYID")
        val recipeId = integer("RECIPEID")
    }

    override val instance: Table
        get() = Store

    @JvmStatic
    fun getRecipes(factory: Factory, callback: Consumer<List<Recipe>>) {
        async {
            transaction {
                Store.select {
                    Store.factoryId eq factory.id
                } get Store.recipeId
            }
        }.onComplete { ids ->
            val recipes = Recipes.values().map(Recipes::recipe).filter { recipe -> ids.contains(recipe.id) }
            callback.accept(recipes)
        }
    }

    fun clearRecipes(factory: Factory) {
        launch {
            transaction {
                Store.deleteWhere {
                    Store.factoryId eq factory.id
                }
            }
        }
    }

    fun addRecipes(factory: Factory, recipes: List<Recipe>) {
        launch {
            recipes.map(Recipe::getId).forEach { recipeId ->
                transaction {
                    Store.insert {
                        it[Store.factoryId] = factory.id
                        it[Store.recipeId] = recipeId
                    }
                }
            }
        }
    }
}