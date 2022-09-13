package com.fahamin.unplashapi_jetpack.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.fahamin.unplashapi_jetpack.R
import com.fahamin.unplashapi_jetpack.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMain.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        })


        binding.btnPerPage.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        })
    }
}