package com.codewithmisu.dishnara

import com.codewithmisu.dishnara.recipe.RecipeRemoteSource
import com.codewithmisu.dishnara.recipe.RecipeRepository
import com.codewithmisu.dishnara.recipe.RecipeRepositoryImp
import com.codewithmisu.dishnara.recipe.RecipeViewModel
import com.codewithmisu.dishnara.recipe.details.RecipeDetailViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

/// Platform Module for Room Database
expect fun platformModule(): Module

/// Http Client
private val restClient = HttpClient {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
}

/// RestClient, Database and Repositories
val dataModule = module {
    single {
        restClient
    }

    /// Recipe Repository
    single<RecipeRepository> {
        RecipeRepositoryImp(
            RecipeRemoteSource(get()),
            get<AppDatabase>().recipeDao()
        )
    }
}

/// View Model
val viewModelModule = module {
    viewModel { RecipeViewModel(get<RecipeRepository>()) }
    viewModel { RecipeDetailViewModel(get<RecipeRepository>()) }
}

fun initKoin(config: KoinAppDeclaration? = null) =
    startKoin {
        config?.invoke(this)
        modules(
            platformModule(),
            dataModule,
            viewModelModule
        )
    }