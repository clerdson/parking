package com.clerdsonjuca.drive.repository


import androidx.room.withTransaction
import com.clerdsonjuca.drive.BaseApiResponse

import com.clerdsonjuca.drive.Resource
import com.clerdsonjuca.drive.Util.NetworkBoundResorces
import com.clerdsonjuca.drive.api.RetrofitInstance
import com.clerdsonjuca.drive.api.SimpleApi
import com.clerdsonjuca.drive.model.Carro
import com.clerdsonjuca.drive.model.Historico
import com.clerdsonjuca.drive.model.HistoricoDatabase

import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
@ActivityRetainedScoped
class Repository @Inject constructor(
    private val simpleApi: SimpleApi
    ,private val db: HistoricoDatabase

    ):BaseApiResponse() {
    private val historicoDao = db.HistoricoDao()

    fun fgets(number: String) = NetworkBoundResorces(
        query = {
            historicoDao.getAllHistorico()
        },
        fetch = {
            delay(2000)
            simpleApi.getPost(number)
        },
        saveFetchResult = { historicos ->
            db.withTransaction {
                historicoDao.delete()
                historicoDao.insert(historicos)
            }
        }
    )


    suspend fun getPushFlow(post: Carro): Flow<Resource<Carro>> {
        return flow<Resource<Carro>> {
        emit(safeApiCall {
            simpleApi.pushPostF(post)
        })
        }.flowOn(Dispatchers.IO)
    }




    suspend fun pushPost(post: Carro): Response<Carro> {
        return RetrofitInstance.api.pushPost(post)
    }
    suspend fun getPost(number:String): List<Historico> {
        return simpleApi.getPost(number)
    }
    suspend fun pushPost2(number: String):Response<Boolean> {
        return RetrofitInstance.api.pushPost2(number)
    }
    suspend fun pushPost3(number: String):Response<Boolean> {
        return RetrofitInstance.api.pushPost3(number)
    }

}