package com.example.livrariaapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.livrariaapp.database.UsuarioDbHelper

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etSenha: EditText
    private lateinit var btnEntrar: Button
    private lateinit var tvCadastrar: TextView
    private lateinit var tvNovaSenha: TextView

    private lateinit var usuarioDbHelper: UsuarioDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.etEmail)
        etSenha = findViewById(R.id.etSenha)
        btnEntrar = findViewById(R.id.btnEntrar)
        tvCadastrar = findViewById(R.id.tvCadastrar)
        tvNovaSenha = findViewById(R.id.tvNovaSenha)

        usuarioDbHelper = UsuarioDbHelper(this)

        btnEntrar.setOnClickListener {
            realizarLogin()
        }

        tvCadastrar.setOnClickListener {
            val intent = Intent(this, CadastrarUsuarioActivity::class.java)
            startActivity(intent)
        }

        tvNovaSenha.setOnClickListener {
            val intent = Intent(this, EsqueciSenhaActivity::class.java)
            startActivity(intent)
        }
    }

    private fun realizarLogin() {
        val email = etEmail.text.toString().trim()
        val senha = etSenha.text.toString().trim()

        if (email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return
        }

        val usuario = usuarioDbHelper.getUsuarioPorEmail(email)

        if (usuario != null && usuario.senha == senha) {
            val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()

            Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "E-mail ou senha incorretos!", Toast.LENGTH_SHORT).show()
        }
    }
}
