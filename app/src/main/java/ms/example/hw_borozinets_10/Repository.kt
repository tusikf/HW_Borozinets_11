package ms.example.hw_borozinets_10

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

private const val PREF_NAME = "preference_name"
private const val SHARED_PREF_KEY = "shared_pref_key"
private lateinit var prefs:SharedPreferences
private  var editor: SharedPreferences.Editor = prefs.edit()
private lateinit var localValue:String

class Repository {

    fun getText(context: Context): String {
        return when {
            getDataFromLocalVariable()!=null -> getDataFromLocalVariable()!!
            getDataFromSharedPreference(context)!=null -> getDataFromSharedPreference(context)!!
            else -> "no one source doesn't contain string"
        }
    }

    fun saveText(text:String) {

        //prefs = text.getSharedPreferences(PREF_NAME, 0)
        editor.putString(SHARED_PREF_KEY, text.toString())
        editor.apply()
        localValue = text
        Log.d("TAG".toString(), "Save Function")
        println("yes")
        println(localValue.toString())

    }

    fun clearText() {
        editor.remove(SHARED_PREF_KEY)
        localValue = null.toString()
    }

    private fun getDataFromLocalVariable(): String? {

        return localValue
    }
    private fun getDataFromSharedPreference(context: Context): String? {
        val prefs = context.getSharedPreferences(PREF_NAME, 0)
        return  prefs.getString(SHARED_PREF_KEY, null)
    }
}