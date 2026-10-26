package com.example.drivenext

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.drivenext.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.postDelayed({
            if (!NetworkUtils.isInternetAvailable(this)) {
                startActivity(Intent(this, NoConnectionActivity::class.java))
            } else {
                val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
                val isOnboardingDone = prefs.getBoolean("is_onboarding_done", false)

                if (isOnboardingDone) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    startActivity(Intent(this, WelcomeActivity::class.java))
                }
            }
            finish()
        }, 2000)
    }
}