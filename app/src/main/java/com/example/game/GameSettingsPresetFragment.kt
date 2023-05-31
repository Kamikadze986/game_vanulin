package com.example.game

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class GameSettingsPresetFragment : Fragment() {

    companion object {
        fun newInstance() = GameSettingsPresetFragment()
    }

    private lateinit var viewModel: GameSettingsPresetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_settings_preset, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameSettingsPresetViewModel::class.java)
        // TODO: Use the ViewModel
    }

}