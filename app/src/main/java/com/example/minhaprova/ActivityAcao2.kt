package com.example.minhaprova

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.minhaprova.databinding.ActivityAcao2Binding
import com.example.minhaprova.databinding.ActivityMainBinding

class ActivityAcao2 : AppCompatActivity() {

    lateinit var binding: ActivityAcao2Binding
    var livroBD = LivroDBOpener(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao2)

        binding.apply {

            buttonOk2.setOnClickListener{
               var l = Livro(
                    null,
                        editTextTitulo.toString(),
                        editTextAutor.toString(),
                        Integer.parseInt(editTextAno.text.toString()),
                        ratingBar.rating
                )

                livroBD.insert(l)

                setResult(Activity.RESULT_OK)
                finish()

            }

            buttonCancel.setOnClickListener{
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }

    }
}