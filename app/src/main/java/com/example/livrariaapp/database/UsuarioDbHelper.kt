package com.example.livrariaapp.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.livrariaapp.Usuario

class UsuarioDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "usuarios.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "usuarios"

        private const val COL_ID = "id"
        private const val COL_EMAIL = "email"
        private const val COL_SENHA = "senha"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_EMAIL TEXT UNIQUE,
                $COL_SENHA TEXT
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun inserirUsuario(email: String, senha: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_EMAIL, email)
            put(COL_SENHA, senha)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun getUsuarioPorEmail(email: String): Usuario? {
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COL_EMAIL = ?", arrayOf(email))

        var usuario: Usuario? = null
        if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID))
            val senha = cursor.getString(cursor.getColumnIndexOrThrow(COL_SENHA))
            usuario = Usuario(id, email, senha)
        }
        cursor.close()
        return usuario
    }

    fun atualizarSenha(email: String, novaSenha: String): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_SENHA, novaSenha)
        }
        return db.update(TABLE_NAME, values, "$COL_EMAIL = ?", arrayOf(email))
    }

    fun excluirUsuario(email: String): Int {
        val db = writableDatabase
        return db.delete(TABLE_NAME, "$COL_EMAIL = ?", arrayOf(email))
    }

    fun listarUsuarios(): List<Usuario> {
        val usuarios = mutableListOf<Usuario>()
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID))
                val email = cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL))
                val senha = cursor.getString(cursor.getColumnIndexOrThrow(COL_SENHA))

                val usuario = Usuario(id, email, senha)
                usuarios.add(usuario)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return usuarios
    }
}
