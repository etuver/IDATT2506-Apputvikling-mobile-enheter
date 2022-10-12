package no.eventu.assignment5

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

const val URL = "https://bigdata.idi.ntnu.no/mobil/tallspill.jsp"

class MainActivity : AppCompatActivity() {

    private val network: HttpWrapper = HttpWrapper(URL)


    //val guessBtn = findViewById<Button>(R.id.guessButton)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //guessBtn.setBackgroundColor(Color.WHITE)
        //guessBtn.setTextColor(Color.RED)

        val guessBtn = findViewById<Button>(R.id.guessButton)
        val newGameBtn = findViewById<Button>(R.id.newGameButton)
        guessBtn.setBackgroundColor(Color.RED)
        newGameBtn.setBackgroundColor(Color.GREEN)

        newGameBtn.setOnClickListener(){
            handleNewGameListener()
        }

        guessBtn.setOnClickListener(){
            handleGuessNewListener()
        }
    }


    private fun changeColorsGuess(){
        val guessBtn = findViewById<Button>(R.id.guessButton)
        val newGameBtn = findViewById<Button>(R.id.newGameButton)
        val responseTextView = findViewById<TextView>(R.id.responseView)
        guessBtn.setBackgroundColor(Color.GREEN)
        newGameBtn.setBackgroundColor(Color.RED)
        responseTextView.setTextColor(Color.BLACK)

    }

    private fun handleWon(){
        val responseTextView = findViewById<TextView>(R.id.responseView)
        val guessBtn = findViewById<Button>(R.id.guessButton)
        val newGameBtn = findViewById<Button>(R.id.newGameButton)
        guessBtn.setBackgroundColor(Color.RED)
        newGameBtn.setBackgroundColor(Color.GREEN)
        responseTextView.setTextColor(Color.rgb(50, 168, 82))
    }

    private fun handleLost(){
        val responseTextView = findViewById<TextView>(R.id.responseView)
        val guessBtn = findViewById<Button>(R.id.guessButton)
        val newGameBtn = findViewById<Button>(R.id.newGameButton)
        guessBtn.setBackgroundColor(Color.RED)
        newGameBtn.setBackgroundColor(Color.GREEN)
        responseTextView.setTextColor(Color.RED)
    }

    private fun handleResponse(response: String){
        val responseTextView = findViewById<TextView>(R.id.responseView)
        responseTextView.setText(response)
        if (response.contains("ppgi et tall mellom") || response.contains("er for") ){
            changeColorsGuess()
        }
        else if (response.contains("Beklager ingen")){
            handleLost()
        }
        else if (response.contains("du har vunnet")){
            handleWon()
        }
        else if (response.contains("ikke på riktig form")){
            handleWrongForm()
        }
    }

    private fun handleWrongForm(){
        val responseTextView = findViewById<TextView>(R.id.responseView)
        val guessBtn = findViewById<Button>(R.id.guessButton)
        val newGameBtn = findViewById<Button>(R.id.newGameButton)
        guessBtn.setBackgroundColor(Color.GREEN)
        newGameBtn.setBackgroundColor(Color.RED)
        responseTextView.setTextColor(Color.RED)
    }

    private fun handleNewGameListener(){
        var nameEdit = findViewById<EditText>(R.id.nameEdit)
        var cardNrEdit = findViewById<EditText>(R.id.cardNumberEdit)
        if (!nameEdit.text.isBlank() && !cardNrEdit.text.isBlank()){
            val name = nameEdit.text.toString()
            val cardnumber = cardNrEdit.text.toString()
            var map = mapOf<String, String>("navn" to name, "kortnummer" to cardnumber)

            CoroutineScope(IO).launch {
                val response: String = try {
                    network.get(map)
                }catch (e: java.lang.Exception){
                    Log.e("handleNewGameListener:",e.message!!)
                    e.toString()
                }
                MainScope().launch { handleResponse(response) }

            }

        }
        //handleResponse("Tallet er ikke på riktig form, det må være mellom 1 og 10!")
    }

    private fun handleGuessNewListener(){
        handleResponse("et, du har vunnet 100 kr som kommer inn på ditt kort 1234\n")
        var guessedNumber = findViewById<EditText>(R.id.guessEdit).text.toString()
        if (!guessedNumber.isBlank()){
            var map = mapOf<String, String>("tall" to guessedNumber)

            CoroutineScope(IO).launch {
                val response: String = try {
                    network.get(map)
                }catch (e: Exception){
                    Log.e("handleguessNewList()",e.message!!)
                    e.toString()
                }
                MainScope().launch { handleResponse(response) }
            }
        }
    }

}