package com.clerdsonjuca.drive

import android.app.Application
import com.clerdsonjuca.drive.api.SimpleApi
import com.clerdsonjuca.drive.model.HistoricoDatabase
import com.clerdsonjuca.drive.repository.Repository
import com.clerdsonjuca.drive.viewModel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

import org.junit.After

import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class MainViewModelTest{

    private lateinit var viewModel: MainViewModel
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private lateinit var simpleApi:SimpleApi
    private lateinit var db:HistoricoDatabase
    private lateinit var application: Application
    var api2 = Repository(simpleApi,db)
    @Before
    fun setup(){

        Dispatchers.setMain(mainThreadSurrogate)

            viewModel = MainViewModel(repository = Repository(simpleApi, db), application)

    }
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }
    @Test
    fun `test`(){

        val value = viewModel.getPost("AAA-0000")

        assert(value != null)

    }
    @Test
    fun `get all post`(){

        val value = viewModel.pushPost3("AAA-0000")

        assert(value != null)

    }
}