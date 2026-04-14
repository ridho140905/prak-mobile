package com.example.ridhoapps.pertemuan_5

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ridhoapps.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Web Merdeka"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        binding.webView.settings.javaScriptEnabled = true

        // Agar link yang diklik tetap di dalam aplikasi
        binding.webView.webViewClient = WebViewClient()

        // ========================================================
        // IMPROVISASI VISUAL 1: PROGRESS BAR LOADING
        // ========================================================
        // WebChromeClient digunakan untuk mendeteksi seberapa jauh web sudah dimuat (0-100%)
        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress < 100) {
                    // Jika belum 100%, munculkan garis loading dan update bar-nya
                    binding.progressBar.visibility = View.VISIBLE
                    binding.progressBar.progress = newProgress
                } else {
                    // Jika sudah 100%, sembunyikan garis loadingnya
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        // ========================================================
        // IMPROVISASI VISUAL 2: TOMBOL MENGAMBANG (FAB)
        // ========================================================
        binding.fabHome.setOnClickListener {
            // Ketika tombol bulat diklik, paksa kembali ke halaman awal
            binding.webView.loadUrl("https://merdeka.com")
        }

        // Muat web pertama kali
        binding.webView.loadUrl("https://merdeka.com")

        // Logika sembunyikan Toolbar saat scroll ke bawah
        binding.webView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY) {
                binding.appBar.setExpanded(false, true)
                binding.fabHome.hide() // Sembunyikan FAB biar layar luas
            } else if (scrollY < oldScrollY) {
                binding.appBar.setExpanded(true, true)
                binding.fabHome.show() // Munculkan FAB lagi
            }
        }

        // Logika tombol back
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.webView.canGoBack()) {
                    binding.webView.goBack()
                } else {
                    finish()
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}