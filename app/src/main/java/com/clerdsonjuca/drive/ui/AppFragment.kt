package com.clerdsonjuca.drive.ui

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.clerdsonjuca.drive.MainViewModel
import com.clerdsonjuca.drive.MainViewModelFactory
import com.clerdsonjuca.drive.R
import com.clerdsonjuca.drive.api.add
import com.clerdsonjuca.drive.model.Carro
import com.clerdsonjuca.drive.repository.Repository
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_app.*
import kotlinx.android.synthetic.main.fragment_saida.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [AppFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AppFragment : Fragment() {
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_app, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonEntrada.setOnClickListener {
            //add(requireContext(),editTextTextPersonNameEntrada)
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
            println("ok")
        }


        editTextTextPersonNameEntrada.addTextChangedListener(object : TextWatcher {
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

    }
