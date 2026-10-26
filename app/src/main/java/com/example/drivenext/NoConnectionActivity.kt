package com.example.drivenext

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.drivenext.databinding.ActivityNoConnectionBinding

class NoConnectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoConnectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoConnectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRetry.setOnClickListener {
            if (NetworkUtils.isInternetAvailable(this)) {
                val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
                val isOnboardingDone = prefs.getBoolean("is_onboarding_done", false)

                if (isOnboardingDone) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    startActivity(Intent(this, WelcomeActivity::class.java))
                }
                finish()
            } else {
                Toast.makeText(this, "Интернета всё ещё нет!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}