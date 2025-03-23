package com.example.blazelivebot

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val qualitySpinner = findViewById<Spinner>(R.id.spinner_quality)
        val frameRateSpinner = findViewById<Spinner>(R.id.spinner_frame_rate)
        val etBitrate = findViewById<EditText>(R.id.et_bitrate)
        val etSubGoal = findViewById<EditText>(R.id.et_sub_goal)

        // Quality Dropdown
        val qualities = arrayOf("720p", "1080p", "1440p")
        qualitySpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, qualities)

        // Frame Rate Dropdown
        val frameRates = arrayOf("30fps", "60fps")
        frameRateSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, frameRates)

        findViewById<Button>(R.id.btn_save).setOnClickListener {
            // Save settings logic
            finish()
        }
    }
}
