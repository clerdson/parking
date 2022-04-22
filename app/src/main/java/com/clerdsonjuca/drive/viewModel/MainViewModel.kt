package com.clerdsonjuca.drive.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.clerdsonjuca.drive.Resource
import com.clerdsonjuca.drive.model.Carro
import com.clerdsonjuca.drive.model.Historico
import com.clerdsonjuca.drive.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application,

):
    AndroidViewModel(application) {

    var myResponse: MutableLiveData<Response<Carro>> = MutableLiveData()
    var myResponse2: MutableLiveData <List<Historico>> = MutableLiveData()
    var myResponse3: MutableLiveData<Response<Boolean>> = MutableLiveData()
    var myResponse4: MutableLiveData<Response<Boolean>> = MutableLiveData()





    //var _historicos:LiveData<Resource<List<Historico>>> = MutableLiveData()


//    fun fgets(number: String) {
//        _historicos= repository.fgets(number).asLiveData()
//
//    }


    private val _response: MutableLiveData<Resource<List<Historico>>> = MutableLiveData()
    val response: LiveData<Resource<List<Historico>>> =_response
    fun fgets(number: String) = viewModelScope.launch {
        repository.fgets(number).collect {
             _response.value= it
        }
    }






    private val _response2: MutableLiveData<Resource<Carro>> = MutableLiveData()
    val response2: LiveData<Resource<Carro>> = _response2
    fun fgets2(post: Carro) = viewModelScope.launch {
        repository.getPushFlow(post =post ).collect {
            _response2.value= it
        }
    }



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