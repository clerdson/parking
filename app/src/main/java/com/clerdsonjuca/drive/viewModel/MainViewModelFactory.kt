package com.clerdsonjuca.drive.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clerdsonjuca.drive.repository.Repository



class MainViewModelFactory(
    private val repository: Repository,
    private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository,application) as T
    }
}