package com.example.pantaubox.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pantaubox.databinding.KandidatListBinding
import com.example.pantaubox.model.Paslon

class PrimaryAdapter(private val listPaslon: ArrayList<Paslon>) :
    RecyclerView.Adapter<PrimaryAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            KandidatListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (name1, name2, photo, norut) = listPaslon[position]
        holder.binding.tvKandidatName1.text = name1
        holder.binding.tvKandidatName2.text = name2
        holder.binding.imgKandidat.setImageResource(photo)
        holder.binding.tvNorutPaslon.text = norut

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listPaslon[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listPaslon.size

    class ViewHolder(var binding: KandidatListBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(paslon: Paslon)
    }
}