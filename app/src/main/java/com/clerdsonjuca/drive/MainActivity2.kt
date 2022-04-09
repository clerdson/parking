package com.clerdsonjuca.drive

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.clerdsonjuca.drive.repository.Repository
import com.clerdsonjuca.drive.ui.SaidaFragment
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.fragment_saida.*

class MainActivity2 : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private val myAdapter by lazy { MyAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val profileName = intent.getStringExtra("placa")
        supportActionBar!!.setTitle(profileName)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.CYAN))
        supportActionBar!!.setDisplayShowHomeEnabled(true);
        supportActionBar!!.setLogo(R.drawable.logo_parking)
        supportActionBar!!.setDisplayUseLogoEnabled(true)

        setupRecyclerview()
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        //onCreateDialog(profileName.toString()).show()
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost(profileName.toString())
        viewModel.myResponse2.observe(this, Observer { response ->
            if(response.isSuccessful){
                Log.d("Main", response.body().toString())
                Log.d("Main", response.code().toString())
                Log.d("Main", response.message())
                response.body()?.let { myAdapter.setData(it) }
                Toast.makeText(this,"certo",Toast.LENGTH_SHORT).show()

                // onCreateDialog(response.body().toString()).show()



            }else {
                Toast.makeText(this,"erro",Toast.LENGTH_SHORT).show()

            }
        })

    }
    private fun setupRecyclerview() {
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
    fun onCreateDialog(response:String): Dialog {
        return this?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(response)
                .setPositiveButton(
                    "ok",
                    DialogInterface.OnClickListener {dialog, which ->
                        dialog.cancel()
                    }
                )
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}