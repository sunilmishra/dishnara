package com.codewithmisu.dishnara.recipe.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithmisu.dishnara.recipe.RecipeEntity
import com.codewithmisu.dishnara.recipe.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class RecipeDetailUiState(
    val recipe: RecipeEntity?
)

class RecipeDetailViewModel(val repository: RecipeRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(
        RecipeDetailUiState(recipe = null)
    )
    val uiState = _uiState.asStateFlow()

    fun loadRecipeDetail(id: Int) {
        viewModelScope.launch {
            val recipe = repository.getRecipe(id)
            println("---- Recipe Details: $recipe")
            _uiState.emit(RecipeDetailUiState(recipe = recipe))
        }
    }
}