package com.example.pref1app2.viewModelFactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pref1app2.Fragments.TeamsViewModel

class TeamsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TeamsViewModel(context = context) as T
        }
        throw IllegalArgumentException("INVALID VIEW MODEL")
    }
}