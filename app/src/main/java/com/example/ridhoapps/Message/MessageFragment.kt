package com.example.ridhoapps.Message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ridhoapps.R
import com.example.ridhoapps.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    private val messageList = listOf(
        MessageModel("Alya", "Halo! Apa kabar?", R.drawable.avatar_perempuan),
        MessageModel("Budi", "Sudah makan?", R.drawable.avatar_laki),
        MessageModel("Citra", "Jangan lupa tugasnya ya!", R.drawable.avatar_perempuan),
        MessageModel("Dika", "Besok kita rapat jam 9", R.drawable.avatar_laki),
        MessageModel("Eka", "Nice job kemarin!", R.drawable.avatar_perempuan),
        MessageModel("Fajar", "Lagi ngapain?", R.drawable.avatar_laki),
        MessageModel("Gita", "Boleh minta tolong?", R.drawable.avatar_perempuan),
        MessageModel("Hana", "Lihat email ya", R.drawable.avatar_perempuan),
        MessageModel("Irfan", "Oke noted", R.drawable.avatar_laki),
        MessageModel("Joko", "Sampai jumpa besok", R.drawable.avatar_laki)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Message"
        }

        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
