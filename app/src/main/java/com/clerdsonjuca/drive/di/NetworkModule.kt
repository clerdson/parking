package com.clerdsonjuca.drive.di

import android.provider.SyncStateContract
import com.clerdsonjuca.drive.Util.Constants
import com.clerdsonjuca.drive.api.SimpleApi
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
}