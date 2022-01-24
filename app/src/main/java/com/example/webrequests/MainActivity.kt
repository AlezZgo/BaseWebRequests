package com.example.webrequests

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.webkit.WebSettings
import androidx.appcompat.app.AppCompatActivity
import com.example.webrequests.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setOnclickListener()
        setContentView(binding.root)
    }

    fun setOnclickListener() {
        binding.buttonRequest.setOnClickListener {
            CoroutineScope(Dispatchers.IO + Job()).launch {
                val client = OkHttpClient()

                val request = Request.Builder()
                    .url(BASE_URL)
                    .build()

                val call = client.newCall(request)
                val response = call.execute()

//                try {
//
//                    val mediaUrl = getMediaUrl(jsonStr)
//                    Handler(Looper.getMainLooper()).post {
//                        setWebView(mediaUrl)
//                    }
//                } finally {
//                    urlConnection.disconnect()
//                }
            }

        }
    }

    fun getMediaUrl(jsonStr: String): String = JSONObject(jsonStr).getString(URL)

    fun setWebView(url: String) {
        with(binding.webView) {
            settings.javaScriptEnabled = true
            settings.defaultZoom = WebSettings.ZoomDensity.FAR
            loadUrl(url)
        }

    }

    companion object {
        private const val URL = "url"
        private const val BASE_URL = "https://random.dog/woof.json"
    }
}