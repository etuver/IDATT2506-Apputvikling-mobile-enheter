package no.eventu.assignment2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.system.exitProcess

class CalculatorActivity : Activity() {
    private val requestCode = 1337



    //var addBtn = findViewById<Button>(R.id.addBtn)
    //var multiBtn = findViewById<Button>(R.id.multiBtn)
    //var num1 = findViewById<TextView>(R.id.number1View)
    //var num2 = findViewById<TextView>(R.id.number2View)
    //var upperLimit = findViewById<TextView>(R.id.upperLimit)
    //var answer = findViewById<TextView>(R.id.answer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        var addBtn = findViewById<Button>(R.id.addBtn)
        var multiBtn = findViewById<Button>(R.id.multiBtn)


        /**
         *
        var num1 = findViewById<TextView>(R.id.number1View)
        var num2 = findViewById<TextView>(R.id.number2View)
        var upperLimit = findViewById<TextView>(R.id.upperLimit)
        var answer = findViewById<TextView>(R.id.answer)
         */


        addBtn.setOnClickListener {
            add()
        }



        multiBtn.setOnClickListener {
            multiplicate()
        }

    }



    private fun multiplicate(){
        var num1 = findViewById<TextView>(R.id.number1View).text.toString().toInt()
        var num2 = findViewById<TextView>(R.id.number2View).text.toString().toInt()
        var answer = findViewById<TextView>(R.id.answer).text.toString().toInt()

            val correct = num1 * num2
            if (correct == answer){
                Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, getString(R.string.wrong) +" " + correct , Toast.LENGTH_SHORT).show()
            }
        getNewNumbers()
    }



    private fun add(){
        var num1 = findViewById<TextView>(R.id.number1View).text.toString().toInt()
        var num2 = findViewById<TextView>(R.id.number2View).text.toString().toInt()
        var answer = findViewById<TextView>(R.id.answer).text.toString().toInt()

        val correct = num1 + num2
        if (correct == answer){
            Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, getString(R.string.wrong)  + " " + correct, Toast.LENGTH_SHORT).show()
        }
        getNewNumbers()
    }


    private fun getNewNumbers(){
        var upperLimit = findViewById<TextView>(R.id.upperLimit).text.toString().toInt()
        onClickStartRandomActivity(upperLimit)
    }

    fun onClickStartRandomActivity(limit: Number) {
        val intent = Intent(this, RandomNumberActivity::class.java);
        intent.putExtra("upperLimit", limit)
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        var num1 = findViewById<TextView>(R.id.number1View)
        var num2 = findViewById<TextView>(R.id.number2View)

        if (resultCode == RESULT_OK){
            num1.text = data.getIntExtra("result2", 0).toString()
            num2.text = data.getIntExtra("result2", 0).toString()
        }

    }




}