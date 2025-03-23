package com.example.blazelivebot

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var streamingManager: StreamingManager
    private var rtmpUrl: String? = null
    private var streamKey: String? = null
    private var quality: String = "720p"
    private var frameRate: Int = 30
    private var bitrate: Int = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        streamingManager = StreamingManager(this)
        findViewById<Button>(R.id.btn_start_stream).setOnClickListener { showRtmpDialog() }
        findViewById<Button>(R.id.btn_settings).setOnClickListener {
            startActivity(android.content.Intent(this, SettingsActivity::class.java))
        }

        // Overlays
        findViewById<TextView>(R.id.text_overlay).text = "Live Now!"
    }

    private fun showRtmpDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_rtmp_input, null)
        val etRtmpUrl = dialogView.findViewById<EditText>(R.id.et_rtmp_url)
        val etStreamKey = dialogView.findViewById<EditText>(R.id.et_stream_key)

        AlertDialog.Builder(this)
            .setTitle("Enter RTMP Details")
            .setView(dialogView)
            .setPositiveButton("Start Streaming") { _, _ ->
                rtmpUrl = etRtmpUrl.text.toString()
                streamKey = etStreamKey.text.toString()
                if (rtmpUrl.isNullOrEmpty() || streamKey.isNullOrEmpty()) {
                    Toast.makeText(this, "Please enter both RTMP URL and Stream Key", Toast.LENGTH_SHORT).show()
                } else {
                    startStreaming()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun startStreaming() {
        streamingManager.startStreaming("$rtmpUrl/$streamKey", quality, frameRate, bitrate)
    }
}
