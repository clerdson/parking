package com.clerdsonjuca.drive

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
fun getSearchUrl(query: String?): String? {
    return query
}
class Game(){
    var score = 0
    private set
    var highScore= 0
    private set
    fun increment (){
        score++
        if (score>highScore){
            highScore++
        }
    }
}
class mainTest{
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun main(){
        // Test getSearchUrl returns not null if query is a string
// 1
        val nonNullResult = getSearchUrl("toaster")
        if (nonNullResult != null) {
// 2
            print("Success\n")
        } else {
// 3
            throw AssertionError("Result was null")
        }
    }
    @Test
    fun shouldIncreament(){
            val game = Game()
        game.increment()
        if (game.highScore == 1){
            print("success")
        }else{
            throw java.lang.AssertionError("error")
        }
    }

}