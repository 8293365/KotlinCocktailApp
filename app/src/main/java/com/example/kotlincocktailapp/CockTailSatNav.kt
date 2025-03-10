package com.example.kotlincocktailapp
package org.RetrofitIstance.*

import androidx.compose.runtime.Composable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.create



@Composable
fun CocktailSatNav() {
    // Variabile per memorizzare la battuta
    var drink by remember { mutableStateOf("Premi il bottone per una Drink!") }

    // Variabile per gestire lo stato di caricamento
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    // un funzione suspend è una funzione sospendibile
    // ovver che potrebbe sospendere la sua esecuzione in un punto specifico
    // permettendo ad altri lavori di essere eseguiti nel frattempo
    suspend fun fetchDrink() {
        isLoading = true

        // sposta l'esecuzione di una coroutine su un thread dedicato
        // utile per ottimizzare l'uso delle risorse di sistema,
        // separando le operazioni di I/O da quelle di CPU-bound (intensive)
        // e gestendo meglio i thread.
        // Dispatchers.Default: CPU Intensive
        // Dispatchers.IO: I/O Bounde
        // Dispatchers.Main: Interazioni con UI
        drink = withContext(Dispatchers.IO) {
            /*try {
                    val response = URL("https://api.chucknorris.io/drink/random").readText()
                    JSONObject(response).getString("value")
                } catch (e: Exception) {
                    "Errore nel caricamento della battuta. Riprova!"
                }*/
            try {
                val DrinkApi = RetrofitInterface.getDrink().create(DrinkApi::class.java)
                // launching a new coroutine
                GlobalScope.launch {
                    val result = DrinkApi.getDrink()
                    if (result != null)
                    // Checking the results
                        Log.d("ayush: ", result.body().toString())
                }
            }catch(e:Exception){
                "Errore nel caricamento della battuta. Riprova!"
            }


        }.toString()///what is thiss???????????????
        isLoading = false
    }

    // Layout principale dell'app
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFDE68A))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Immagine di Chuck Norris
        Image(
            painter = painterResource(),        //id = R.drawable.chucknorris),
            contentDescription = "Chuck Norris",
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Testo che mostra la battuta
        Text(
            text = drink,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Bottone per richiedere una nuova battuta
        Button(
            onClick = {
                // Avvia la coroutine quando si preme il bottone
                coroutineScope.launch { fetchDrink() }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107))
        ) {
            if (isLoading) {
                // Indicatore di caricamento mentre si attende la risposta
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp))
            } else {
                // Testo del bottone
                Text(text = "Dimmi una battuta!", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}