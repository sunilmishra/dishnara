package com.codewithmisu.dishnara

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import com.codewithmisu.dishnara.recipe.RecipeDao
import com.codewithmisu.dishnara.recipe.RecipeEntity

@Database(
    entities = [RecipeEntity::class],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase>

