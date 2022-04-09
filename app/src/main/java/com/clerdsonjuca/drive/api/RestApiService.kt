package com.clerdsonjuca.drive.api

import com.clerdsonjuca.drive.model.Carro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RestApiService {
    fun addUser(plate: Carro, onResult: (Carro?) -> Unit){
        val retrofit = ServiceBuilder.buildService(Rest::class.java)
        retrofit.addUser(plate).enqueue(
            object : Callback<Carro> {
                override fun onFailure(call: Call<Carro>, t: Throwable) {
                    println(t)
                }
                override fun onResponse(call: Call<Carro>, response: Response<Carro>) {
                    val addedUser = response.body()
                   println(response.body());
                    onResult(addedUser)
                }
            }
        )
    }
}