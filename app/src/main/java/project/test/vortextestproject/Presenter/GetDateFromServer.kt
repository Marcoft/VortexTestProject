package project.test.vortextestproject.Presenter

import android.content.Context
import android.widget.Toast
import project.test.vortextestproject.RetrofitModel.GetInterface
import project.test.vortextestproject.RetrofitModel.Link
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetDateFromServer(val context: Context, private val getInterface : GetInterface) {

    fun getOpened() {

        val saveDate = SaveLoadSharedPreferenceInterface(context)

        val call: Call<Link> = getInterface.getProd()
        call.enqueue(object : Callback<Link?> {
            override fun onResponse(call: Call<Link?>, response: Response<Link?>) {
                if (!response.isSuccessful) {
                    Toast.makeText(context, "Code: " + response.code(), Toast.LENGTH_SHORT).show()
                    return
                }
                val posts: Link? = response.body()
                if (posts != null) {
                    val link = posts.link
                    val home = posts.home

                    saveDate.saveLink(link, home)
            }
            }

            override fun onFailure(call: Call<Link?>, t: Throwable) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

}