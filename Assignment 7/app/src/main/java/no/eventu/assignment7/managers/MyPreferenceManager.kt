package no.eventu.assignment7.managers

import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import no.eventu.assignment7.R

class MyPreferenceManager(private val activity: AppCompatActivity) {

    private val resources = activity.resources
    private val preferences = PreferenceManager.getDefaultSharedPreferences(activity)
    private val editor: SharedPreferences.Editor = preferences.edit()

     fun putString(key: String, value: String){
         editor.putString(key, value)
         editor.apply()
     }

    fun getString(key: String, defaultValue: String): String{
        return preferences.getString(key, defaultValue)?: defaultValue
    }

    /**
     *
    fun updateBackgroundColor(){
        val backgroundColorValues = resources.getStringArray(R.array.background_color_values)
        val value = preferences.getString(
            resources.getString(R.string.background_color),
            resources.getString(R.string.background_color_default)
        )
        when (value) {
            activity.

        }
    }     */

    fun registerListener(activity: SharedPreferences.OnSharedPreferenceChangeListener){
        preferences.registerOnSharedPreferenceChangeListener(activity)
    }

    fun unregisterListener(activity: OnSharedPreferenceChangeListener){
        preferences.unregisterOnSharedPreferenceChangeListener(activity)
    }







}