package com.example.spinthewheel.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.spinthewheel.R
import com.example.spinthewheel.viewmodel.ViewModel

val viewmodel1 = ViewModel()


@Composable
fun GuessTheWordScreen(navController: NavController) {
    val st2 by viewmodel1.uist.collectAsState()
    var wordInput by remember { mutableStateOf(TextFieldValue("")) }


    Box {
        val imageModifier = Modifier
            .fillMaxSize()
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background",
            contentScale = ContentScale.FillBounds,
            modifier = imageModifier
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.TopCenter)
        ) {
            Spacer(modifier = Modifier.height(55.dp))

            Row() {
                Column() {
                    Text(
                        text = "Antal liv tilbage: ${st2.lives}",
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "Penge optjent: ${st2.moneywon}",
                        fontWeight = FontWeight.Bold,
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(onClick = { navController.navigate(Screen.GuessScreen.route) }) {
                    Text(text = "Genstart")
                    viewmodel1.RestartGame()

                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Kategorien er: ${st2.category}",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {

                viewmodel1.randomCategory()
            }) {
                Text(text = "Få et nyt ord")
            }
            Spacer(modifier = Modifier.height(16.dp))
//            Text(
//                text = "Word: $targetWord",
//                fontWeight = FontWeight.Bold,
//                color = Color.Black,
//            )

            TextField(
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters,
                    keyboardType = KeyboardType.Text
                ),

                value = wordInput,
                onValueChange = { wordInput = it },
                label = { Text("Skriv dit bud her") },
            )

            Text(text = "Gæt ordet:")
            Text(text = st2.wordDisplay, letterSpacing = 2.sp)

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
              viewmodel1.guessWord(wordInput.text)

                viewmodel1.updateState()

            }) {
                Text(text = "Tryk her for at gætte")
            }
            Spacer(modifier = Modifier.height(16.dp))

            //Show used letters
            Text(text = "Du har brugt følgende bogstaver: ")
            Text(st2.usedLetters.toString())



            Button(onClick = { viewmodel1.moneyWon() }) {
                Text(text = "Drej hjulet")

            }
            val infiniteTransition = rememberInfiniteTransition()
            val angle by infiniteTransition.animateFloat(
                initialValue = 0F,
                targetValue = 360F,
                animationSpec = infiniteRepeatable(
                    animation = tween(7000, easing = LinearEasing)
                )
            )
            Image(
                painter = painterResource(R.drawable.wheellogo),
                contentDescription = "wheel",
                Modifier
                    .size(100.dp)
                    .graphicsLayer {
                        rotationZ = angle
                    }
            )

            Text(text = "Du har vundet: ${st2.price} kr")

            Spacer(modifier = Modifier.height(16.dp))
            if (st2.state == 3) {
                Text(text = "Du har vundet - genstart spil", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
            if (st2.state == 2) {
                Text(text = "Du har tabt - genstart spil", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
        }

    }

}


/*
  val infiniteTransition = rememberInfiniteTransition()
        val angle by infiniteTransition.animateFloat(
            initialValue = 0F,
            targetValue = 360F,
            animationSpec = infiniteRepeatable(
                animation = tween(7000, easing = LinearEasing)
            )
        )
        Image(
            painter = painterResource(R.drawable.wheellogo),
            contentDescription = "wheel",
            Modifier
                .size(100.dp)
                .graphicsLayer {
                    rotationZ = angle
                }
        )
 */