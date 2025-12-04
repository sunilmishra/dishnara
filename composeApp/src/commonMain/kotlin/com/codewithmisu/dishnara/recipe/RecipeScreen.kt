package com.codewithmisu.dishnara.recipe

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.codewithmisu.dishnara.components.CustomRatingBar
import com.codewithmisu.dishnara.components.LoadingIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen(repository: RecipeRepository, onItemClick: (Int) -> Unit) {

    val viewModel = viewModel {
        RecipeViewModel(repository)
    }

    val uiState by viewModel.uiState.collectAsState()
    val pullDownRefreshState = rememberPullToRefreshState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Recipes")
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            if (uiState.loading) {
                LoadingIndicator()
            }

            /// Error Message
            if (uiState.errorMessage != null) {
                Text(
                    "Error: ${uiState.errorMessage}",
                    modifier = Modifier.padding(16.dp)
                )
            }

            /// Recipe List with PullDown Refresh
            PullToRefreshBox(
                isRefreshing = uiState.forceRefresh,
                state = pullDownRefreshState,
                onRefresh = {
                    viewModel.loadRecipes(forceRefresh = true)
                },
            ) {
                RecipeList(
                    uiState.recipes,
                    onItemClick = {
                        onItemClick(it)
                    },
                )
            }
        }
    }
}

@Composable
private fun RecipeList(recipes: List<RecipeEntity>, onItemClick: (id: Int) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(recipes) { recipe ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(recipe.id) },
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                ListItem(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    leadingContent = {
                        AsyncImage(
                            model = recipe.image,
                            contentDescription = null,
                            modifier = Modifier
                                .size(72.dp)
                                .clip(RoundedCornerShape(8.dp)),
                        )
                    },
                    headlineContent = {
                        Text(
                            recipe.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1
                        )
                    },
                    supportingContent = {
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Row {
                                Icon(
                                    Icons.Default.AccessAlarm,
                                    contentDescription = "Clock",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    " ${recipe.prepTimeMinutes} mins",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            CustomRatingBar(
                                rating = recipe.rating,
                                modifier = Modifier.height(16.dp)
                            )
                        }
                    },
                    trailingContent = {
                        Icon(
                            Icons.Filled.ChevronRight,
                            contentDescription = "Right Arrow",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                )
            }
        }
    }
}