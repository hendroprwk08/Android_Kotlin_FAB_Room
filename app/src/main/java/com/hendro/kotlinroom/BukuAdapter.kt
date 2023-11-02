package com.hendro.kotlinroom

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hendro.kotlinroom.databinding.RvItemsBinding
import com.hendro.kotlinroom.db.Buku

class BukuAdapter (private var bukuList: List<Buku>) :
    RecyclerView.Adapter<BukuAdapter.MyViewHolder>(){

    //binding layout: 1. ganti "binding: ItemRowLayoutBinding" dan "binding.root"
    class MyViewHolder(val binding: RvItemsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BukuAdapter.MyViewHolder {
        //binding layout: 2. tarik layout
        val binding = RvItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BukuAdapter.MyViewHolder, position: Int) {
        //binding layout: 3. letakkan nilai pada layout
        holder.binding.tvJudul.text = bukuList[position].judul
        holder.binding.tvPenulis.text = bukuList[position].penulis

        holder.itemView.setOnClickListener{
            Toast.makeText(holder.itemView.context, bukuList[position].judul, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return bukuList.size
    }
}