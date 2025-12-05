package com.codewithmisu.dishnara.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class RecipeUiState(
    var loading: Boolean = false,
    var errorMessage: String? = null,
    var recipes: List<RecipeEntity> = emptyList(),

    /// Pull Down to Refresh
    var forceRefresh: Boolean = false,

    /// Infinite Scrolling by Loading more
    var loadMore: Boolean = false
)

class RecipeViewModel(val repository: RecipeRepository) : ViewModel() {

    private var _uiState = MutableStateFlow(RecipeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadRecipes()
    }

    /// Load Recipes
    fun loadRecipes(forceRefresh: Boolean = false) {
        viewModelScope.launch {
            try {
                if (forceRefresh) {
                    _uiState.emit(uiState.value.copy(forceRefresh = true))
                } else {
                    _uiState.emit(RecipeUiState(loading = true))
                }
                val entities = repository.getAll(forceRefresh)

                _uiState.emit(
                    RecipeUiState(
                        loading = false,
                        forceRefresh = false,
                        loadMore = false,
                        recipes = entities
                    )
                )
            } catch (e: Exception) {
                _uiState.emit(
                    RecipeUiState(
                        loading = false,
                        forceRefresh = false,
                        loadMore = false,
                        errorMessage = e.toString()
                    )
                )
            }
        }
    }

    /// Loading more on Scrolling
    fun loadMore(skip: Int) {
        viewModelScope.launch {
            _uiState.emit(
                _uiState.value.copy(
                    loadMore = true
                )
            )
            delay(500)
            val entities = repository.getAll(skip = skip)
            _uiState.emit(
                _uiState.value.copy(
                    loadMore = false,
                    recipes = entities
                )
            )
        }
    }
}