package com.example.nztrip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowInsetsAnimationController
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.nztrip.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var isAndroidReady = false
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                if (isAndroidReady) {
                    content.viewTreeObserver.removeOnPreDrawListener(this)
                }
                dismissSplashScreen()
                return false
            }
        })
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
  //      setContentView(R.layout.activity_main)

        navController= Navigation.findNavController(this, R.id.activity_main_nav_host_fragment)
        setupWithNavController(binding.bottomNavigationView, navController)

    }

    private fun dismissSplashScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            isAndroidReady = true
        }, 3000)
    }

}