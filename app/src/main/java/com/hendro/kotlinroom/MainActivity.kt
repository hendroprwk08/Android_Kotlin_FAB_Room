package com.hendro.kotlinroom

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hendro.kotlinroom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
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
}