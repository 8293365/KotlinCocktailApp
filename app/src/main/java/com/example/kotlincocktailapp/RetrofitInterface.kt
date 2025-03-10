package com.example.kotlincocktailapp

import retrofit2.Response
import retrofit2.http.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitInterface {
    @GET("/api/json/v1/1/random.php")
    suspend fun getDrink(): List<Drink>
/*
    @POST("Drinks")
    suspend fun addDrink(@Body Drink: Drink)

    @PUT("Drinks/{id}")
    suspend fun updateDrink(@Path("id") id: String, @Body Drink: Drink)

    @DELETE("Drinks/{id}")
    suspend fun deleteDrink(@Path("id") id: String)
*/
    //companion object
}




