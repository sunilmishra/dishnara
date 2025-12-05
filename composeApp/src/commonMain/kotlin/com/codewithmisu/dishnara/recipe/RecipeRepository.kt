package com.codewithmisu.dishnara.recipe

interface RecipeRepository {
    suspend fun getAll(
        forceRefresh: Boolean = false,
        skip: Int = 0,
        limit: Int = 30,
        total: Int = 50
    ): List<RecipeEntity>

    suspend fun getRecipe(id: Int): RecipeEntity?
}

class RecipeRepositoryImp(
    val remoteSource: RecipeRemoteSource, val dao: RecipeDao
) : RecipeRepository {

    private suspend fun fetchAndSaveRecipes(skip: Int, limit: Int) {
        val recipeDTO = remoteSource.fetchRecipes(skip = skip, limit = limit)
        val entities = mutableListOf<RecipeEntity>()
        for (recipe in recipeDTO.recipes) {
            val entity = recipe.toEntity()
            entities.add(entity)
        }
        dao.insertRecipes(entities)
        println("Recipes Saved: ${entities.size}")
    }

    override suspend fun getAll(
        forceRefresh: Boolean,
        skip: Int,
        limit: Int,
        total: Int
    ): List<RecipeEntity> {
        var entities = dao.getAll()
        if (entities.size < total || forceRefresh) {
            fetchAndSaveRecipes(skip, limit)
            entities = dao.getAll()
        }
        println("Recipes GetAll: ${entities.size}")
        return entities
    }

    override suspend fun getRecipe(id: Int): RecipeEntity? {
        return dao.getRecipe(id)
    }
}