package com.example.pantaubox.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pantaubox.R
import com.example.pantaubox.databinding.ActivityMainBinding
import com.example.pantaubox.di.ViewModelFactory
import com.example.pantaubox.login.LoginWelcome
import com.example.pantaubox.main.adapter.PrimaryAdapter
import com.example.pantaubox.model.Paslon

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels { viewModelFactory }
    private lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvPaslon: RecyclerView
    private val list = ArrayList<Paslon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvPaslon = binding.rvPaslon
        rvPaslon.setHasFixedSize(true)

        list.addAll(getListPaslon())
        setupRv()
        setupViewModel()
    }

    @SuppressLint("Recycle")
    private fun getListPaslon(): ArrayList<Paslon> {
        val paslonName1 = resources.getStringArray(R.array.paslon_name1)
        val paslonName2 = resources.getStringArray(R.array.paslon_name2)
        val paslonDesc = resources.getStringArray(R.array.paslon_desc)
        val paslonImg = resources.getStringArray(R.array.paslon_img)
        val paslonNorut = resources.getStringArray(R.array.norut)
        val listPaslon = ArrayList<Paslon>()
        for (i in paslonName1.indices) {
            val paslon = Paslon(
                paslonName1[i],
                paslonName2[i],
                paslonImg[i],
                paslonNorut[i],
                paslonDesc[i]
            )
            listPaslon.add(paslon)
        }
        return listPaslon
    }

    private fun setupRv() {
        rvPaslon.layoutManager = LinearLayoutManager(this)
        val adapter = PrimaryAdapter(list)
        rvPaslon.adapter = adapter

        adapter.setOnItemClickCallback(object : PrimaryAdapter.OnItemClickCallback {
            override fun onItemClicked(paslon: Paslon) {
                detailVote(paslon)
            }
        })
    }

    private fun setupViewModel() {
        viewModelFactory = ViewModelFactory.getInstance(this)
    }

    private fun detailVote(paslon: Paslon) {
        val intentVote = Intent(this@MainActivity, VoteActivity::class.java)
        intentVote.putExtra("data", paslon)
        startActivity(intentVote)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                mainViewModel.logout()
                startActivity(Intent(this@MainActivity, LoginWelcome::class.java))
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}