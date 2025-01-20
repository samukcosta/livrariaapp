package com.example.livrariaapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.livrariaapp.Livro
import com.example.livrariaapp.R

class LivroAdapter(
    private val context: Context,
    private var livros: List<Livro>,
    private val onItemClick: (Livro) -> Unit
) : RecyclerView.Adapter<LivroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LivroViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return LivroViewHolder(layoutInflater.inflate(R.layout.item_livro, parent, false))
    }

    override fun getItemCount(): Int {
        return livros.size
    }

    override fun onBindViewHolder(holder: LivroViewHolder, position: Int) {
        val livro = livros[position]
        holder.render(livro, onItemClick)
    }

    fun updateList(novaLista: List<Livro>) {
        livros = novaLista
        notifyDataSetChanged()
    }

}
