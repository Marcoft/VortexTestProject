package project.test.vortextestproject

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import project.test.vortextestproject.RetrofitModel.GetInterface
import project.test.vortextestproject.View.FragmentLoading
import project.test.vortextestproject.View.IOnBackPressedInterface
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var getInterface : GetInterface;
    private var fragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        connectNetwork()

        fragment = FragmentLoading(getInterface)
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            fragment!!
        ).commit()
    }

    private fun connectNetwork() {
        val rxAdapter: RxJavaCallAdapterFactory =
            RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io())
        val gson = GsonBuilder().serializeNulls().create()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        // connect retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://efs5i1ube5.execute-api.eu-central-1.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(rxAdapter)
            .client(okHttpClient)
            .build()
        getInterface = retrofit.create<GetInterface>(GetInterface::class.java)
    }

    override fun onBackPressed() {
        val fragment =
            this.supportFragmentManager.findFragmentById(R.id.fragment_container)
        (fragment as? IOnBackPressedInterface)?.onBackPressed()?.not()?.let {
            if (it){
                // webView go back
            } else {
                super.onBackPressed()
            }
        }
    }

}