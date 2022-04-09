package com.clerdsonjuca.drive.api

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import com.clerdsonjuca.drive.model.Carro

fun add(context: Context,editText: EditText) {
    var apiService = RestApiService()
    val userInfo = Carro(
        plate = editText.text.toString())
     apiService.addUser(userInfo){
         if (it?.plate!= null) {
             Toast.makeText(context,"seguinte carro add ${it.plate}",Toast.LENGTH_SHORT).show()
             // it = newly added user parsed as response
             // it?.id = newly added user ID
         } else {
             Toast.makeText(context,"erro",Toast.LENGTH_SHORT).show()
         }

     }
}