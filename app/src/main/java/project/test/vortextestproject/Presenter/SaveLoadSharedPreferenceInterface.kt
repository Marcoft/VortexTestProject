package project.test.vortextestproject.Presenter

import android.content.Context

class SaveLoadSharedPreferenceInterface(var context: Context) : SaveLoadPreferenceInterface {

    private val save_load = "save_load"

    override fun loadLink(link : String): String? {
        val sharedPref = context.getSharedPreferences(save_load,Context.MODE_PRIVATE)
        return sharedPref.getString(link, "")
    }

    override fun saveLink(link: String , home : String) {
        val sharedPref = context.getSharedPreferences(save_load,Context.MODE_PRIVATE)
        val ed = sharedPref.edit()
        ed.putString("link", link)
        ed.putString("home", home)
        ed.apply()
    }

}