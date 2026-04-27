package com.example.ridhoapps.Home.pertemuan_5

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
            setHomeAsUpIndicator(R.drawable.ic_arrow_back) // Ganti jika icon berbeda
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

        // --- 1. LOGIKA WAJIB UNTUK CHECKBOX & RADIO BUTTON ---
        // Mengubah status centang saat item diklik
        if (item.isCheckable) {
            item.isChecked = !item.isChecked
        }

        // --- 2. MENANGANI AKSI KLIK SETIAP MENU ---
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

            // --- Menangkap klik dari Sub-menu Radio Button ---
            R.id.sort_name -> {
                Toast.makeText(this, "Diurutkan berdasarkan Nama", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.sort_date -> {
                Toast.makeText(this, "Diurutkan berdasarkan Tanggal", Toast.LENGTH_SHORT).show()
                true
            }

            // --- Menangkap klik dari Sub-menu Checkbox ---
            R.id.filter_image -> {
                if (item.isChecked) {
                    Toast.makeText(this, "Gambar Ditampilkan", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Gambar Disembunyikan", Toast.LENGTH_SHORT).show()
                }
                true
            }
            R.id.filter_video -> {
                if (item.isChecked) {
                    Toast.makeText(this, "Video Ditampilkan", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Video Disembunyikan", Toast.LENGTH_SHORT).show()
                }
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