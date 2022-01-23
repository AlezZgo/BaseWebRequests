package com.example.webrequests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.webrequests.databinding.ActivityMainBinding
import java.net.URLConnection

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.buttonRequest.setOnClickListener {
//            val urlConnection : URLConnection
            Toast.makeText(this,"Hello web!",Toast.LENGTH_LONG).show()

        }
        setContentView(binding.root)
    }

    companion object{
        private const val URL_ADDRESS = "http://google.com"
    }
}