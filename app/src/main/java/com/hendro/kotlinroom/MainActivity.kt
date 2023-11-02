package com.hendro.kotlinroom

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.hendro.kotlinroom.databinding.ActivityMainBinding
import com.hendro.kotlinroom.db.Buku
import com.hendro.kotlinroom.db.BukuDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var db: BukuDatabase

    //https://teknorial.com/blog/membuat-database-room-di-kotlin/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // inisialisasi Database
        db = Room.databaseBuilder(applicationContext, BukuDatabase::class.java, "buku-db").build()

        // menggunakan coroutine u/ kode asinkron
        GlobalScope.launch {
            setSampleData() //memanggil function di dalam coroutine
            diplayData()
        }
    }

    private fun diplayData() {
        //gunakan recyclerview
        val books: List<Buku> = db.bukuDao().getAllBooks()
//
//
//            var displayText = ""
//            for (book in books) {
//                displayText += "${book.id} | ${book.title} | ${book.authorName} | Hal : ${book.totalPages}\n"
//            }
//            tvDisplay.text = displayText

    }

    override fun onStart() {
        super.onStart()

        binding.floatingActionButton.setOnClickListener(){
            val builder = AlertDialog.Builder(this)
                .setPositiveButton(R.string.simpan) { dialog, which ->
                    finish()
                }
                .setNeutralButton(R.string.tidak) { dialog, which ->
                    dialog.cancel()
                }.create()

            val view = layoutInflater.inflate(R.layout.input_layout,null)
            builder.setView(view)
            builder.setCanceledOnTouchOutside(false)
            builder.show()
        }
    }

    fun setSampleData(){
         db.bukuDao().insert(Buku("Bumi Manusia", "Pramoedya Ananta Toer"))
         db.bukuDao().insert(Buku("Laskar Pelangi", "Andrea Hirata"))
         db.bukuDao().insert(Buku("Anak Semua Bangsa", "Pramoedya Ananta Toer"))
         db.bukuDao().insert(Buku("Negeri 5 Menara", "Ahmad Fuadi"))
         db.bukuDao().insert(Buku(" Daun yang Jatuh Tak Pernah Membenci Angin", "Tere Liye"))
    }
}