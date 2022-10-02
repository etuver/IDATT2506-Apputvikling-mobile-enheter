package no.eventu.assignment3

import android.app.AlertDialog
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity

class MainActivity : Activity() {

    private var birthdays: ArrayList<Friend> = ArrayList()
    private var requestCode = 1337


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        birthdays.add(Friend("Even Tuverud", "16.01.1991"))
        birthdays.add(Friend("Petter Smart", "23.04.1994"))
        birthdays.add(Friend("Tomte Gløgg", "11.11.2011"))
        birthdays.add(Friend("Onkel Skrue", "22.02.1922"))
        initList()



        val addNewBtn = findViewById<Button>(R.id.addNewButton)

        //val listview = findViewById<ListView>(R.id.listView)
        //val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, birthdays)


        addNewBtn.setOnClickListener(){
            onClickStartAddFriendActivity()
        }
    }


    private fun initList() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_activated_1, birthdays
        )
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter;
        listView.choiceMode = ListView.CHOICE_MODE_SINGLE
        listView.setOnItemClickListener {
            parent, view, position, id ->
            onClickStartEditFriendActivity(position)
        }


    }


    private fun onClickStartAddFriendActivity(){
        val intent = Intent(this, AddFriendActivity::class.java)
        startActivityForResult(intent, requestCode)
    }

    private fun onClickStartEditFriendActivity(index: Int){
        val intent = Intent(this, EditFriendActivity::class.java)
        intent.putExtra("arrayIndex", index)
        intent.putExtra("name", birthdays[index].name)
        intent.putExtra("birthday", birthdays[index].birthday)
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
       if (resultCode == RESULT_FIRST_USER){
           var newname = "";
           var newBirthday = "";
           newname = data.getStringExtra("name").toString()
           newBirthday = data.getStringExtra("birthday").toString()
           if (newname != "" && newBirthday != ""){
               birthdays.add(Friend(newname, newBirthday));
               initList()
           }
       }
        else if (resultCode == RESULT_OK){
           var newName = "";
           var newDate = "";
           newName = data.getStringExtra("newName").toString()
           newDate = data.getStringExtra("newDate").toString()
           var index = data.getIntExtra("arrayIndex", 0)
           if (newName != "" && newDate != ""){
               birthdays[index] = Friend(newName, newDate)
           }
           initList()
       }
    }


}

