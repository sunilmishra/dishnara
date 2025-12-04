package com.codewithmisu.dishnara.recipe

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(entities: List<RecipeEntity>)

    @Query("SELECT * FROM recipeentity")
    suspend fun getAll(): List<RecipeEntity>

    @Query("SELECT * FROM recipeentity WHERE id = :itemId LIMIT 1")
    suspend fun getRecipe(itemId: Int): RecipeEntity?

    @Delete
    suspend fun deleteRecipe(recipeEntity: RecipeEntity)
}