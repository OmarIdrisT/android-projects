package com.example.exdiceroller

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exdiceroller.ui.theme.ExDiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExDiceRollerTheme {
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

@Composable
fun Body() {
    var dice1 by rememberSaveable { mutableStateOf(1) }
    var dice2 by rememberSaveable { mutableStateOf(1) }
    var diceRoll1 = when (dice1) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    var diceRoll2 = when (dice2) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Image(
        painter = painterResource(id = R.drawable.tapestry),
        modifier = Modifier.fillMaxSize(),
        contentDescription = "Fons pantalla",
        contentScale = ContentScale.FillBounds
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.title),
            contentDescription = "Títol",
            modifier = Modifier
                .size(width = 300.dp, height = 300.dp)
                .padding(top = 50.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {dice1 = (1 .. 6).random(); dice2 = (1 .. 6).random()},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            ),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .size(width = 300.dp, height = 70.dp)
                .padding(top = 10.dp)
        ) {
            Text(
                text = "ROLL THE DICE",
                fontSize = 18.sp,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(80.dp))

        Row {
            Image(
                painter = painterResource(id = diceRoll1),
                contentDescription = "DiceA",
                modifier = Modifier
                    .weight(1f)
                    .clickable { dice1 = (1..6).random() }
            )
            Image(
                painter = painterResource(id = diceRoll2),
                contentDescription = "DiceB",
                modifier = Modifier
                    .weight(1f)
                    .clickable { dice2 = (1..6).random() }
            )
        }
        if (dice1 == 6 && dice2 == 6) {
            Toast.makeText(LocalContext.current,"JACKPOT!", Toast.LENGTH_SHORT).show()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ExDiceRollerTheme {
        Body()
    }
}