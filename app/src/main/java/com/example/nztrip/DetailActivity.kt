package com.example.nztrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nztrip.databinding.ActivityDetailBinding
import com.example.nztrip.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val country = intent.getStringExtra("country")
        val imageId = intent.getIntExtra("imageId", R.drawable.nz_flag)

        binding.textik.text = name
    }
}