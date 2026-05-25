package com.example.ridhoapps.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.lifecycleScope
import com.example.ridhoapps.AuthActivity
import com.example.ridhoapps.Home.pertemuan_10.TenthActivity
import com.example.ridhoapps.Home.pertemuan_2.SecondActivity
import com.example.ridhoapps.Home.pertemuan_3.ThirdActivity
import com.example.ridhoapps.Home.pertemuan_4.FourthActivity
import com.example.ridhoapps.Home.pertemuan_5.FifthActivity
import com.example.ridhoapps.Home.pertemuan_7.SeventhActivity
import com.example.ridhoapps.Home.pertemuan_9.NinthActivity
import com.example.ridhoapps.R
import com.example.ridhoapps.data.api.CatFactApiClient
import com.example.ridhoapps.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar2)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }

        // PENTING: Setup Klik Tombol Refresh
        binding.btnRefresh.setOnClickListener {
            loadCatFact()
        }

        // Load fakta pertama kali saat aplikasi dibuka
        loadCatFact()

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.btnlogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { dialog, _ ->
                    sharedPref.edit { clear() }
                    dialog.dismiss()
                    startActivity(Intent(requireContext(), AuthActivity::class.java))
                    requireActivity().finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        // Setup tombol navigasi lainnya
        binding.btnsecond.setOnClickListener {
            startActivity(Intent(requireContext(), SecondActivity::class.java))
        }
        binding.btnthird.setOnClickListener {
            startActivity(Intent(requireContext(), ThirdActivity::class.java))
        }
        binding.btnfourth.setOnClickListener {
            startActivity(Intent(requireContext(), FourthActivity::class.java))
        }
        binding.btnfifth.setOnClickListener {
            startActivity(Intent(requireContext(), FifthActivity::class.java))
        }
        binding.btnseventh.setOnClickListener {
            startActivity(Intent(requireContext(), SeventhActivity::class.java))
        }
        binding.btnninth.setOnClickListener {
            startActivity(Intent(requireContext(), NinthActivity::class.java))
        }
        binding.btnp10.setOnClickListener {
            startActivity(Intent(requireContext(), TenthActivity::class.java))
        }
    }

    // Fungsi loadCatFact diletakkan mandiri di tingkat class
    private fun loadCatFact() {
        lifecycleScope.launch {
            try {
                binding.tvCatFact.text = "Mencari fakta kucing..."
                val response = CatFactApiClient.apiService.getCatFact()
                binding.tvCatFact.text = "\"${response.fact}\""
            } catch (e: Exception) {
                binding.tvCatFact.text = "Gagal mengambil fakta. Cek koneksi internet Anda."
                Log.e("HomeFragment", "Error: ${e.message}")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}