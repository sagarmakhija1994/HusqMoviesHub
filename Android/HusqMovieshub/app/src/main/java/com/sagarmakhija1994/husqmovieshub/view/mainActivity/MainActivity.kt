package com.sagarmakhija1994.husqmovieshub.view.mainActivity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.sagarmakhija1994.husqmovieshub.databinding.ActivityMainBinding
import com.sagarmakhija1994.husqmovieshub.view.movieList.MoviesListActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        Looper.myLooper()?.let {
            Handler(it).postDelayed({
                startActivity(Intent(this,MoviesListActivity::class.java))
                finish()
            },2500)
        }
    }
}