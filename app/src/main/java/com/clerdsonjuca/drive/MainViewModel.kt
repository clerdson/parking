package com.clerdsonjuca.drive

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clerdsonjuca.drive.model.Carro
import com.clerdsonjuca.drive.model.Historico
import com.clerdsonjuca.drive.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    var myResponse: MutableLiveData<Response<Carro>> = MutableLiveData()
    var myResponse2: MutableLiveData<Response<List<Historico>>> = MutableLiveData()
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