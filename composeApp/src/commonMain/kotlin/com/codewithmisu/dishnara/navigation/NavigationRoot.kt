package com.codewithmisu.dishnara.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.codewithmisu.dishnara.recipe.RecipeListScreen
import com.codewithmisu.dishnara.recipe.details.RecipeDetailScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun NavigationRoot() {
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Route.RecipeList::class, Route.RecipeList.serializer())
                    subclass(Route.RecipeDetails::class, Route.RecipeDetails.serializer())
                }
            }
        },
        Route.RecipeList
    )
    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
        ),
        entryProvider = { key ->
            when (key) {
                is Route.RecipeList -> {
                    NavEntry(key) {
                        RecipeListScreen(
                            onItemClick = {
                                backStack.add(Route.RecipeDetails(it))
                            }
                        )
                    }
                }

                is Route.RecipeDetails -> {
                    NavEntry(key) {
                        RecipeDetailScreen(
                            id = key.id,
                            onBack = {
                                backStack.remove(it)
                            }
                        )
                    }
                }

                else -> error("Unknown NavKey: $key")
            }
        }
    )
}