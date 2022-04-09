package com.clerdsonjuca.drive.repository


import com.clerdsonjuca.drive.api.RetrofitInstance
import com.clerdsonjuca.drive.model.Carro
import com.clerdsonjuca.drive.model.Historico
import retrofit2.Response
class Repository {
    suspend fun pushPost(post: Carro): Response<Carro> {
        return RetrofitInstance.api.pushPost(post)
    }
    suspend fun getPost(number:String): Response<List<Historico>> {
        return RetrofitInstance.api.getPost(number)
    }
    suspend fun pushPost2(number: String):Response<Boolean> {
        return RetrofitInstance.api.pushPost2(number)
    }
    suspend fun pushPost3(number: String):Response<Boolean> {
        return RetrofitInstance.api.pushPost3(number)
    }
}