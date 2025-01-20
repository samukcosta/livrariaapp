package com.example.livrariaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.livrariaapp.database.LivroDbHelper

class EditarLivroActivity : AppCompatActivity() {

    private lateinit var etISBN: EditText
    private lateinit var etTitulo: EditText
    private lateinit var etAutor: EditText
    private lateinit var etEditora: EditText
    private lateinit var etUrlCapa: EditText
    private lateinit var etDescricao: EditText
    private lateinit var btnSalvar: Button
    private lateinit var btnVoltar: ImageButton

    private lateinit var dbHelper: LivroDbHelper
    private var livro: Livro? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_livro)

        etISBN = findViewById(R.id.etISBN)
        etTitulo = findViewById(R.id.etTitulo)
        etAutor = findViewById(R.id.etAutor)
        etEditora = findViewById(R.id.etEditora)
        etUrlCapa = findViewById(R.id.etUrlCapa)
        etDescricao = findViewById(R.id.etDescricao)
        btnSalvar = findViewById(R.id.btnSalvar)
        btnVoltar = findViewById(R.id.btnVoltarEditar)

        dbHelper = LivroDbHelper(this)

        val isbn = intent.getStringExtra("ISBN")

        if (isbn != null) {
            livro = dbHelper.buscarLivroPorISBN(isbn)

            livro?.let {
                etISBN.setText(it.isbn)
                etTitulo.setText(it.titulo)
                etAutor.setText(it.autor)
                etEditora.setText(it.editora)
                etUrlCapa.setText(it.urlCapa)
                etDescricao.setText(it.descricao)
            }
        }


        btnSalvar.setOnClickListener {
            salvarAlteracoes()
        }

        btnVoltar.setOnClickListener {
            finish()
        }
    }

    private fun salvarAlteracoes() {
        val novoTitulo = etTitulo.text.toString()
        val novoAutor = etAutor.text.toString()
        val novaEditora = etEditora.text.toString()
        val novaUrlCapa = etUrlCapa.text.toString()
        val novaDescricao = etDescricao.text.toString()

        if (novoTitulo.isNotEmpty() && novoAutor.isNotEmpty() && novaEditora.isNotEmpty() && novaUrlCapa.isNotEmpty()) {
            val livroEditado = Livro(
                titulo = novoTitulo,
                autor = novoAutor,
                editora = novaEditora,
                isbn = etISBN.text.toString(),
                descricao = novaDescricao,
                urlCapa = novaUrlCapa,
            )

            val resultado = dbHelper.atualizarLivro(livroEditado)

            if (resultado > 0) {
                Toast.makeText(this, "Livro atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Erro ao atualizar o livro!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Preencha todos os campos corretamente!", Toast.LENGTH_SHORT).show()
        }
    }
}
