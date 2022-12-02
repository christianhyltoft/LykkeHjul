package com.example.spinthewheel.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

data class State(val int: Int = 0, val price: Int = 0, val lives: Int = 5, val multiplier: Int = 0, val moneywon: Int = 0, val targetWord: String = "",val category: String = "", val wordDisplay: String = "", val state:Int = 0, val rollState: Int = 1,val usedLetters: MutableList<String> = mutableListOf("") )


