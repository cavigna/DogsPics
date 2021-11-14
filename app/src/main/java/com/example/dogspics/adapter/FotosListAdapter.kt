package com.example.dogspics.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dogspics.databinding.ItemRowImgBinding
import com.example.dogspics.model.PerroImagenes

//private val miEscuchador: MiEscuchador

class FotosListAdapter(private val miEscuchador: MiEscuchador): ListAdapter<String, FotosViewHolder>(FotoComparador()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FotosViewHolder {
        return FotosViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FotosViewHolder, position: Int) {

        val currentImagenes = getItem(position)
        holder.binding.imageViewPerro.load(currentImagenes)

        holder.binding.imageViewPerro.setOnLongClickListener{
            miEscuchador.alHacerClick(currentImagenes)
            true

        }

        /*
        holder.binding.imageViewPerro.setOnClickListener {
            miEscuchador.alHacerClick(currentImagenes)
        }

         */
    }

    interface MiEscuchador {
        fun alHacerClick(url: String)
    }
}

class FotosViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemRowImgBinding.bind(itemView)

    companion object{
        fun create(parent: ViewGroup) : FotosViewHolder{
            val layoutInflaterB = LayoutInflater.from(parent.context)
            val binding = ItemRowImgBinding.inflate(layoutInflaterB, parent, false)

            return FotosViewHolder(binding.root)

        }
    }
}

class FotoComparador : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}
