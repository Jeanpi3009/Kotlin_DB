package com.example.app_bd

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var id:EditText
    lateinit var nombre:EditText
    lateinit var cantidad:EditText
    lateinit var lista:TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        id = findViewById(R.id.id)
        nombre = findViewById(R.id.nombre)
        cantidad = findViewById(R.id.cantidad)
    }

    fun GuardarDatos(view:View){
        var db = Base_DT(this)
        var datos = Datos()

        datos.nombre = nombre.text.toString()
        datos.cantidad = cantidad.text.toString().toInt()

        var mensaje = db.guardarDatos(datos)
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()




    }
}