package com.example.kotlincocktailapp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext







@Composable
fun CocktailSatNav() {



    val apiService = RetrofitInstance.api
    val coroutineScope = rememberCoroutineScope()

    var drink by remember { mutableStateOf<Drink?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    val ingredientsList = getIngredients(drink)//INGREDIENT VALUE

    suspend fun fetchDrink() {
        isLoading = true
        try {
            val response = withContext(Dispatchers.IO) {
                apiService.getDrink()
            }
            drink = response.drinks?.firstOrNull() // Get first drink from API response
        } catch (e: Exception) {
            Log.e("API_ERROR", "Error fetching drink: ${e.message}")
        }
        isLoading = false
    }

    // UI Layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFDE68A))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Load Image from API
        AsyncImage(
            model = drink?.imageUrl,  // Online image from API
            contentDescription = drink?.name,
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Display Drink Name
        Text(
            text = drink?.name ?: "Click the button to fetch a drink!",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Display Drink Instructions
        Text(
            text = (drink?.instructions)?: "",
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 8.dp)
        )


        Text(
            text = if (ingredientsList.isNotEmpty()) {
                "Ingredients: " + ingredientsList.joinToString(", ")
            } else { ""/*"No ingredients available" */},
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 8.dp)
        )


        Spacer(modifier = Modifier.height(20.dp))

        // Fetch Drink Button
        Button(
            onClick = { coroutineScope.launch { fetchDrink() } },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107))
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp))
            } else {
                Text(text = "Get a Drink!", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
        Button(
            onClick = { coroutineScope.launch { /*searchDrinks*/ } },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107))
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp))
            } else {
                Text(text = "Search Drinks", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

fun getIngredients(drink: Drink?): List<String> {
    return listOfNotNull<String>(
        drink?.ingredient1?.takeIf { it.isNotBlank() },
        drink?.ingredient2?.takeIf { it.isNotBlank() },
        drink?.ingredient3?.takeIf { it.isNotBlank() },
        drink?.ingredient4?.takeIf { it.isNotBlank() },
        drink?.ingredient5?.takeIf { it.isNotBlank() }
    )
}


/*
fun ingredientsChecker(): String
{
    var str = ""
    for (i in 0..4){

        drink.ingredient1
    }
    iterator()


    return str
}*/