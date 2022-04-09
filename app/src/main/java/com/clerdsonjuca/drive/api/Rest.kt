package com.clerdsonjuca.drive.api

import com.clerdsonjuca.drive.model.Carro
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Rest {
    @Headers("Content-Type: application/json")
    @POST("parking")
    fun addUser(@Body plate: Carro): Call<Carro>
}