package com.example.spinthewheel.viewmodel

import androidx.lifecycle.ViewModel
import com.example.spinthewheel.model.State
import com.example.spinthewheel.screens.Screen
import com.example.spinthewheel.screens.viewmodel1
import kotlinx.coroutines.flow.*
import java.util.*
import kotlin.random.Random

val seed = Calendar.getInstance().timeInMillis
var random = Random(seed)

class ViewModel : ViewModel() {
    private val st1 = MutableStateFlow(State())
    val uist: StateFlow<State> = st1.asStateFlow()
    fun updt() {
        st1.update { t -> t.copy(int = 5) }
    }


//Save the letters used from ShowLettersUsed()
    //Lives remaining in the game


    //randomize a list of integers as the price pool
    fun moneyWon() {
        if (st1.value.rollState == 0) {
            return
        }
        val pricePool = listOf(100, 200, 300, 500, 1000, 2000, 0)
        val randomPrice = pricePool.random(random = random)
        st1.update { t -> t.copy(price = randomPrice, rollState = 0) }
        println("The price is: $randomPrice")

    }

    fun looseLives() {
        if (st1.value.lives == 0) {
            return
        }
        st1.update { t -> t.copy(lives = st1.value.lives - 1) }
    }

    fun updateState() {
        st1.update { t -> t.copy(rollState = 1) }
    }

    //A validation function to check for the amount of correct letters in the word and return it as an integer
    fun CorrectLetters(word: String, targetWord: String): Int {
        var correctLetters = 0
        for (i in targetWord) {
            if (word[0].uppercaseChar() == i) {
                correctLetters++
            }
        }
        return correctLetters
    }


    val sports = listOf("Sport", "FODBOLD", "BASKETBALL", "HOCKEY", "DANS")
    val food = listOf("Food", "BROWNIE", "OST", "CHOKOLADE", "KAGEMAND")
    val spil = listOf("Spil", "COUNTERSTRIKE", "DOTA", "FORTNITE", "MINECRAFT")

    val categories = listOf("Sport", "Food", "Spil")


    fun randomCategory() {
        var category = listOf<String>("Sport", "Food", "Spil")
        val categoryforword = category[(Math.random() * category.size).toInt()]
        getWord(categoryforword)
        InitializeWord()

        st1.update { t -> t.copy(category = categoryforword) }
    }

    fun guessWord(wordInput: String) {
        if (wordInput.equals(""))
            return
        if (st1.value.rollState == 1) {
            return
        }
        if(st1.value.wordDisplay.equals(st1.value.targetWord)){
            st1.update { t -> t.copy(state = 3) }
            return
        }


        GuessedLetters(wordInput)
        var wordDisplay =
            viewmodel1.DisplayWord(
                st1.value.targetWord,
                wordInput.toCharArray()[0],
                st1.value.wordDisplay
            )
        //If guessed word is equal to target word, the player wins
        //If the letter is not in the word, remove a life
        if (CorrectLetters(wordInput, st1.value.targetWord) == 0) {
            updateState()
            looseLives()
        }
        //If the letter is in the word, add money
        if (CorrectLetters(wordInput, st1.value.targetWord) > 0) {
            updateState()
        }
        //If the player has no lives left, navigate to the lose screen
        if (st1.value.lives == 0) {
            st1.update { t -> t.copy(state = 2) }
            Looser()
        }
        Winner()

    }


    fun getWord(category: String) {
        var word = ""
        var words = listOf(sports, food, spil)
        for (i in words.indices) {
            if (words[i][0] == category) {
                word = words[i][(Math.random() * (words[i].size - 1) + 1).toInt()]
            }
        }
        st1.update { t -> t.copy(targetWord = word) }
    }

    fun validateWord(word: String, guess: String): Int {
        //Checks if the letter is in the word and how many times it appears
        var counter = 0
        for (i in 0..word.length) {
            if (guess == word[i].toString()) {
                counter++
            }

        }
        return counter

    }


    fun InitializeWord(): String {
        var current = ""
        for (i in 0 until st1.value.targetWord.toCharArray().size) {
            current += "-"
        }
        st1.update { t -> t.copy(wordDisplay = current) }
        return current
    }

    private fun DisplayWord(word: String, guess: Char, current: String): String {
        var newCur = StringBuilder(current)
        var counter = 0

        for (i in 0 until word.toCharArray().size) {
            if (guess.lowercaseChar() == word.toCharArray()[i].lowercaseChar()) {
                newCur.setCharAt(i, guess)
                counter++
            }
        }
        val newMoney = st1.value.moneywon + st1.value.price * counter
        val word1 = newCur.toString().uppercase()

        st1.update { t -> t.copy(wordDisplay = word1, moneywon = newMoney) }
        return word1
    }

    //Function to show usedLetters
    fun GuessedLetters(letter: String) {
        st1.value.usedLetters.add(letter.toString())
        val usedLetters=st1.value.usedLetters
        st1.update { t->t.copy(usedLetters = usedLetters) }

        }


    fun RestartGame() {
        st1.update { t -> t.copy(lives = 5, moneywon = 0, price = 0) }
        updateState()
        randomCategory()
    }

    //If all letters from guessedLettes usedLetters are in the word, the player wins
    fun Winner(){
           if(st1.value.wordDisplay.equals(st1.value.targetWord)){
                st1.update { t -> t.copy(state = 3) }
               return
           }

    }


    fun Looser() {
        if (st1.value.lives == 0) {
            st1.update { t -> t.copy(state = 2) }
        }
    }


    /*
    @Composable
    fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
        var result by remember { mutableStateOf(0) };
        val imageResult = when (result) {
            1 -> R.drawable.ethundrede
            2 -> R.drawable.tohundrede
            3 -> R.drawable.trehundrede
            4 -> R.drawable.femhund
            5 -> R.drawable.tusind
            6 -> R.drawable.totusind
            else -> R.drawable.bankrupt
        }

        val multiplier = result;


        Column(modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,) {
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
                    .size(200.dp)
                    .graphicsLayer {
                        rotationZ = angle
                    }
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Du landede på feltet:",
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(painter = painterResource(imageResult),
                contentDescription = result.toString())
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if(viewmodel2.state == 0) {
                    result = (1..7).random()
                    viewmodel2.state = 1
                }
            }) {
                Text(text = stringResource(id = R.string.SpinWheel))
            }
        }

    }

     */

}




