package com.gastonsaillen.hzone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _averageBpm = MutableStateFlow(0)
    val averageBpm: StateFlow<Int> = _averageBpm

    init {
        viewModelScope.launch {
            for (bpm in bpmValuesList) {
                updateBpmValue(bpm)
                delay(1000)
            }
        }
    }

    private fun updateBpmValue(value: Int) {
        _averageBpm.value = calculateAverageBpm(value, _averageBpm.value)
    }
}