package com.example.exconstraintlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.exconstraintlayout.ui.theme.ExConstraintLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExConstraintLayoutTheme {
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
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Green)
    )
    Column (
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.padding(20.dp, top = 20.dp)
    ) {
        ConstraintLayout {
            val (logo, boxYellow, boxBlue, mobile) = createRefs()

            Image(modifier = Modifier
                .size(140.dp)
                .constrainAs(logo) {},
                contentDescription = "Logo",
                painter = painterResource(id = R.drawable.logo))

            Spacer(modifier = Modifier.width(20.dp))

            Box(modifier = Modifier
                .background(Color.Yellow)
                .size(height = 30.dp, width = 200.dp)
                .constrainAs(boxYellow) {
                    start.linkTo(logo.end)
                    top.linkTo(logo.top)
                })
            {
                Text(text = "Hello World!", color = Color.Black, textAlign = TextAlign.Center, modifier = Modifier.size(height = 30.dp, width = 200.dp))
            }
            Box(modifier = Modifier
                .background(Color.Blue)
                .size(height = 60.dp, width = 200.dp)
                .constrainAs(boxBlue) {
                    start.linkTo(boxYellow.start)
                    top.linkTo(boxYellow.bottom)
                })
            Box(modifier = Modifier
                .background(Color.Cyan)
                .size(height = 60.dp, width = 330.dp)
                .constrainAs(mobile) {
                    start.linkTo(logo.start)
                    top.linkTo(logo.bottom)
                })
        }
    }


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExConstraintLayoutTheme {
        Body()
    }
}