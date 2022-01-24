package com.example.webrequests

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.webrequests.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setOnclickListener()
        setContentView(binding.root)
    }

    fun setOnclickListener(){
        binding.buttonRequest.setOnClickListener {
            CoroutineScope(Dispatchers.IO + Job()).launch{
                val url = URL(BASE_URL)
                val urlConnection = url.openConnection() as HttpURLConnection
                try{
                    val inputStream: InputStream = BufferedInputStream(urlConnection.inputStream)
                    val jsonStr = inputStream.bufferedReader().readText()
                    val mediaUrl = getMediaUrl(jsonStr)
                    Handler(Looper.getMainLooper()).post {
                        setWebView(mediaUrl)
                    }
                }finally {
                    urlConnection.disconnect()
                }
            }

        }
    }

    fun getMediaUrl(jsonStr: String): String = JSONObject(jsonStr).getString(URL)

    fun setWebView(url: String) {
        with(binding.webView){
            settings.javaScriptEnabled = true
            loadUrl(url)
        }

    }

    companion object {
        private const val URL = "url"
        private const val BASE_URL = "https://random.dog/woof.json"
    }
}