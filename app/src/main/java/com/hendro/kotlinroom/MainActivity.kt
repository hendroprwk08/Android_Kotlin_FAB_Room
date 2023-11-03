package com.hendro.kotlinroom

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.hendro.kotlinroom.databinding.ActivityMainBinding
import com.hendro.kotlinroom.databinding.InputLayoutBinding
import com.hendro.kotlinroom.db.Buku
import com.hendro.kotlinroom.db.BukuDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var inputLayoutBinding: InputLayoutBinding //bind dialog layout
    private lateinit var bukuAdapter: BukuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // inisialisasi Database
        db = Room.databaseBuilder(applicationContext, BukuDatabase::class.java, "buku-db").build()

        GlobalScope.launch {
            setSampleData()
            displayData()
        }
    }

    companion object { // static behaviour
        lateinit var db: BukuDatabase
    }

    private fun displayData() {
        var bukuList: List<Buku> = db.bukuDao().getAllBooks()

        Log.i("TAG", "displayData: ${bukuList.toString()}")

        //set recyclerview adapter
        bukuAdapter = BukuAdapter( applicationContext ,bukuList)

        val layoutManager = GridLayoutManager(this, 1) //jumlah kolom 1
        binding.contentMain.recyclerView.layoutManager = layoutManager
        binding.contentMain.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.contentMain.recyclerView.adapter = bukuAdapter
    }

    override fun onStart() {
        super.onStart()

        binding.floatingActionButton.setOnClickListener(){
            inputLayoutBinding = InputLayoutBinding.inflate(layoutInflater) //binding

            val builder = AlertDialog.Builder(this)
                .setPositiveButton(R.string.simpan) { dialog, which ->
                    GlobalScope.launch {
                        db.bukuDao().insert(Buku(inputLayoutBinding.etJudul.text.toString(),
                            inputLayoutBinding.etPenulis.text.toString()))
                        displayData()
                    }

                    // refresh adapter
                    bukuAdapter.notifyDataSetChanged()
                }
                .setNeutralButton(R.string.tidak) { dialog, which ->
                    dialog.cancel()
                }.create()

            builder.setView(inputLayoutBinding.root) //masukkan layout
            builder.setCanceledOnTouchOutside(false)
            builder.show()
        }
    }

    fun setSampleData(){
        if(db.bukuDao().getAllBooks().size == 0) {
            GlobalScope.launch {//memanggil function di dalam coroutine
                db.bukuDao().insert(Buku("Bumi Manusia", "Pramoedya Ananta Toer"))
                db.bukuDao().insert(Buku("Laskar Pelangi", "Andrea Hirata"))
                db.bukuDao().insert(Buku("Anak Semua Bangsa", "Pramoedya Ananta Toer"))
                db.bukuDao().insert(Buku("Negeri 5 Menara", "Ahmad Fuadi"))
                db.bukuDao().insert(Buku("Daun yang Jatuh Tak Pernah Membenci Angin", "Tere Liye"))
            }
        }
    }
}