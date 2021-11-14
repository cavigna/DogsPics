package com.example.dogspics.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dogspics.databinding.ItemRowFavBinding
import com.example.dogspics.model.PerroFavorito

class FavListAdapter(private val miEscuchadorFav: MiEscuchadorFav) : ListAdapter<PerroFavorito, FavViewHolder>(FavoritoComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        return FavViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val currentDog = getItem(position)
        with(holder.binding){
            imageViewFav.load(currentDog.imagenUrl)
            textViewFav.text = currentDog.raza
        }

        holder.binding.ivEliminar.setOnClickListener {
            miEscuchadorFav.alHacerClick(currentDog)
        }

    }

    interface MiEscuchadorFav{
        fun alHacerClick(perroFavorito: PerroFavorito)
    }
}

class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemRowFavBinding.bind(itemView)

    companion object {
        fun create(parent: ViewGroup): FavViewHolder {
            val layoutInflaterB = LayoutInflater.from(parent.context)
            val binding = ItemRowFavBinding.inflate(layoutInflaterB, parent, false)
            return FavViewHolder(binding.root)
        }
    }
}

class FavoritoComparator: DiffUtil.ItemCallback<PerroFavorito>() {
    override fun areItemsTheSame(oldItem: PerroFavorito, newItem: PerroFavorito): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PerroFavorito, newItem: PerroFavorito): Boolean {
        return oldItem.id == newItem.id
    }


}
