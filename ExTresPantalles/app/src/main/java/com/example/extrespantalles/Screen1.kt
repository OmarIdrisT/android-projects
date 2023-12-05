import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen1(navController: NavController) {
    var nom by remember { mutableStateOf("") }
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    )
    {
        Box(
            modifier = Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .height(50.dp)) {
            Text(text = "MyForm", modifier = Modifier.fillMaxHeight().padding(top = 16.dp))
        }
        Spacer(modifier = Modifier.height(300.dp))
        TextField(value = nom, onValueChange = {nom = it}, label = {Text("Escriu el teu nom")})
        Spacer(modifier = Modifier.height(290.dp))
        Box(modifier = Modifier
            .background(Color.Cyan)
            .fillMaxWidth()
            .fillMaxHeight()
            .align(Alignment.CenterHorizontally)) {
            Text(
                text = "NEXT STEP",
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.Center)
                    .clickable { navController.navigate(Routes.Pantalla2.route) })
        }
    }

}






