package com.codewithmisu.dishnara.recipe

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class RecipeRemoteSource(val client: HttpClient) {

    suspend fun fetchRecipes(skip: Int = 0, limit: Int = 30): RecipeDTO {
        val url = "https://dummyjson.com/recipes?skip=$skip&limit=$limit"
        val response = client.get(url)
        println("Recipes Fetch Status: ${response.status}")
        return response.body<RecipeDTO>()
    }
}