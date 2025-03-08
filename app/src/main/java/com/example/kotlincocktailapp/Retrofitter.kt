package com.example.kotlincocktailapp

import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApiService {
    @GET("api/json/v1/1/search.php") // Base URL + this endpoint = Full URL
    suspend fun searchCocktails(@Query("s") query: String): CocktailResponse
}
