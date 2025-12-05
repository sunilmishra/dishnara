package com.codewithmisu.dishnara

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.codewithmisu.dishnara.navigation.NavigationRoot
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
//        val appDatabase = getRoomDatabaseBuilder(databaseBuilder)
//        val repository = RecipeRepositoryImp(
//            remoteSource = RecipeRemoteSource(RestService.client),
//            dao = appDatabase.recipeDao()
//        )
        NavigationRoot()
    }
}