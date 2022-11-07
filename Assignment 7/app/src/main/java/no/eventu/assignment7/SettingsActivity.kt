package no.eventu.assignment7

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import no.eventu.assignment7.databinding.SettingsActivityBinding
import no.eventu.assignment7.managers.MyPreferenceManager

class SettingsActivity : AppCompatActivity(),
    SharedPreferences.OnSharedPreferenceChangeListener,
    Preference.SummaryProvider<ListPreference>{

    private lateinit var ui: SettingsActivityBinding
   // private lateinit var myPreferenceManager: MyPreferenceManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // myPreferenceManager = MyPreferenceManager(this)
       // myPreferenceManager.registerListener(this)

        ui = SettingsActivityBinding.inflate(layoutInflater)
        setContentView(ui.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_container, SettingsFragment())
            .commit()

        ui.button.setOnClickListener{
            //setResult(RESULT_OK, Intent().putExtra("colors", myPreferenceManager.getString("colors", "none")))
            finish()
        }

    }


    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preference_screen, rootKey)
        }
    }


    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == getString(R.string.background_color)){
            val color = sharedPreferences?.getString(key, sharedPreferences.toString())
            val layout = findViewById<TextView>(R.id.resultTextView)
            layout.setBackgroundColor(Color.parseColor(color))

        }
    }

    override fun provideSummary(preference: ListPreference?): CharSequence {
        return when(preference?.key){
            getString(R.string.background_color) -> preference.entry
            else -> "error"
        }
    }

    /**
     *
    override fun onDestroy() {
        super.onDestroy()
        myPreferenceManager.unregisterListener(this)
    }*/

}