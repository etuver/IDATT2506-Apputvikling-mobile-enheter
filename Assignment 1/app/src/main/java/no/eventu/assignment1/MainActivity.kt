package no.eventu.assignment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menu.add("Even")
        menu.add("Tuverud")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.title.equals("Even")){
            Log.w("menuChoice", "Even")
        }
        else if (item.title.equals("Tuverud")){
            Log.e("menuChoice", "Tuverud")
        }
        return true
        //else Log.i("menuChoice", "No name was chosen")
    }
}