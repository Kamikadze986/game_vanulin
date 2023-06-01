package com.example.game.fragments.gamePreset

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.game.R
import com.example.game.databinding.FragmentGameSettingsPresetBinding

class GameSettingsPresetFragment : Fragment() {
    var _binding: FragmentGameSettingsPresetBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = GameSettingsPresetFragment()
    }

    private lateinit var viewModel: GameSettingsPresetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameSettingsPresetBinding.inflate(inflater, container, false)
        binding.button.setOnClickListener {
            val bundle = Bundle()
            try {
                bundle.putInt("st1", binding.editText2.text.toString().toInt())

            } catch (e: Exception) {

            }
            try {
                bundle.putInt("st2", binding.editText3.text.toString().toInt())
            } catch (e: Exception) {

            }
            findNavController().navigate(R.id.gameFragment, bundle)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameSettingsPresetViewModel::class.java)
        // TODO: Use the ViewModel
    }

}