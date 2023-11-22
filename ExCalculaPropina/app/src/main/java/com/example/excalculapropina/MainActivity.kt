package com.example.excalculapropina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excalculapropina.ui.theme.ExCalculaPropinaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExCalculaPropinaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Body()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Body() {
    var priceMenu by rememberSaveable { mutableStateOf("") }
    var tip by rememberSaveable {mutableStateOf("")}
    var show by rememberSaveable {mutableStateOf(false)}
    var result by rememberSaveable { mutableStateOf(0.0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Tip Calculator", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        TextField(value = priceMenu, onValueChange = {priceMenu = it}, label = {Text("Preu del menú (euros)")}, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        TextField(value = tip, onValueChange = {tip = it}, label = {Text("Propina (percentatge)")}, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Button(onClick = {
            show = true
            result = CalcularPreuFinal(priceMenu, tip)
        }) {
            Text(text = "Calcular")
        }
        if (show) {
            Text (
                text = "$result euros.",
                fontWeight = FontWeight.Light,
                color = Color.Red
            )
        }
    }
}

fun CalcularPreuFinal(priceMenu:String, tip:String): Double {
    var priceMenuDouble = priceMenu.toDouble()
    var tipDouble = tip.toDouble()
    var result = priceMenuDouble + priceMenuDouble * tipDouble/100
    return result
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ExCalculaPropinaTheme {
        Body()
    }
}