package com.example.livrariaapp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.livrariaapp.Livro
import com.example.livrariaapp.databinding.ItemLivroBinding

class LivroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemLivroBinding.bind(view)

    fun render(livro: Livro, onItemClick: (Livro) -> Unit) {
        binding.tvTitulo.text = livro.titulo
        binding.tvAutor.text = "Autor: ${livro.autor}"
        binding.tvEditora.text = "Editora: ${livro.editora}"
        binding.tvIsbn.text = "ISBN: ${livro.isbn}"

        Glide.with(binding.root.context)
            .load(livro.urlCapa)
            .into(binding.ivCapaLivro)

        itemView.setOnClickListener {
            onItemClick(livro)
        }
    }
}
