package com.example.webrequests

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.webrequests.databinding.ActivityMainBinding
import kotlinx.coroutines.*
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
                val url = URL(URL_ADDRESS)
                val urlConnection = url.openConnection() as HttpURLConnection
                try{
                    val inputStream: InputStream = BufferedInputStream(urlConnection.inputStream)
                    val text = inputStream.bufferedReader().readText()
                    runOnUiThread {
                        toast(text)
                    }
                }finally {
                    urlConnection.disconnect()
                }
            }

        }
    }

    fun toast(mes: String) {
        Toast.makeText(this, mes, Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val URL_ADDRESS = "https://random.dog/woof.json"
    }
}