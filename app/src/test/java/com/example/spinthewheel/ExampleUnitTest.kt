package com.example.spinthewheel

import com.example.spinthewheel.viewmodel.ViewModel
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun counterTimes() {
        val model= ViewModel()
        assertEquals(model.CorrectLetters("L","LLLHUYUGHYVL"),4)
    }
}