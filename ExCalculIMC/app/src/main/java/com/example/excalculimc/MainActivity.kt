package com.example.excalculimc

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excalculimc.ui.theme.ExCalculIMCTheme
import kotlin.math.pow
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExCalculIMCTheme {
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
    var nomUsuari by rememberSaveable { mutableStateOf("") }
    var anyNaixement by rememberSaveable { mutableStateOf("") }
    var pes by rememberSaveable { mutableStateOf("") }
    var alcada by rememberSaveable { mutableStateOf("") }
    var botoIMC by rememberSaveable { mutableStateOf(false) }
    var edat by rememberSaveable { mutableStateOf(0) }
    var imc by rememberSaveable { mutableStateOf(0.0) }
    var imcVeredict by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "IMC calculator", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        TextField(value = nomUsuari, onValueChange = {nomUsuari = it}, label = {Text("User name")})
        TextField(value = anyNaixement, onValueChange = {anyNaixement = it}, label = {Text("Date of Birth")})
        TextField(value = pes, onValueChange = {pes = it}, label = {Text("Weight(Kg)")})
        TextField(value = alcada, onValueChange = {alcada = it}, label = {Text("Height(cm)")})
        Button(onClick = {
            botoIMC = true
            edat = CalcularEdat(anyNaixement.toInt())
            imc = CalcularIMC(pes.toDouble(), alcada.toDouble())
        })
        {
            Text(text = "Calculate IMC")
        }
        if (botoIMC) {
            when {
                imc < 18.5 -> imcVeredict = "Insufficient weight"
                imc in 18.5 .. 24.9 -> imcVeredict = "Normal weight"
                imc in 25.0 .. 50.0 -> imcVeredict = "Overweight"
                imc >= 50.0 -> imcVeredict = "Obesity"
            }
            Text (
                text = "Your age: $edat.",
                fontWeight = FontWeight.Bold,
                color = Color.Green
            )
            Text (
                text = "Your IMC: $imc",
                fontWeight = FontWeight.Bold,
                color = Color.Green
            )
            Text (
                text = imcVeredict,
                fontWeight = FontWeight.Bold,
                color = Color.Green
            )
        }
    }
}

fun CalcularEdat(edat:Int): Int {
    var resultat:Int
    val calendar = Calendar.getInstance()
    val actualYear = calendar.get(Calendar.YEAR)
    resultat = actualYear - edat
    return resultat
}

fun CalcularIMC(pes:Double, alcada:Double): Double {
    var resultat:Double
    resultat = Math.round((pes/(alcada/100).pow(2.0))*100.0)/100.0
    return resultat
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExCalculIMCTheme {
        Body()
    }
}