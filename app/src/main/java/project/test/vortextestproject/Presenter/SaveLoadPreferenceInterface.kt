package project.test.vortextestproject.Presenter

interface SaveLoadPreferenceInterface {

    fun loadLink(link : String): String?

    fun saveLink(link: String , home : String)

}