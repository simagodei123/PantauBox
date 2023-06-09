package com.example.pantaubox.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pantaubox.databinding.ActivityVoteBinding
import com.example.pantaubox.model.Paslon

class VoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        @Suppress("DEPRECATION")
        val dataPaslon = intent.getParcelableExtra<Paslon>("data")

        binding.apply {
            tvNamaPaslon.text = dataPaslon?.name1
            tvNamaWapaslon.text = dataPaslon?.name2
            tvNorut.text = dataPaslon?.norut
            ivPaslon.setImageResource(dataPaslon?.photo!!)
            btnVote.setOnClickListener {
                votingPaslon()
            }
        }
    }

    private fun votingPaslon() {
        Toast.makeText(
            this@VoteActivity,
            "Anda telah selesai memilih !",
            Toast.LENGTH_SHORT
        ).show()
        startActivity(Intent(this@VoteActivity, MainActivity::class.java))
    }

    @Suppress("DEPRECATION")
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}