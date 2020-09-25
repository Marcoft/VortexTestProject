package project.test.vortextestproject.RetrofitModel

import com.google.gson.annotations.SerializedName

class Link {

    @SerializedName("link")
    val link : String;
    @SerializedName("home")
    val home : String;

    constructor(link: String, home: String) {
        this.link = link
        this.home = home
    }

}