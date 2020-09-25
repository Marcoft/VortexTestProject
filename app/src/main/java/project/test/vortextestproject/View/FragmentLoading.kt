package project.test.vortextestproject.View

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import project.test.vortextestproject.Presenter.GetDateFromServer
import project.test.vortextestproject.Presenter.ProgressBarAnimation
import project.test.vortextestproject.R
import project.test.vortextestproject.RetrofitModel.GetInterface

class FragmentLoading(private var getInterface: GetInterface?) : Fragment() {

    var textProgress : TextView? = null
    var progressBar : ProgressBar? = null
    lateinit var getDateFromServer : GetDateFromServer

    private val firstStartApp = "first_start"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutView: View = inflater.inflate(R.layout.fragment_loading, container, false)

        textProgress = layoutView.findViewById(R.id.textProgress)
        progressBar = layoutView.findViewById(R.id.progressBar)

        val sp  = context!!.getSharedPreferences(firstStartApp, Context.MODE_PRIVATE) // Check First Enter
        var firstStart = false
        if (sp != null) {
            firstStart = sp.getBoolean("firstStart", true)
        }
        if (firstStart) {
            getDateFromServer = GetDateFromServer(context!!, getInterface!!)
            getDateFromServer.getOpened()
        }

        progressAnimation()

        return layoutView
    }

    fun progressAnimation() {
        val anim = ProgressBarAnimation(context, progressBar, textProgress, 0f, 100f)

        //Check is it a first enter
        val sp  = context!!.getSharedPreferences(firstStartApp, Context.MODE_PRIVATE) // Check First Enter
        var firstStart = false
        if (sp != null) {
            firstStart = sp.getBoolean("firstStart", true)
        }
        if (firstStart) {
            anim.duration = 8000 // if first long load
        }else{
            anim.duration = 2000 // else fast load
        }

        progressBar!!.animation = anim
    }

}