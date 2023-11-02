package com.hendro.kotlinroom

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hendro.kotlinroom.databinding.RvItemsBinding
import com.hendro.kotlinroom.db.Buku

class TemanAdapter (private var temanList: List<Buku>) :
    RecyclerView.Adapter<TemanAdapter.MyViewHolder>(){

    //binding layout: 1. ganti "binding: ItemRowLayoutBinding" dan "binding.root"
    class MyViewHolder(val binding: RvItemsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TemanAdapter.MyViewHolder {
        //binding layout: 2. tarik layout
        val binding = RvItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TemanAdapter.MyViewHolder, position: Int) {
        //binding layout: 3. letakkan nilai pada layout
//        holder.binding.tvNama.text = temanList[position].nama()
//        holder.binding.tvKeterangan.text = temanList[position].getKeterangan()
//
//        Glide.with(holder.itemView.context)
//            .load(temanList[position].getGambar())
//            .centerCrop()
//            .into(holder.binding.imageview)
//
//        holder.itemView.setOnClickListener{
//            Toast.makeText(holder.itemView.context, temanList[position].getNama(), Toast.LENGTH_SHORT).show()
//        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}