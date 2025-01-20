package com.example.livrariaapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.livrariaapp.database.UsuarioDbHelper

class CadastrarUsuarioActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etSenha: EditText
    private lateinit var etConfirmarSenha: EditText
    private lateinit var btnCadastrar: Button
    private lateinit var btnVoltar: ImageButton

    private lateinit var usuarioDbHelper: UsuarioDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_usuario)

        etEmail = findViewById(R.id.etEmailCadastro)
        etSenha = findViewById(R.id.etSenhaCadastro)
        etConfirmarSenha = findViewById(R.id.etConfirmarSenhaCadastro)
        btnCadastrar = findViewById(R.id.btnCadastrarUsuario)
        btnVoltar = findViewById(R.id.btnVoltar)

        usuarioDbHelper = UsuarioDbHelper(this)

        btnCadastrar.setOnClickListener {
            cadastrarUsuario()
        }

        btnVoltar.setOnClickListener {
            finish()
        }
    }

    private fun cadastrarUsuario() {
        val email = etEmail.text.toString().trim()
        val senha = etSenha.text.toString().trim()
        val confirmarSenha = etConfirmarSenha.text.toString().trim()

        if (email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return
        }

        if (senha != confirmarSenha) {
            Toast.makeText(this, "As senhas não coincidem!", Toast.LENGTH_SHORT).show()
            return
        }

        val usuarioExistente = usuarioDbHelper.getUsuarioPorEmail(email)
        if (usuarioExistente != null) {
            Toast.makeText(this, "Este e-mail já está cadastrado!", Toast.LENGTH_SHORT).show()
        } else {
            usuarioDbHelper.inserirUsuario(email, senha)

            Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
