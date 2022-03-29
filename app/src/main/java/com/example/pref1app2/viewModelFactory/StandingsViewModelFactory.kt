package com.example.pref1app2.viewModelFactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pref1app2.Fragments.StandingsViewModel
import java.lang.IllegalArgumentException

class StandingsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StandingsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StandingsViewModel(context = context) as T
        }
        throw IllegalArgumentException("INVALID VIEW MODEL")
    }
}