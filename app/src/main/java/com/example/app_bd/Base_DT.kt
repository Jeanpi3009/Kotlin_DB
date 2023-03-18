package com.example.app_bd

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class Base_DT (contexto: Context):SQLiteOpenHelper(contexto, "BdDatos",null ,1){

    override fun onCreate(db: SQLiteDatabase?) {
        var sql="CREATE TABLE producto (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR(80), cantidad INTEGER)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun guardarDatos(datos:Datos):String{
        val db =this.writableDatabase
        var contenedor= ContentValues()
        contenedor.put("nombre",datos.nombre)
        contenedor.put("cantidad",datos.cantidad)
        try {

            var resultado=db.insert("producto", null, contenedor)
            if(resultado == -1.toLong()){
                return "Falla en la insersion"
            }else{
                return "Se grabo exitosamente"
            }
        }catch (ex:Exception){
            return ex.message.toString()
        }finally {
            db.close()
        }
    }

    fun actualizarDatos(id:Int,nombre:String,cantidad:Int):String{

        val db =this.writableDatabase
        var contenedor= ContentValues()
        contenedor.put("nombre",nombre)
        contenedor.put("cantidad",cantidad)
        try {

            var resultado=db.update("producto", contenedor, "id=?", arrayOf(id.toString()))
            if(resultado>0){
                return "Actualizacion exitosa"
            }else {
                return "Falla en la actualizacion"
            }
        }catch (ex:Exception){
            return ex.message.toString()
        }finally {
            db.close()
        }
    }

//    fun actualizarDatos2(datos: Datos):String{
//        val db =this.writableDatabase
//        var contenedor= ContentValues()
//        contenedor.put("nombre",datos.nombre)
//        contenedor.put("cantidad",datos.cantidad)
//        try {
//
//            var resultado=db.update("producto", contenedor, "id=?", arrayOf(datos.id.toString()))
//            if(resultado>0){
//                return "Actualizacion exitosa"
//            }else {
//                return "Falla en la actualizacion"
//            }
//        }catch (ex:Exception){
//            return ex.message.toString()
//        }finally {
//            db.close()
//        }
//    }

    fun borrarDatos(id:Int):String{
        val db = this.writableDatabase
        if (id != 0) {
            db.delete("producto", "id=?", arrayOf(id.toString()))
            return "Se ah borrado el registro exitosamente"
        }else{
            return "No se ah podido borrar el registro"
        }
    }

    @SuppressLint("Range", "SuspiciousIndentation")
    fun ListarDatos():MutableList<Datos>{
        val lista:MutableList<Datos> = ArrayList()
        val db=this.readableDatabase
        val sql="select * from producto"
        var resultado=db.rawQuery(sql,null)
        if(resultado.moveToFirst()){

            do {
                var datosp=Datos()
                datosp.id = resultado.getString(resultado.getColumnIndex("id")).toInt()
                datosp.nombre = resultado.getString(resultado.getColumnIndex("nombre"))
                datosp.cantidad = resultado.getString(resultado.getColumnIndex("cantidad")).toInt()
                    lista.add(datosp)
            }while (resultado.moveToNext())
            resultado.close()
            db.close()
        }
        return lista
    }
}



