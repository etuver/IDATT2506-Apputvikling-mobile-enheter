package no.eventu.server

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val log = findViewById<TextView>(R.id.messages)
        Server(log).start()
    }

    /**
     *

    fun addToServerLog(logstring: String){
        val log = findViewById<TextView>(R.id.messages)
        var oldLog = log.text
        var newLog = oldLog.toString() + "\n" + logstring
        log.setText(newLog)
    }*/
}