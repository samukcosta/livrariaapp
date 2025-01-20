package com.example.livrariaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.livrariaapp.database.LivroDbHelper

class DetalheLivroActivity : AppCompatActivity() {

    private lateinit var ivCapa: ImageView
    private lateinit var tvTitulo: TextView
    private lateinit var tvAutor: TextView
    private lateinit var tvEditora: TextView
    private lateinit var tvISBN: TextView
    private lateinit var tvDescricao: TextView
    private lateinit var btnExcluir: Button
    private lateinit var btnEditar: Button
    private lateinit var btnVoltar: ImageButton

    private lateinit var dbHelper: LivroDbHelper

    private var livro: Livro? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_livro)

        ivCapa = findViewById(R.id.ivCapaDetalhe)
        tvTitulo = findViewById(R.id.tvTituloPage)
        tvAutor = findViewById(R.id.tvAutorDetalhe)
        tvEditora = findViewById(R.id.tvEditoraDetalhe)
        tvISBN = findViewById(R.id.tvISBNDetalhe)
        tvDescricao = findViewById(R.id.tvDescricaoDetalhe)
        btnExcluir = findViewById(R.id.btnExcluirLivro)
        btnEditar = findViewById(R.id.btnEditarLivro)
        btnVoltar = findViewById(R.id.btnVoltar)

        dbHelper = LivroDbHelper(this)

        livro = intent.getParcelableExtra("livro")

        livro?.let {
            tvTitulo.text = it.titulo
            tvAutor.text = "Autor: ${it.autor}"
            tvEditora.text = "Editora: ${it.editora}"
            tvISBN.text = "ISBN: ${it.isbn}"
            tvDescricao.text = "Descrição: ${it.descricao}"

            Glide.with(this)
                .load(it.urlCapa)
                .placeholder(R.drawable.placeholder_capa)
                .into(ivCapa)
        }

        btnVoltar.setOnClickListener {
            val intentListarLivros = Intent(this, ListarLivrosActivity::class.java)
            startActivity(intentListarLivros)
            finish()
        }

        btnExcluir.setOnClickListener {
            livro?.let { livro ->
                val resultado = dbHelper.excluirLivro(livro.isbn)

                if (resultado > 0) {
                    Toast.makeText(this, "Livro excluído com sucesso!", Toast.LENGTH_SHORT).show()

                    val intentListarLivros = Intent(this, ListarLivrosActivity::class.java)
                    startActivity(intentListarLivros)

                    finish()
                } else {
                    Toast.makeText(this, "Erro ao excluir o livro.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnEditar.setOnClickListener {
            livro?.let { livro ->
                val intent = Intent(this, EditarLivroActivity::class.java)
                intent.putExtra("ISBN", livro.isbn)
                startActivity(intent)
            }
        }
    }
}

