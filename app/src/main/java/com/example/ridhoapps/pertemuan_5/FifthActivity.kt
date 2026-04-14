package com.example.ridhoapps.pertemuan_5

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ridhoapps.R
import com.example.ridhoapps.databinding.ActivityFifthBinding

class FifthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFifthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFifthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengaktifkan Toolbar custom
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            // Pengaturan title dan subtitle ditiadakan di sini karena
            // sudah diatur otomatis oleh CollapsingToolbarLayout di XML
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        // Aksi ketika tombol WebView ditekan
        binding.btnWebView.setOnClickListener {
            // Berpindah dari FifthActivity ke WebViewActivity
            val intentPindah = Intent(this@FifthActivity, WebViewActivity::class.java)
            startActivity(intentPindah)
        }
    }

    // Menampilkan menu di kanan atas Toolbar (Search & Settings)
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // Mengatur aksi ketika tombol/menu di Toolbar ditekan
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // Jika tombol panah kiri (back) ditekan
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            // Jika icon search ditekan
            R.id.action_search -> {
                Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            // Jika icon settings ditekan
            R.id.action_settings -> {
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}