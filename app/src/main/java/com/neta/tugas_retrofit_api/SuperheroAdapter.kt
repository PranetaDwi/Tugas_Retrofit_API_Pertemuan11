package com.neta.tugas_retrofit_api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neta.tugas_retrofit_api.databinding.ItemSuperheroBinding
import com.neta.tugas_retrofit_api.model.SuperheroData
import com.squareup.picasso.Picasso

class SuperheroAdapter (private var listSuperhero: List<SuperheroData>, private val
onClickHero: (SuperheroData)-> Unit): RecyclerView.Adapter<SuperheroAdapter.ItemSuperheroViewHolder>() {

    inner class ItemSuperheroViewHolder(private val binding: ItemSuperheroBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: SuperheroData) {
            with(binding) {
                txtTitleSuperhero.text = data.title

                Picasso.get()
                    .load(data.image)
                    .error(R.drawable.soekarno) // Gambar placeholder jika terjadi kesalahan
                    .into(txtImageSuperhero)

                itemView.setOnClickListener {
                    onClickHero(data)
                }
            }
        }
    }

    fun setData(newData: List<SuperheroData>) {
        listSuperhero = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSuperheroViewHolder {
        val binding = ItemSuperheroBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent, false
        )
        return ItemSuperheroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemSuperheroViewHolder, position: Int) {
        holder.bind(listSuperhero[position])
    }

    override fun getItemCount(): Int = listSuperhero.size
}