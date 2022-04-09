package com.clerdsonjuca.drive.api

import com.clerdsonjuca.drive.model.Carro
import com.clerdsonjuca.drive.model.Historico
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface SimpleApi {
    @Headers("Content-Type: application/json")
    @POST("parking")
    suspend fun pushPost(@Body post: Carro):Response<Carro>

    @GET("parking/{postNumber}")
    suspend fun  getPost(
        @Path("postNumber")number:String
    ):Response<List<Historico>>

    @POST("parking/{postNumber}/pay")
    suspend fun pushPost2(
        @Path("postNumber")number:String):Response<Boolean>

    @POST("parking/{postNumber}/out")
    suspend fun pushPost3(
        @Path("postNumber")number:String):Response<Boolean>

}