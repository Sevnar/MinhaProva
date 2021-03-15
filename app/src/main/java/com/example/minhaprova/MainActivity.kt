package com.example.minhaprova

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.minhaprova.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var bemvindo = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val settings = getSharedPreferences("prefereces", Context.MODE_PRIVATE)
        bemvindo = settings.getInt("bemvindo", 0)

        if(bemvindo == 0){
            Toast.makeText(this, "Seja bem-vindo!", Toast.LENGTH_LONG).show()
            bemvindo = 1
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            button1.setOnClickListener{
                intent = Intent(this@MainActivity, ActivityAcao1::class.java)
                val requestCode = 1
                startActivityForResult(intent, requestCode)
            }

            button2.setOnClickListener{
                val dialog = SendMessageDialogFragment()
                dialog.show(supportFragmentManager,"Dialog")

            }

            button3.setOnClickListener{
                intent = Intent(this@MainActivity, ActivityAcao2::class.java)
                val requestCode = 2
                startActivityForResult(intent, requestCode)
            }

            button4.setOnClickListener{
                intent = Intent(this@MainActivity, ActivityAcao3::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1 -> {
                when(resultCode) {
                    Activity.RESULT_OK -> {
                        binding.textView1.text = data?.getStringExtra("RESPOSTA").toString()
                    }
                    Activity.RESULT_CANCELED -> {
                        Snackbar.make(binding.button2, "Cancelado", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
            2 -> {
                when(resultCode){
                    Activity.RESULT_OK -> {
                        binding.textView2.text = "Cadastrado"
                    }
                    Activity.RESULT_CANCELED ->{
                        Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("textView1", binding.textView1.text.toString())

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        binding.textView1.text = savedInstanceState.getString("textView1")
    }

    override fun onStop() {
        super.onStop()

        val settings = getSharedPreferences("prefereces", Context.MODE_PRIVATE)
        var editor = settings.edit()

        editor.putInt("bemvindo", bemvindo)

        editor.commit()
    }
}