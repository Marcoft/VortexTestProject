package project.test.vortextestproject.RetrofitModel

import retrofit2.Call
import retrofit2.http.GET

interface GetInterface {

    @GET("prod")
    fun getProd() : Call<Link>;

}