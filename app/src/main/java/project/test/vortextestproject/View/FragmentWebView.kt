package project.test.vortextestproject.View

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import project.test.vortextestproject.R

class FragmentWebView(var url: String) : Fragment(), IOnBackPressedInterface {

    lateinit var webView: WebView;
    lateinit var progressWeb : ProgressBar;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutView: View = inflater.inflate(R.layout.fragment_web_view, container, false)
        webView = layoutView.findViewById(R.id.webView)
        progressWeb = layoutView.findViewById(R.id.progressWeb)
        progressWeb.max = 100

        webView.webViewClient = object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                progressWeb.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                progressWeb.visibility = View.GONE
                super.onPageFinished(view, url)
            }
        };

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                progressWeb.progress = newProgress;
            }
        }

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.settings.setAppCacheEnabled(true)
        webView.settings.cacheMode = WebSettings.LOAD_DEFAULT

        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptThirdPartyCookies(webView, true)

        webView.loadUrl(url)

        return layoutView
    }

    override fun onBackPressed(): Boolean {
        return if (webView.canGoBack()) {
            webView.goBack()
            false
        } else {
            true
        }
    }



}