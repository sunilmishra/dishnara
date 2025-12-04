package com.codewithmisu.dishnara.recipe

interface RecipeRepository {
    suspend fun getAll(
        forceRefresh: Boolean = false,
        skip: Int = 0,
        limit: Int = 30,
    ): List<RecipeEntity>

    suspend fun getRecipe(id: Int): RecipeEntity?
}

class RecipeRepositoryImp(
    val remoteSource: RecipeRemoteSource, val dao: RecipeDao
) : RecipeRepository {

    var totalCount: Int = 30
    var limit: Int = 30;
    var skip: Int = 0;

    private suspend fun fetchAndSaveRecipes() {
        val recipeDTO = remoteSource.fetchRecipes(skip = skip, limit = limit)
        totalCount = recipeDTO.total
        limit = recipeDTO.limit
        skip = recipeDTO.skip

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
    ): List<RecipeEntity> {
        var entities = dao.getAll()
        if (entities.size <= totalCount || forceRefresh) {
            fetchAndSaveRecipes()
            entities = dao.getAll()
        }
        println("Recipes GetAll: ${entities.size}")
        return entities
    }

    override suspend fun getRecipe(id: Int): RecipeEntity? {
        return dao.getRecipe(id)
    }
}