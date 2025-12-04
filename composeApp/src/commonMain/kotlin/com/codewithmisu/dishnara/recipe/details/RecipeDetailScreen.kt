package com.codewithmisu.dishnara.recipe.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.codewithmisu.dishnara.recipe.RecipeRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(id: Int, repository: RecipeRepository, onBack: () -> Unit) {

    val viewModel = remember {
        RecipeDetailViewModel(repository)
    }

    LaunchedEffect(Unit) { // 'Unit' ensures the effect runs only once
        viewModel.loadRecipeDetail(id)
    }

    val uiState by viewModel.uiState.collectAsState()
    val recipe = uiState.recipe

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Recipe Details")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBack()
                        },
                        content = {
                            Icon(
                                Icons.Default.ArrowBackIosNew,
                                contentDescription = "Go Back",
                            )
                        }
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (recipe == null) return@Column

            AsyncImage(
                model = recipe.image,
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(
                modifier = Modifier.padding(vertical = 12.dp)
            )
            Text(
                recipe.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(
                modifier = Modifier.padding(vertical = 12.dp)
            )
            Text(
                "Ingredients",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
            recipe.ingredients.map {
                it.split(",", limit = 1).map { item ->
                    Text("- $item")
                }
            }
            Spacer(
                modifier = Modifier.padding(vertical = 12.dp)
            )
            Text(
                "Instructions",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
            recipe.instructions.map {
                Text("- $it")
            }
        }
    }
}

