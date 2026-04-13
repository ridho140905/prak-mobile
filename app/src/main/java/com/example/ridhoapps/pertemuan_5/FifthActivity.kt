package com.example.ridhoapps.pertemuan_5

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

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Activity Fifth"
            subtitle = "Ini adalah subtitle"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            R.id.action_search -> {
                Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_settings -> {
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}