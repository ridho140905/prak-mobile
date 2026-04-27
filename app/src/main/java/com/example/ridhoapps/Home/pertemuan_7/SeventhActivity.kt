package com.example.ridhoapps.Home.pertemuan_7

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.ridhoapps.R
import com.example.ridhoapps.databinding.ActivitySeventhBinding

class SeventhActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySeventhBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeventhBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        // Mengaktifkan Toolbar custom
        setSupportActionBar(binding.toolbar2)
        supportActionBar?.apply {
            // Pengaturan title dan subtitle ditiadakan di sini karena
            // sudah diatur otomatis oleh CollapsingToolbarLayout di XML
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back) // Ganti jika icon berbeda
        }
        replaceFragment(SatuFragment())
            binding.btnfragment1.setOnClickListener {
                replaceFragment(SatuFragment())
            }
            binding.btnfragment2.setOnClickListener {
                replaceFragment(DuaFragment())
            }
            binding.btfragment3.setOnClickListener {
                replaceFragment(TigaFragment())
            }
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

}