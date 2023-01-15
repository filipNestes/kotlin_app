package com.example.nztrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nztrip.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true) //back button

        val name = intent.getStringExtra("name")
        val imageId = intent.getIntExtra("imageId", R.drawable.auckland)
        val placeDescription = intent.getStringExtra("detailContent")

        binding.detailTitle.text = name
        binding.placeImage.setImageResource(imageId)
        binding.detailContent.text = placeDescription
    }
}