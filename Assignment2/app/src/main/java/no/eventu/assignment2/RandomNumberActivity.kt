package no.eventu.assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class RandomNumberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_number)

        val toastBtn = findViewById<Button>(R.id.toastBtn)

        var upperLimit = intent.getIntExtra("upperLimit", 5)

        //toastBtn.setOnClickListener {
            val random = (0..upperLimit).random();
            val randomNumber1 = (0..upperLimit).random();
            val randomNumber2 = (0..upperLimit).random();
            intent.putExtra("result", random)
            intent.putExtra("result2", randomNumber1)
            intent.putExtra("result3", randomNumber2)

            //Toast.makeText(this, randomNumber.toString(), Toast.LENGTH_SHORT).show()
            setResult(RESULT_OK, intent)
            finish()
        //}





    }
}