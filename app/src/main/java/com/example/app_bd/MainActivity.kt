package com.example.app_bd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
class MainActivity : AppCompatActivity() {
    // definimos las variables
    lateinit var nombre:EditText
    lateinit var edad:EditText
    lateinit var codigo:EditText
    lateinit var listaD:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nombre=findViewById(R.id.txtnombre)
        codigo=findViewById(R.id.txtid)
        edad=findViewById(R.id.txtid)
        listaD=findViewById(R.id.txtlista)
    }
    fun GuardarDatos(view: View){
        var db=BasesDatos(this)
        var usu=Usuario()
        if(nombre.text.toString().length>0 && edad.text.toString().length>0){
            usu.nombre=nombre.text.toString()
            usu.edad=edad.text.toString().toInt()
            var mensaje =db.insertarDatos(usu)
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }
    }
    fun ListaDatos(view: View){
        listaD.text=""
        var bd=BasesDatos(this)
        if (codigo.text.toString().length>0){
            var datosL=bd.listarDatos()
            for(i in 0..datosL.size-1){
                listaD.append("codigo "+datosL.get(i).id+"nombre"+datosL.get(i).nombre+"edad"+datosL.get(i).edad.toString()+"\n")
            }
        }
    }
    fun borrarDatos(view: View){
        var bd =BasesDatos(this)
        if(codigo.text.toString().length>0){
            bd.borrar(codigo.text.toString())
        }
    }
}
