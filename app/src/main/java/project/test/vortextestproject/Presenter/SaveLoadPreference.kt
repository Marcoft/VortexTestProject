package project.test.vortextestproject.Presenter

interface SaveLoadPreference {

    fun loadLink(link : String): String?

    fun saveLink(link: String , home : String)

}