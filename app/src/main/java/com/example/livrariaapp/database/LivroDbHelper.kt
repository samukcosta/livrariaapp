package com.example.livrariaapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.livrariaapp.Livro

class LivroDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "livros.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "livros"

        private const val COL_ID = "id"
        private const val COL_TITULO = "titulo"
        private const val COL_AUTOR = "autor"
        private const val COL_EDITORA = "editora"
        private const val COL_ISBN = "isbn"
        private const val COL_DESCRICAO = "descricao"
        private const val COL_URL_CAPA = "urlCapa"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """ 
            CREATE TABLE $TABLE_NAME (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, 
                $COL_TITULO TEXT, 
                $COL_AUTOR TEXT, 
                $COL_EDITORA TEXT, 
                $COL_ISBN TEXT UNIQUE, 
                $COL_DESCRICAO TEXT, 
                $COL_URL_CAPA TEXT
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun inserirLivro(livro: Livro): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_TITULO, livro.titulo)
            put(COL_AUTOR, livro.autor)
            put(COL_EDITORA, livro.editora)
            put(COL_ISBN, livro.isbn)
            put(COL_DESCRICAO, livro.descricao)
            put(COL_URL_CAPA, livro.urlCapa)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun listarLivros(): List<Livro> {
        val livros = mutableListOf<Livro>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            do {
                val livro = Livro(
                    titulo = cursor.getString(cursor.getColumnIndexOrThrow(COL_TITULO)),
                    autor = cursor.getString(cursor.getColumnIndexOrThrow(COL_AUTOR)),
                    editora = cursor.getString(cursor.getColumnIndexOrThrow(COL_EDITORA)),
                    isbn = cursor.getString(cursor.getColumnIndexOrThrow(COL_ISBN)),
                    descricao = cursor.getString(cursor.getColumnIndexOrThrow(COL_DESCRICAO)),
                    urlCapa = cursor.getString(cursor.getColumnIndexOrThrow(COL_URL_CAPA))
                )
                livros.add(livro)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return livros
    }

    fun buscarLivroPorISBN(isbn: String): Livro? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COL_ISBN = ?", arrayOf(isbn))

        return if (cursor.moveToFirst()) {
            val livro = Livro(
                titulo = cursor.getString(cursor.getColumnIndexOrThrow(COL_TITULO)),
                autor = cursor.getString(cursor.getColumnIndexOrThrow(COL_AUTOR)),
                editora = cursor.getString(cursor.getColumnIndexOrThrow(COL_EDITORA)),
                isbn = cursor.getString(cursor.getColumnIndexOrThrow(COL_ISBN)),
                descricao = cursor.getString(cursor.getColumnIndexOrThrow(COL_DESCRICAO)),
                urlCapa = cursor.getString(cursor.getColumnIndexOrThrow(COL_URL_CAPA))
            )
            cursor.close()
            livro
        } else {
            cursor.close()
            null
        }
    }

    fun atualizarLivro(livro: Livro): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_TITULO, livro.titulo)
            put(COL_AUTOR, livro.autor)
            put(COL_EDITORA, livro.editora)
            put(COL_DESCRICAO, livro.descricao)
            put(COL_URL_CAPA, livro.urlCapa)
        }
        return db.update(TABLE_NAME, values, "$COL_ISBN = ?", arrayOf(livro.isbn))
    }

    fun excluirLivro(isbn: String): Int {
        val db = this.writableDatabase
        val args = ContentValues()

        val whereClause = "isbn = ?"
        val whereArgs = arrayOf(isbn)

        return db.delete(TABLE_NAME, whereClause, whereArgs)
    }

}
