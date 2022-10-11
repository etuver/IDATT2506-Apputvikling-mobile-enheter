package no.eventu.assignment2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private val upperLimit = 100;
    private var randomresult = 0;
    private val requestCode = 1337;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.startBtn)
        val calculatorBtn = findViewById<Button>(R.id.openCalculatorBtn)

        btn.setOnClickListener {
            onClickStartRandomActivity()
        }

        calculatorBtn.setOnClickListener {
            onClickStartCalculatorActivity()
        }

    }

    fun onClickStartRandomActivity() {
        val intent = Intent(this, RandomNumberActivity::class.java);
        intent.putExtra("upperLimit", upperLimit)
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        val text = findViewById<TextView>(R.id.textView)

        if (resultCode != RESULT_OK){
            text.text = "error"
        }
        else{
                    randomresult = data.getIntExtra("result", 0)
                   text.text = randomresult.toString()
          }
    }

    fun onClickStartCalculatorActivity(){
        val intent = Intent(this, CalculatorActivity::class.java)
        startActivity(intent)
    }
}