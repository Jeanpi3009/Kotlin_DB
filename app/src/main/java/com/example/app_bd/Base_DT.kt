package com.example.app_bd

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class BasesDatos(contexto:Context):SQLiteOpenHelper(contexto,"BD",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var sql="CREATE TABLE usuario (id INTEGER PRIMARY KEY AUTOINCREMENT,nombre VARCHAR(250),edad INTEGER)"
        db?.execSQL(sql)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun borrar(id: String){
        if(id.length>0){
            if(id.length>0){
                val db =this.writableDatabase
                db.delete("usuario","id=?", arrayOf(id))
                db.close()
            }
        }
    }
    fun actualizarDatos(id:String,nombre:String,edad:Int):String{
        val db = this.writableDatabase
        var contenedor =ContentValues()
        contenedor.put("nombre",nombre)
        contenedor.put("edad",edad)
        var resultado =db.update("usuario",contenedor,"id=?", arrayOf(id))
        if(resultado>0){
            return "Actualizacion realizada"
        }else
        {
            return "no actualizado"
        }
    }
    fun insertarDatos(usuario:Usuario):String{
        val db = this.writableDatabase
        var contenedor =ContentValues()
        contenedor.put("nombre",usuario.nombre)
        contenedor.put("edad",usuario.edad)
        var resultado =db.insert("usuario",null,contenedor)
        if(resultado==-1.toLong()){
            return "Existió una falla en base de datos"
        }else
        {
            return "Insertado"
        }
    }
    @SuppressLint("Range")
    fun listarDatos():MutableList<Usuario>{
        val lista:MutableList<Usuario> = ArrayList()
        val db = this.readableDatabase
        val sql = "select * from usuario"
        var resultado =db.rawQuery(sql,null)
        if(resultado.moveToFirst()){
            do{
                var usu = Usuario()
                usu.id = resultado.getString(resultado.getColumnIndex("id")).toInt()
                usu.nombre = resultado.getString(resultado.getColumnIndex("nombre"))
                usu.edad = resultado.getString(resultado.getColumnIndex("edad")).toInt()
                lista.add(usu)
            } while(resultado.moveToNext())
            resultado.close()
            db.close()
        }
        return lista
    }
}


