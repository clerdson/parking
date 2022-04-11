package com.clerdsonjuca.drive.ui

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.database.DatabaseUtils
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.clerdsonjuca.drive.*
import com.clerdsonjuca.drive.databinding.ActivityMainBinding
import com.clerdsonjuca.drive.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_app.*
import kotlinx.android.synthetic.main.fragment_saida.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [SaidaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SaidaFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var bindingSignIn: SaidaFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saida, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        val buttonPagamento = view.findViewById<Button>(R.id.buttonP)
        val buttonSaida = view.findViewById<Button>(R.id.buttonS)
        val buttonSaida2 = view.findViewById<Button>(R.id.buttonS2)

         buttonPagamento.setOnClickListener {

             println("dialog")
             onCreateDialogP().show()

         }
        buttonSaida.setOnClickListener {

            println("dialog")
            onCreateDialogS().show()

        }
        buttonSaida2.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity2::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("placa",editTextTextPersonNameSaida.text.toString())
            startActivity(intent)
        }
        editTextTextPersonNameSaida.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.length == 8) {
                    Toast.makeText(requireContext(),"Pagamento",Toast.LENGTH_SHORT).show()
                     buttonP.setBackgroundColor(Color.GREEN)
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


    fun onCreateDialogS(): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Deseja Sair")
                .setPositiveButton("Pagar",
                    DialogInterface.OnClickListener { dialog, id ->
                        // START THE GAME!
                        progressBar2.visibility = View.VISIBLE
                        val repository = Repository()
                        val viewModelFactory = MainViewModelFactory(repository)
                        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

                        viewModel.pushPost3(editTextTextPersonNameSaida.text.toString())
                        viewModel.myResponse4.observe(requireActivity(), Observer { response ->
                            if(response.isSuccessful){

                                Log.d("Main", response.body().toString())
                                Log.d("Main", response.code().toString())
                                Log.d("Main", response.message())
                                Toast.makeText(requireContext(),"certo",Toast.LENGTH_SHORT).show()



                                progressBar2.visibility = View.GONE
                            }else {

                                progressBar2.visibility = View.GONE
                            }
                        })


                    })
                .setNegativeButton("voltar",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                        dialog.cancel()
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
    fun onCreateDialogP(): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Deseja Pagar")
                .setPositiveButton("Pagar",
                    DialogInterface.OnClickListener { dialog, id ->
                        // START THE GAME!
                        progressBar2.visibility = View.VISIBLE
                        val repository = Repository()
                        val viewModelFactory = MainViewModelFactory(repository)
                        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

                        viewModel.pushPost2(editTextTextPersonNameSaida.text.toString())
                        viewModel.myResponse3.observe(requireActivity(), Observer { response ->
                            if(response.isSuccessful){
                                Log.d("Main", response.body().toString())
                                Log.d("Main", response.code().toString())
                                Log.d("Main", response.message())
                                Toast.makeText(requireContext(),"certo",Toast.LENGTH_SHORT).show()


                                progressBar2.visibility = View.GONE

                            }else {

                                progressBar2.visibility = View.GONE
                            }
                        })


                    })
                .setNegativeButton("voltar",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                        dialog.cancel()
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


}


