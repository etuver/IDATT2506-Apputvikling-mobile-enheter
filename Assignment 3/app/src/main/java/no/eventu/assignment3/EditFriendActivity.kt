package no.eventu.assignment3

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class EditFriendActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_friend)


        val saveButton = findViewById<Button>(R.id.edit_save_button)
        val cancelButton = findViewById<Button>(R.id.edit_cancel_button)
        var editName = findViewById<EditText>(R.id.editName)
        var editDate = findViewById<EditText>(R.id.editBirthday)
        var name = intent.getStringExtra("name")
        var birthdate = intent.getStringExtra("birthday")
        editName.setText(name)
        editDate.setText(birthdate)

        saveButton.setOnClickListener(){
            intent.putExtra("newName", editName.text.toString())
            intent.putExtra("newDate", editDate.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }

        cancelButton.setOnClickListener(){
            setResult(RESULT_CANCELED, intent)
            finish()
        }







    }
}