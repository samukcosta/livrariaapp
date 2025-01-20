package com.example.livrariaapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Livro(
    val titulo: String,
    val autor: String,
    val editora: String,
    val isbn: String,
    val descricao: String,
    val urlCapa: String
) : Parcelable
