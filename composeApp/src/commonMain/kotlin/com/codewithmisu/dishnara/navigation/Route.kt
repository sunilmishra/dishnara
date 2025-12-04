package com.codewithmisu.dishnara.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {

    @Serializable
    object RecipeList : Route, NavKey

    @Serializable
    data class RecipeDetails(var id: Int) : Route, NavKey
}

