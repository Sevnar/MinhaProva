package com.example.minhaprova

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class LivroDBOpener(context: Context):SQLiteOpenHelper(context, LivroContrato.DATABASE_NAME, null, LivroContrato.DATA_BASE_VERSION) {

    val SQL_CREATE_TABLE =
        "CREATE TABLE ${LivroContrato.LivroEntry.LIVROS}" +
                "( ${BaseColumns._ID} INTEGER PRIMARY KEY," +
                " ${LivroContrato.LivroEntry.NOME} TEXT," +
                " ${LivroContrato.LivroEntry.AUTOR} TEXT," +
                " ${LivroContrato.LivroEntry.ANO} INTEGER," +
                " ${LivroContrato.LivroEntry.NOTA} REAL" +
                ")"

    val SQL_DROP_TABLE =
        "DROP TABLE ${LivroContrato.LivroEntry.LIVROS}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion != newVersion){
            db?.execSQL(SQL_DROP_TABLE)
            db?.execSQL(SQL_CREATE_TABLE)
        }
    }

    fun insert (l:Livro){

        var banco:SQLiteDatabase = writableDatabase

        try {

            var values = ContentValues()

            values.put(LivroContrato.LivroEntry.NOME, l.nome)
            values.put(LivroContrato.LivroEntry.AUTOR, l.autor)
            values.put(LivroContrato.LivroEntry.ANO, l.ano)
            values.put(LivroContrato.LivroEntry.NOTA, l.nota)

            banco.insert(LivroContrato.LivroEntry.LIVROS, null, values)
        }finally{
            banco.close()
        }
    }

    fun update(l:Livro){
        var banco:SQLiteDatabase = writableDatabase
        try{
            var values = ContentValues()

            values.put(LivroContrato.LivroEntry.NOME, l.nome)
            values.put(LivroContrato.LivroEntry.AUTOR, l.autor)
            values.put(LivroContrato.LivroEntry.ANO, l.ano)
            values.put(LivroContrato.LivroEntry.NOTA, l.nota)

            var selection =  "${BaseColumns._ID} = ?"
            var whereArgs = arrayOf("${l.id}")

            banco.update(LivroContrato.LivroEntry.LIVROS, values, selection, whereArgs)

        }finally {
            banco.close()
        }
    }

    fun delete(l:Livro){
        var banco:SQLiteDatabase = writableDatabase
        try {
            var selection = "${BaseColumns._ID}"
            var whereArgs = arrayOf("${l.id}")
            banco.delete(LivroContrato.LivroEntry.LIVROS, selection, whereArgs)
        }finally {
            banco.close()
        }
    }

    fun findById(id:Int): Livro{
        var banco:SQLiteDatabase = readableDatabase
        try {

            var selection = "${BaseColumns._ID} = ?"
            var whereArgs = arrayOf("${id}")
            val cursor:Cursor = banco.query(LivroContrato.LivroEntry.LIVROS, null, selection, whereArgs, null, null, null, null)

            cursor.moveToFirst()


            //Ei boy, só aceita esse jeito aí embaixo, pfv <3
            var livro = Livro(

            cursor.getInt(cursor.getColumnIndex(BaseColumns._ID)),
            cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.NOME)),
            cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.AUTOR)),
            cursor.getInt(cursor.getColumnIndex(LivroContrato.LivroEntry.ANO)),
            cursor.getFloat(cursor.getColumnIndex(LivroContrato.LivroEntry.NOTA))

            )

            return livro
        }finally {
            banco.close()
        }
    }
}