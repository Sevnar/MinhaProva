package com.example.minhaprova

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.minhaprova.databinding.ActivityAcao1Binding
import com.example.minhaprova.databinding.ActivityMainBinding

class ActivityAcao1 : AppCompatActivity() {

    lateinit var binding: ActivityAcao1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao1)

        binding.apply {
            buttonOk.setOnClickListener{

                if(!editText.text.isBlank()){
                    var intent = Intent()
                    intent.putExtra("RESPOSTA", editText.text.toString())
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }

            buttonCancelar.setOnClickListener{
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }
    }
}