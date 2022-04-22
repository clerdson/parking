package com.clerdsonjuca.drive.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.clerdsonjuca.drive.MyAdapter
//import com.clerdsonjuca.drive.MyAdapter
import com.clerdsonjuca.drive.viewModel.MainViewModel

import com.clerdsonjuca.drive.R
import com.clerdsonjuca.drive.Resource
//import com.clerdsonjuca.drive.RestaurantAdapter

import com.clerdsonjuca.drive.databinding.ActivityMain2Binding
import com.clerdsonjuca.drive.model.Historico
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class MainActivity2 : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    //private val myAdapter by lazy { RestaurantAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val profileName = intent.getStringExtra("placa")
        supportActionBar!!.setTitle(profileName)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.CYAN))
        supportActionBar!!.setDisplayShowHomeEnabled(true);
        supportActionBar!!.setLogo(R.drawable.logo_parking)
        supportActionBar!!.setDisplayUseLogoEnabled(true)

        //setupRecyclerview()

        val historicoAdapter = MyAdapter()
        binding.apply {
            recyclerView.apply {
                adapter = historicoAdapter
                layoutManager = LinearLayoutManager(this@MainActivity2)
            }

            viewModel.fgets(profileName.toString())
            viewModel._historicos.observe(this@MainActivity2) { result ->

                result.data?.let { historicoAdapter.setData(it) }

                 //restaurantAdapter.submitList(result.value?.data)

                //restaurantAdapter.submitList(result)

//                result.map {json->
//
//                restaurantAdapter.setData(json.data!!)


                //}



            }
        }
    }
}


     //   viewModel.getPost(profileName.toString())
      //  viewModel.myResponseHistorico.observe(this) { response ->

//
//            if (response.isSuccessful) {
//                placa1.text = profileName
//                Log.d("Main", response.body().toString())
//                Log.d("Main", response.code().toString())
//                Log.d("Main", response.message())
//                response.body()?.let { myAdapter.setData(it) }
//
//
//                // onCreateDialog(response.body().toString()).show()

//
//                progressBar3.visibility = View.GONE
//
//            } else {
//                Toast.makeText(this, "erro", Toast.LENGTH_SHORT).show()
//                progressBar3.visibility = View.GONE
//            }
//        }





    /*
       val repository = Repository()
        progressBar3.visibility = View.VISIBLE
        imageButtonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val viewModelFactory = MainViewModelFactory(repository)
        onCreateDialog(profileName.toString()).show()
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost(profileName.toString())
        viewModel.myResponse2.observe(this){ response ->
            if(response.isSuccessful){
                placa1.text = profileName
                Log.d("Main", response.body().toString())
                Log.d("Main", response.code().toString())
                Log.d("Main", response.message())
                response.body()?.let { myAdapter.setData(it) }
                Toast.makeText(this,"certo",Toast.LENGTH_SHORT).show()

                // onCreateDialog(response.body().toString()).show()


                progressBar3.visibility = View.GONE

            }else {
                Toast.makeText(this,"erro",Toast.LENGTH_SHORT).show()
                progressBar3.visibility = View.GONE
            }
        })
*/

//    private fun setupRecyclerview() {
//        recyclerView.adapter = myAdapter
//        recyclerView.layoutManager = LinearLayoutManager(this)
//    }
//    fun onCreateDialog(response:String): Dialog {
//        return this@MainActivity2?.let {
//            // Use the Builder class for convenient dialog construction
//            val builder = AlertDialog.Builder(it)
//            builder.setMessage(response)
//                .setPositiveButton(
//                    "ok",
//                    DialogInterface.OnClickListener {dialog, which ->
//                        dialog.cancel()
//                    }
//                )
//            // Create the AlertDialog object and return it
//            builder.create()
//        } ?: throw IllegalStateException("Activity cannot be null")
//    }
//}