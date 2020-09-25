package project.test.vortextestproject.Presenter

import android.content.Context
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ProgressBar
import android.widget.TextView
import project.test.vortextestproject.MainActivity
import project.test.vortextestproject.R
import project.test.vortextestproject.RetrofitModel.GetInterface
import project.test.vortextestproject.View.FragmentWebView

class ProgressBarAnimation(
    private var context: Context?,
    private var progressBar: ProgressBar?,
    private var textView: TextView?,
    private var from: Float,
    private var to: Float
) : Animation() {

    var i = 0
    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)
        val value = from + (to - from) * interpolatedTime

        progressBar!!.progress = value.toInt()
        textView!!.text = ("${value.toInt()}  %")

        if (value == to && value == 100f) {
            i++
            if (i == 2) {
                val firstEnterInApp = FirstEnter(context!!)
                i = 0

                val loadDate = SaveLoadSharedPreference(context!!)
                val url = loadDate.loadLink(firstEnterInApp.firstEnter()).toString()

                (context as MainActivity).supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    FragmentWebView(url)
                ).commit()
            }
        }
    }

}