package com.clerdsonjuca.drive.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clerdsonjuca.drive.model.Carro
import com.clerdsonjuca.drive.model.Historico
import com.clerdsonjuca.drive.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application):
    AndroidViewModel(application) {

    var myResponse: MutableLiveData<Response<Carro>> = MutableLiveData()
    var myResponse2: MutableLiveData <Response<List<Historico>>> = MutableLiveData()
    var myResponse3: MutableLiveData<Response<Boolean>> = MutableLiveData()
    var myResponse4: MutableLiveData<Response<Boolean>> = MutableLiveData()


    fun pushPost(post: Carro) {
        viewModelScope.launch {
            val response = repository.pushPost(post)
            myResponse.postValue(response)
        }
    }

    fun getPost(number: String) {
        viewModelScope.launch {
            val response = repository.getPost(number)
            myResponse2.value = response

        }
    }
    fun pushPost2(number: String) {
        viewModelScope.launch {
            val response = repository.pushPost2(number)
            myResponse3.value = response

        }
    }
    fun pushPost3(number: String) {
        viewModelScope.launch {
            val response = repository.pushPost3(number)
            myResponse4.value = response

        }

}}