package com.example.game.fragments.game

import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.shareIn

class GameViewModel : ViewModel() {
    fun st1Value(progress: Int) = MutableLiveData(progress)

    fun st2Value(progress: Int) = MutableLiveData(progress)
    fun st2ValueFlow(progress: ProgressBar) =
        flowOf(progress.progress).shareIn(viewModelScope, SharingStarted.Eagerly, 1)

}