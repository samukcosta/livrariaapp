package com.example.livrariaapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.livrariaapp.database.UsuarioDbHelper

class EsqueciSenhaActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etNovaSenha: EditText
    private lateinit var etConfirmarNovaSenha: EditText
    private lateinit var btnAtualizarSenha: Button
    private lateinit var btnVoltar: ImageButton

    private lateinit var usuarioDbHelper: UsuarioDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_esqueci_senha)

        etEmail = findViewById(R.id.etEmailEsqueciSenha)
        etNovaSenha = findViewById(R.id.etNovaSenha)
        etConfirmarNovaSenha = findViewById(R.id.etConfirmarNovaSenha)
        btnAtualizarSenha = findViewById(R.id.btnAtualizarSenha)
        btnVoltar = findViewById(R.id.btnVoltar)

        usuarioDbHelper = UsuarioDbHelper(this)

        btnAtualizarSenha.setOnClickListener {
            atualizarSenha()
        }

        btnVoltar.setOnClickListener {
            finish()
        }
    }

    private fun atualizarSenha() {
        val email = etEmail.text.toString().trim()
        val novaSenha = etNovaSenha.text.toString().trim()
        val confirmarNovaSenha = etConfirmarNovaSenha.text.toString().trim()

        if (email.isEmpty() || novaSenha.isEmpty() || confirmarNovaSenha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return
        }

        if (novaSenha != confirmarNovaSenha) {
            Toast.makeText(this, "As senhas não coincidem!", Toast.LENGTH_SHORT).show()
            return
        }

        val usuario = usuarioDbHelper.getUsuarioPorEmail(email)

        if (usuario != null) {
            usuarioDbHelper.atualizarSenha(usuario.email, novaSenha)
            Toast.makeText(this, "Senha atualizada com sucesso!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "E-mail não encontrado!", Toast.LENGTH_SHORT).show()
        }
    }
}
