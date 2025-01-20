package com.example.livrariaapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.livrariaapp.adapter.LivroAdapter
import com.example.livrariaapp.database.LivroDbHelper

class ListarLivrosActivity : AppCompatActivity() {

    private lateinit var rvLivros: RecyclerView
    private lateinit var etBusca: EditText
    private lateinit var btnAdicionarLivro: ImageButton
    private lateinit var btnLogout: ImageButton
    private lateinit var livroAdapter: LivroAdapter
    private lateinit var dbHelper: LivroDbHelper
    private var listaLivros = mutableListOf<Livro>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_livros)

        rvLivros = findViewById(R.id.rvLivros)
        etBusca = findViewById(R.id.etBusca)
        btnAdicionarLivro = findViewById(R.id.btnAdicionarLivro)
        btnLogout = findViewById(R.id.btnLogout)
        dbHelper = LivroDbHelper(this)

        rvLivros.layoutManager = LinearLayoutManager(this)
        livroAdapter = LivroAdapter(this, listaLivros) { livro ->
            val intent = Intent(this, DetalheLivroActivity::class.java)
            intent.putExtra("livro", livro)
            startActivity(intent)
        }
        rvLivros.adapter = livroAdapter

        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        rvLivros.addItemDecoration(decoration)

        atualizarListaLivros()

        btnAdicionarLivro.setOnClickListener {
            val intent = Intent(this, CadastroLivroActivity::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            realizarLogout()
        }

        etBusca.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filtrarLivros(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun onResume() {
        super.onResume()
        atualizarListaLivros()
    }

    private fun atualizarListaLivros() {
        listaLivros.clear()
        listaLivros.addAll(dbHelper.listarLivros())

        if (listaLivros.isEmpty()) {
            findViewById<TextView>(R.id.tvListaVazia).visibility = View.VISIBLE
            rvLivros.visibility = View.GONE
        } else {
            findViewById<TextView>(R.id.tvListaVazia).visibility = View.GONE
            rvLivros.visibility = View.VISIBLE
            livroAdapter.notifyDataSetChanged()
        }
    }

    private fun filtrarLivros(query: String) {
        val livrosFiltrados = if (query.isEmpty()) {
            dbHelper.listarLivros()
        } else {
            dbHelper.listarLivros().filter { it.isbn.contains(query, ignoreCase = true) }
        }
        livroAdapter.updateList(livrosFiltrados)
    }

    private fun realizarLogout() {
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
