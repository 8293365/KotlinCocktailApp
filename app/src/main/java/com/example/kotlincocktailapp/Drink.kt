package com.example.kotlincocktailapp

import com.google.gson.annotations.SerializedName
/*
data class DrinkResponse(
    @SerializedName("drinks") val drinks: List<Drink>?
)*/

data class Drink (
    @SerializedName("idDrink") val id: String,
    @SerializedName("strDrink") val name: String,
    @SerializedName("strCategory") val category: String,
    @SerializedName("strAlcoholic") val alcoholic: String,
    @SerializedName("strGlass") val glass: String,
    @SerializedName("strInstructions") val instructions: String,
    @SerializedName("strDrinkThumb") val imageUrl: String,
    @SerializedName("strIngredient1") val Ingredient1: String,
    @SerializedName("strIngredient2") val Ingredient2: String,
    @SerializedName("strIngredient3") val Ingredient3: String,
    @SerializedName("strIngredient4") val Ingredient4: String,
    @SerializedName("strIngredient5") val Ingredient5: String,
)