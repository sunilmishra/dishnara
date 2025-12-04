package com.codewithmisu.dishnara

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.room.RoomDatabase
import com.codewithmisu.dishnara.navigation.NavigationRoot
import com.codewithmisu.dishnara.recipe.RecipeRemoteSource
import com.codewithmisu.dishnara.recipe.RecipeRepositoryImp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(databaseBuilder: RoomDatabase.Builder<AppDatabase>) {
    MaterialTheme {
        val appDatabase = getRoomDatabaseBuilder(databaseBuilder)
        val repository = RecipeRepositoryImp(
            remoteSource = RecipeRemoteSource(RestService.client),
            dao = appDatabase.recipeDao()
        )
        NavigationRoot(repository)
    }
}