package no.eventu.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val messageLog = findViewById<TextView>(R.id.messages)
        val sendButton = findViewById<Button>(R.id.sendMessageButton)

        sendButton.setOnClickListener(){
            handleSendMessageButton()
        }
    }

    private fun handleSendMessageButton(){
        val messageEditText = findViewById<EditText>(R.id.messageEditText)
        val usernameEditText = findViewById<EditText>(R.id.usernameEdit)
        val messageLog = findViewById<TextView>(R.id.messages)
        //messageLog.setText(messageLog.text.toString() + "\nFrom me: " +messageEditText.text.toString() )
        Client(messageLog, usernameEditText.text.toString(), messageEditText.text.toString() ).start(usernameEditText.text.toString(), messageEditText.text.toString())
    }
}