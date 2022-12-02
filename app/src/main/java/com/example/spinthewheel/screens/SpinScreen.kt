import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.spinthewheel.R
import com.example.spinthewheel.screens.Screen
import com.example.spinthewheel.viewmodel.ViewModel

//
//val viewmodel2 = ViewModel()
//
//@Composable
//fun SpinScreen(navController: NavController) {
//    Box() {
//        val imageModifier = Modifier
//            .fillMaxSize()
//        Image(
//            painter = painterResource(id = R.drawable.background),
//            contentDescription = "background",
//            contentScale = ContentScale.FillBounds,
//            modifier = imageModifier
//        )
//       // DiceRollerApp()
//    }
//}
//
//
//@Preview
//@Composable
//fun DiceRollerApp() {
//    DiceWithButtonAndImage(modifier = Modifier
//        .fillMaxSize()
//        .wrapContentSize(Alignment.Center)
//    )
//}
//
//@Composable
//fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
//    var result by remember { mutableStateOf(0) };
//    val imageResult = when (result) {
//        1 -> R.drawable.ethundrede
//        2 -> R.drawable.tohundrede
//        3 -> R.drawable.trehundrede
//        4 -> R.drawable.femhund
//        5 -> R.drawable.tusind
//        6 -> R.drawable.totusind
//        else -> R.drawable.bankrupt
//    }
//
//
//    Column(modifier = modifier,
//        horizontalAlignment = Alignment.CenterHorizontally,) {
//        val infiniteTransition = rememberInfiniteTransition()
//        val angle by infiniteTransition.animateFloat(
//            initialValue = 0F,
//            targetValue = 360F,
//            animationSpec = infiniteRepeatable(
//                animation = tween(7000, easing = LinearEasing)
//            )
//        )
//        Image(
//            painter = painterResource(R.drawable.wheellogo),
//            contentDescription = "wheel",
//            Modifier
//                .size(200.dp)
//                .graphicsLayer {
//                    rotationZ = angle
//                }
//            )
//        Spacer(modifier = Modifier.height(20.dp))
//
//        Text(
//            text = "Du landede på feltet:",
//            fontWeight = FontWeight.Bold,
//            fontFamily = FontFamily.Monospace
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Image(painter = painterResource(imageResult),
//            contentDescription = result.toString())
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(onClick = {
//            if(viewmodel2.state == 0) {
//                result = (1..7).random()
//            }
//        }) {
//            Text(text = stringResource(id = R.string.SpinWheel))
//        }
//    }
//
//}
//
//fun MultiplierResult(multiplier: Int): Int {
//    return multiplier
//}