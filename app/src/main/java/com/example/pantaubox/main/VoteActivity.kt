package com.example.pantaubox.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.pantaubox.databinding.ActivityVoteBinding
import com.example.pantaubox.di.ViewModelFactory
import com.example.pantaubox.model.Paslon

class VoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVoteBinding
    private val votingViewModel: VotingViewModel by viewModels { viewModelFactory }
    private lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupViewModel()

        @Suppress("DEPRECATION")
        val dataPaslon = intent.getParcelableExtra<Paslon>("data")

        Glide.with(this)
            .load(dataPaslon?.photo)
            .into(binding.ivPaslon)
        binding.apply {
            tvNamaPaslon.text = dataPaslon?.name1
            tvNamaWapaslon.text = dataPaslon?.name2
            tvDesc.text = dataPaslon?.deskripsi
            tvNorut.text = dataPaslon?.norut
            btnVote.setOnClickListener {
                uploadVoting()
            }
        }
    }

    private fun setupViewModel() {
        viewModelFactory = ViewModelFactory.getInstance(this)
    }

    private fun votingPaslon() {
        Toast.makeText(
            this@VoteActivity,
            "Anda telah selesai memilih !",
            Toast.LENGTH_SHORT
        ).show()
        startActivity(Intent(this@VoteActivity, MainActivity::class.java))
        finish()
    }

    private fun uploadVoting() {
        val norut = binding.tvNorut.text.toString()
        votingViewModel.tokenState().observe(this) {
            if (it != "") {
                votingViewModel.votingPaslon(norut)
                votingPaslon()
            }
        }
    }

    @Suppress("DEPRECATION")
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}