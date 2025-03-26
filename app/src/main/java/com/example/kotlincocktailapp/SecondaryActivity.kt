package com.example.kotlincocktailapp

import androidx.activity.enableEdgeToEdge
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlincocktailapp.ui.theme.KotlinCocktailAppTheme

class SecondaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinCocktailAppTheme {
                //Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                //CocktailApp()
                //}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CocktailSearchApp(){
    //SatNav2()
}
