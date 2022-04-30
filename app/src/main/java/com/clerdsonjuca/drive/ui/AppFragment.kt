package com.clerdsonjuca.drive.ui

import android.database.DatabaseUtils
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.adapters.ViewBindingAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager

import com.clerdsonjuca.drive.viewModel.MainViewModel
import com.clerdsonjuca.drive.viewModel.MainViewModelFactory
import com.clerdsonjuca.drive.R
import com.clerdsonjuca.drive.Resource
import com.clerdsonjuca.drive.databinding.ActivityMain2Binding.inflate
import com.clerdsonjuca.drive.databinding.ActivityMainBinding
import com.clerdsonjuca.drive.databinding.FragmentAppBinding
import com.clerdsonjuca.drive.model.Carro
import com.clerdsonjuca.drive.repository.Repository
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_app.*
import kotlinx.android.synthetic.main.fragment_saida.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [AppFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class AppFragment : Fragment() {
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    private lateinit var binding:FragmentAppBinding

    private val viewModel by viewModels <MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_app, container, false)
          binding = FragmentAppBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.buttonEntrada.setOnClickListener {

            var carro = Carro(editTextTextPersonNameEntrada.text.toString())
            viewModel.fgets2(carro)
            viewModel.response2.observe(requireActivity(), Observer {
                when(it){
                    is Resource.Success->{
                           Toast.makeText(requireContext(),"ok",Toast.LENGTH_SHORT).show()

                    }
                    is Resource.Loading->{
                        binding.progressBar.visibility = View.VISIBLE

                    }
                    is Resource.Error->{
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(),"error",Toast.LENGTH_SHORT).show()
                    }

                }
            })

            //add(requireContext(),editTextTextPersonNameEntrada)

//            var carro = Carro(editTextTextPersonNameEntrada.text.toString())
//
//
//            viewModel.pushPost(carro)
//            viewModel.myResponse.observe(requireActivity(), Observer { response ->
//                if(response.isSuccessful){
//                    Log.d("Main", response.body().toString())
//                    Log.d("Main", response.code().toString())
//                    Log.d("Main", response.message())
//
//                    progressBar.visibility = View.GONE
//                }else {
//                    Toast.makeText(requireContext(),response.message().toString(),Toast.LENGTH_SHORT).show()
//                    progressBar.visibility = View.GONE
//                }
//            })

/*
            progressBar.visibility = View.VISIBLE
            val repository = Repository()
            val viewModelFactory = MainViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
            var carro = Carro(editTextTextPersonNameEntrada.text.toString())


            viewModel.pushPost(carro)

            viewModel.myResponse.observe(requireActivity(), Observer { response ->
                if(response.isSuccessful){
                    Log.d("Main", response.body().toString())
                    Log.d("Main", response.code().toString())
                    Log.d("Main", response.message())

                    progressBar.visibility = View.GONE
                }else {
                    Toast.makeText(requireContext(),response.message().toString(),Toast.LENGTH_SHORT).show()
                    progressBar.visibility = View.GONE
                }
            })
 */           println("ok")
        }


        binding.editTextTextPersonNameEntrada.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.length == 8) {
                    Toast.makeText(requireContext(),"Pagamento", Toast.LENGTH_SHORT).show()
                    buttonEntrada.setBackgroundColor(Color.GREEN)
                    //Apaga o conteudo
                    //Aqui faça o que pretende ou chame um método da sua Activity
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
            }
        })

    }
fun fetchData(){

}
    }
