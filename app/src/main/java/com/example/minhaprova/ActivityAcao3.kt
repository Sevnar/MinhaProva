package com.example.minhaprova

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.minhaprova.databinding.ActivityAcao3Binding
import com.example.minhaprova.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception

class ActivityAcao3 : AppCompatActivity() {

    lateinit var binding: ActivityAcao3Binding
    var livroBD = LivroDBOpener(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao3)

        var count = 1
        var livros = livroBD.findAll()
        try {


            binding.apply {
                var l = livroBD.findById(count)


                textViewTitulo.text = l.nome
                textViewAutor.text = l.autor
                textViewAno.text = l.ano.toString()
                textViewNota.text = l.nota.toString()
                Log.i("Error", l.nome)

                if(livros.size <= count)
                    buttonProx.visibility = View.INVISIBLE
                buttonAnt.visibility = View.INVISIBLE


                buttonProx.setOnClickListener {
                    count++
                    if (count >= livros.size)
                        buttonProx.visibility = View.INVISIBLE
                    if (count > 1)
                        buttonAnt.visibility = View.VISIBLE

                    l = livroBD.findById(count)
                    textViewTitulo.text = l.nome
                    textViewAutor.text = l.autor
                    textViewAno.text = l.ano.toString()
                    textViewNota.text = l.nota.toString()

                }

                buttonAnt.setOnClickListener {
                    count--
                    if (count < livros.size)
                        buttonProx.visibility = View.VISIBLE
                    if (count == 1)
                        buttonAnt.visibility = View.INVISIBLE

                    livroBD.findAll()
                    l = livroBD.findById(count)
                    textViewTitulo.text = l.nome
                    textViewAutor.text = l.autor
                    textViewAno.text = l.ano.toString()
                    textViewNota.text = l.nota.toString()

                }
            }
        }catch(e:Exception){
            binding.buttonProx.visibility = View.INVISIBLE
            binding.buttonAnt.visibility = View.INVISIBLE
            Snackbar.make(binding.textViewTitulo, "O banco está vazio", Snackbar.LENGTH_INDEFINITE).setAction("Voltar"){
                finish()
            }.show()
        }
    }
}