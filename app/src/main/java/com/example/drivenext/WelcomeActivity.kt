package com.example.drivenext

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.drivenext.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var adapter: WelcomePagerAdapter
    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = WelcomePagerAdapter(this)
        binding.viewPager.adapter = adapter

        binding.btnSkip.setOnClickListener { finishOnboarding() }

        binding.btnNext.setOnClickListener {
            if (currentPage < 2) {
                binding.viewPager.setCurrentItem(currentPage + 1, true)
            } else {
                finishOnboarding()
            }
        }

        binding.viewPager.registerOnPageChangeCallback(object :
            androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                currentPage = position
                updateIndicators(position)
                binding.btnNext.text = if (position == 2) getString(R.string.btn_go) else getString(R.string.btn_next)
            }
        })
    }

    private fun updateIndicators(position: Int) {
        val active = ContextCompat.getDrawable(this, R.drawable.indicator_active)
        val inactive = ContextCompat.getDrawable(this, R.drawable.indicator_inactive)
        binding.dot1.background = if (position == 0) active else inactive
        binding.dot2.background = if (position == 1) active else inactive
        binding.dot3.background = if (position == 2) active else inactive
    }

    private fun finishOnboarding() {
        getSharedPreferences("app_prefs", MODE_PRIVATE).edit().putBoolean("is_onboarding_done", true).apply()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}