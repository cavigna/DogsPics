package com.example.dogspics.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dogspics.databinding.ItemRowBinding
import com.example.dogspics.model.ListadoRazaDB

class RazaListAdapter : ListAdapter<ListadoRazaDB, RazaViewHolder>(RazaComparator()) {

    var nombreRazaElegida = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RazaViewHolder {
        return RazaViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RazaViewHolder, position: Int) {
        var raza = getItem(position)

        nombreRazaElegida = raza.raza

        with(holder){
            hacedorBotones(raza)
        }
    }
}


class RazaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemRowBinding.bind(itemView)

    companion object {
        fun create(parent: ViewGroup): RazaViewHolder{
            val layoutInflaterB = LayoutInflater.from(parent.context)
            val binding = ItemRowBinding.inflate(layoutInflaterB, parent, false)

            return RazaViewHolder(binding.root)
        }
    }
    fun hacedorBotones(razaDB: ListadoRazaDB){
        binding.buttonRaza.text = razaDB.raza

    }

}

class RazaComparator: DiffUtil.ItemCallback<ListadoRazaDB>() {
    override fun areItemsTheSame(oldItem: ListadoRazaDB, newItem: ListadoRazaDB): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ListadoRazaDB, newItem: ListadoRazaDB): Boolean {
        return oldItem.id == newItem.id
    }

}
