package com.example.nztrip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewTreeObserver
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    var isAndroidReady = false
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
        setContentView(R.layout.activity_main)
    }

    private fun dismissSplashScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            isAndroidReady = true
        }, 3000)
    }

}