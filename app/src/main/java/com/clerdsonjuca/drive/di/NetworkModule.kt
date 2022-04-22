package com.clerdsonjuca.drive.di

import android.app.Application
import android.provider.SyncStateContract
import androidx.room.Room
import com.clerdsonjuca.drive.Util.Constants
import com.clerdsonjuca.drive.api.SimpleApi
import com.clerdsonjuca.drive.model.HistoricoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient():OkHttpClient{
        return OkHttpClient
            .Builder()
            .readTimeout(15,TimeUnit.SECONDS)
            .connectTimeout(15,TimeUnit.SECONDS)
            .build()
    }
    @Singleton
    @Provides
    fun providesConvertFactory():GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun providerRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ):Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
    @Singleton
    @Provides
    fun providesCurrentService(retrofit: Retrofit):SimpleApi =
        retrofit.create(SimpleApi::class.java)

    @Singleton
    @Provides
    fun provideDatabase(app:Application):HistoricoDatabase =
        Room.databaseBuilder(app,HistoricoDatabase::class.java,"historico")
            .build()
}