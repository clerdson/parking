package com.clerdsonjuca.drive.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.clerdsonjuca.drive.*
import com.clerdsonjuca.drive.databinding.FragmentSaidaBinding
import com.clerdsonjuca.drive.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
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
@AndroidEntryPoint
class SaidaFragment : Fragment() {

    private lateinit var binding: FragmentSaidaBinding
    private val viewModel by viewModels <MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_saida, container, false)

        binding= FragmentSaidaBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val repository = Repository()
        //val viewModelFactory = MainViewModelFactory(repository)
//        val buttonPagamento = view.findViewById<Button>(R.id.buttonP)
//        val buttonSaida = view.findViewById<Button>(R.id.buttonS)
//        val buttonSaida2 = view.findViewById<Button>(R.id.buttonS2)
        val phoneNumber =  view.findViewById<EditText>(R.id.editTextTextPersonNameSaida)
        phoneNumber.addTextChangedListener( MaskWatcher("###-###"));

         binding.buttonP.setOnClickListener {

             println("dialog")
            // onCreateDialogP().show()

             //MyCustomDialog().show(parentFragmentManager, "MyCustomFragment")
             showDialogPagamento(editTextTextPersonNameSaida.text.toString())

         }
        binding.buttonS.setOnClickListener {

            println("dialog")
            //onCreateDialogS().show()
           showDialogSaida(editTextTextPersonNameSaida.text.toString())
        }
       binding.buttonS2.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity2::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("placa",editTextTextPersonNameSaida.text.toString())
            startActivity(intent)
        }
        binding.editTextTextPersonNameSaida.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.length == 8) {
                    Toast.makeText(requireContext(),"Pagamento",Toast.LENGTH_SHORT).show()
                     buttonP.setBackgroundColor(Color.parseColor("#A769B2"))


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
    private fun showDialogPagamento(title: String) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog)
        val body = dialog.findViewById(R.id.textView3) as TextView
        body.text = title
        val pagamentoBtn = dialog.findViewById(R.id.buttonPa) as Button
        val saidaBtn = dialog.findViewById(R.id.buttonSa) as TextView
        pagamentoBtn.setOnClickListener {
            progressBar2.visibility = View.VISIBLE
            //val repository = Repository()
            // val viewModelFactory = MainViewModelFactory(repository)
            //viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

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

            dialog.dismiss()
        }
        saidaBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()

    }
    private fun showDialogSaida(title: String) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_saida)
        val body = dialog.findViewById(R.id.textView3) as TextView
        body.text = title
        val pagamentoBtn = dialog.findViewById(R.id.buttonPa) as Button
        val saidaBtn = dialog.findViewById(R.id.buttonSa) as TextView
        pagamentoBtn.setOnClickListener {
            // START THE GAME!
            progressBar2.visibility = View.VISIBLE
            // val repository = Repository()
            // val viewModelFactory = MainViewModelFactory(repository)
            // viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

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

            dialog.dismiss()
        }
        saidaBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()

    }




}


