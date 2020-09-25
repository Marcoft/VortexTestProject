package project.test.vortextestproject.Presenter

import android.content.Context

class FirstEnter(var context: Context) {

    private val firstStartApp = "first_start"
    fun firstEnter(): String {
        val result : String;
        val sp  = context.getSharedPreferences(firstStartApp, Context.MODE_PRIVATE) // For First Download App
        var firstStart = false
        if (sp != null) {
            firstStart = sp.getBoolean("firstStart", true)
        }
        if (firstStart) {
            sp!!.edit().putBoolean("firstStart", false).apply()
            result = "link"
        } else {
            result = "home"
        }

        return result
    }
}