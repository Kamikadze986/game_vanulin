package com.example.game.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.game.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val pref = SettingsPlaylistImpl.getSettingsPlaylist(requireContext())
        when (pref.getComplexity()) {
            Complexity.HARD -> {
                binding.box1.isChecked = true
            }

            Complexity.MIDLE -> {
                binding.box2.isChecked = true
            }

            Complexity.EASE -> {
                binding.box3.isChecked = true
            }
        }
        binding.box1.setOnClickListener {
            pref.setComplexity(Complexity.HARD)
            binding.box2.isChecked = false
            binding.box3.isChecked = false

        }
        binding.box2.setOnClickListener {
            pref.setComplexity(Complexity.MIDLE)
            binding.box1.isChecked = false
            binding.box3.isChecked = false

        }
        binding.box3.setOnClickListener {

            pref.setComplexity(Complexity.EASE)
            binding.box2.isChecked = false
            binding.box1.isChecked = false

        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[SettingsViewModel::class.java]
    }

}