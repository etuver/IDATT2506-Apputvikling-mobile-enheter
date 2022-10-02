package no.eventu.assignment3

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText



class AddFriendActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_friend)


        val saveButton = findViewById<Button>(R.id.save_button)
        val cancelButton = findViewById<Button>(R.id.cancel_button)
        val newName = findViewById<EditText>(R.id.newName)
        val newBirthday =  findViewById<EditText>(R.id.newBirthday)

        cancelButton.setOnClickListener(){
            setResult(RESULT_CANCELED, intent)
            finish()
        }

        saveButton.setOnClickListener(){
            val name = newName.text.toString()
            val birthday = newBirthday.text.toString()

            intent.putExtra("name", name)
            intent.putExtra("birthday", birthday)
            setResult(RESULT_FIRST_USER, intent)
            finish()

        }







    }
}