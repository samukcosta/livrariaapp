package com.example.livrariaapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.livrariaapp.database.LivroDbHelper

class CadastroLivroActivity : AppCompatActivity() {

    private lateinit var etTituloCadastrar: EditText
    private lateinit var etAutorCadastrar: EditText
    private lateinit var etEditoraCadastrar: EditText
    private lateinit var etISBNCadastrar: EditText
    private lateinit var etDescricaoCadastrar: EditText
    private lateinit var etUrlCapaCadastrar: EditText
    private lateinit var btnCadastrarLivroCadastrar: Button
    private lateinit var btnVoltarCadastrar: ImageButton
    private lateinit var dbHelperCadastrar: LivroDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_livro)

        etTituloCadastrar = findViewById(R.id.etTituloCadastrar)
        etAutorCadastrar = findViewById(R.id.etAutorCadastrar)
        etEditoraCadastrar = findViewById(R.id.etEditoraCadastrar)
        etISBNCadastrar = findViewById(R.id.etISBNCadastrar)
        etDescricaoCadastrar = findViewById(R.id.etDescricaoCadastrar)
        etUrlCapaCadastrar = findViewById(R.id.etUrlCapaCadastrar)
        btnCadastrarLivroCadastrar = findViewById(R.id.btnCadastrarLivroCadastrar)
        btnVoltarCadastrar = findViewById(R.id.btnVoltarCadastro)

        dbHelperCadastrar = LivroDbHelper(this)

        btnCadastrarLivroCadastrar.setOnClickListener {
            cadastrarLivro()
        }

        btnVoltarCadastrar.setOnClickListener {
            voltarParaListarLivros()
        }
    }

    private fun cadastrarLivro() {
        val titulo = etTituloCadastrar.text.toString()
        val autor = etAutorCadastrar.text.toString()
        val editora = etEditoraCadastrar.text.toString()
        val isbn = etISBNCadastrar.text.toString()
        val descricao = etDescricaoCadastrar.text.toString()
        val urlCapa = etUrlCapaCadastrar.text.toString()

        if (titulo.isEmpty() || autor.isEmpty() || editora.isEmpty() || isbn.isEmpty() || descricao.isEmpty() || urlCapa.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return
        }

        val livro = Livro(titulo, autor, editora, isbn, descricao, urlCapa)
        val resultado = dbHelperCadastrar.inserirLivro(livro)

        if (resultado > 0) {
            Toast.makeText(this, "Livro cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
            limparCampos()
            voltarParaListarLivros()
        } else {
            Toast.makeText(this, "Erro ao cadastrar o livro!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun limparCampos() {
        etTituloCadastrar.text.clear()
        etAutorCadastrar.text.clear()
        etEditoraCadastrar.text.clear()
        etISBNCadastrar.text.clear()
        etDescricaoCadastrar.text.clear()
        etUrlCapaCadastrar.text.clear()
    }

    private fun voltarParaListarLivros() {
        val intent = Intent(this, ListarLivrosActivity::class.java)
        startActivity(intent)
        finish()
    }
}
