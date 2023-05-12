package com.example.examensegundoparcial

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.Theme_ExamenSegundoParcial)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        validar()
    }
    fun validar():Boolean{
        lateinit var nombre:EditText
        nombre = findViewById(R.id.txtNombre)
        if(nombre.text.isEmpty()){
            Toast.makeText( this, "Escribe tu nombre", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
    fun sendText(view: View) {
        if(validar()){
            val editText = findViewById<EditText>(R.id.txtNombre)
            val nombre = editText.text.toString()
            val intent = Intent(this, Calculadora::class.java).apply {
                putExtra(EXTRA_MESSAGE, nombre)
            }
            startActivity(intent)
        }
    }
}