package com.example.ridhoapps

import com.example.ridhoapps.Message.MessageFragment
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.ridhoapps.Home.HomeFragment
import com.example.ridhoapps.More.MoreFragment
import com.example.ridhoapps.Note.NotesFragment
import com.example.ridhoapps.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityBaseBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        // Menampilkan HomeFragment secara default saat aplikasi dibuka
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
            binding.bottomNavView.selectedItemId = R.id.home
        }

        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.message -> {
                   replaceFragment(MessageFragment())
                    true
                }
                R.id.more -> {
                    replaceFragment(MoreFragment())
                    true
                }
                R.id.notes -> {
                    replaceFragment(NotesFragment())
                    true
                }
                
                else -> false // return false jika item tidak ada yang di klik
            }
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            //.addToBackStack(null) -> ini kita nonaktifkan agar saat back langsung keluar aplikasi
            .commit()
    }
}