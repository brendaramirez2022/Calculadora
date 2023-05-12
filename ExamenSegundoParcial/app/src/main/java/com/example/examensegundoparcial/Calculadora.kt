package com.example.examensegundoparcial


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.exp

class Calculadora : AppCompatActivity() {

    lateinit var datatv:TextView
    lateinit var resulttv:TextView
    var lastNumeric = false
    var stateError = false
    var lastDot = false

    private lateinit var expression: Expression
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        resulttv = findViewById(R.id.resulttv1)
        datatv = findViewById(R.id.datatv1)

        // Get the Intent that started this activity and extract the string
        val nombre = intent.getStringExtra(EXTRA_MESSAGE)
        // Capture the layout's TextView and set the string as its text
        val mensaje = "Bienvenid@ "
        val textView = findViewById<TextView>(R.id.txtNombre).apply {
            text = mensaje + nombre
        }

    }

    fun onEqualClick(view: View) {
        onEqual()
        datatv.text = resulttv.text.toString().drop(1)
    }


    fun onDigitClick(view: View) {
        if(stateError){
            datatv.text = (view as Button).text
            stateError = false
        }else{
            datatv.append((view as Button).text)
        }
        lastNumeric = true
        onEqual()
    }


    fun onOperatorClick(view: View) {
        if(!stateError && lastNumeric){
            datatv.append((view as Button).text)
            lastDot = false
            lastNumeric = false
            onEqual()
        }
    }


    fun onBackClick(view: View) {
        datatv.text = datatv.text.toString().dropLast(1)
        try{
            val lastChar = datatv.text.toString().last()
            if(lastChar.isDigit()){
                onEqual()
            }
        }catch (e: Exception){
            resulttv.text =""
            resulttv.visibility = View.GONE
            Log.e("last char error", e.toString())
        }
    }


    fun onClearClick(view: View) {
        datatv.text = ""
        lastNumeric = false
    }


    fun onAllclearClick(view: View) {
        datatv.text = ""
        resulttv.text = ""
        stateError = false
        lastDot = false
        lastNumeric = false
        resulttv.visibility = View.GONE
    }

    fun onEqual(){
        if(lastNumeric && !stateError){
            val txt = datatv.text.toString()
            expression = ExpressionBuilder(txt).build()
            try{
                val result = expression.evaluate()
                resulttv.visibility = View.VISIBLE
                resulttv.text = "=" + result.toString()
            }catch (ex: ArithmeticException){
                Log.e("evaluate error", ex.toString())
                resulttv.text = "Error"
                stateError = true
                lastNumeric = false
            }
        }
    }


}