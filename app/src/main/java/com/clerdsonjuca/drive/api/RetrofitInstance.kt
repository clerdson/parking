package com.clerdsonjuca.drive.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://parking-lot-to-pfz.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api: SimpleApi by lazy {
            retrofit.create(SimpleApi::class.java)
        }

    }
