package com.example.hw6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.hw6.adapter.PosterLoader
import com.example.hw6.databinding.ActivityMainBinding
import com.example.hw6.fragment.MovieDetailsFragment
import com.example.hw6.fragment.PosterFragment
import com.example.hw6.model.MoviePreview

class MainActivity : AppCompatActivity(), PosterLoader {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        replaceFragment(PosterFragment(this))
    }

    private fun replaceFragment(fragment: Fragment) {
         supportFragmentManager.beginTransaction()
                .replace(binding.movieFragmentContainer.id, fragment).addToBackStack(null).commit()
        }

    override fun onPosterClicked(moviePreview: MoviePreview) {
        replaceFragment(MovieDetailsFragment(moviePreview))
    }

    override fun onBackPressed() {
        val backStackEntryCount = supportFragmentManager.backStackEntryCount
        if (backStackEntryCount == 1) {
            finishAffinity()
        } else {
            super.onBackPressed()
        }
    }
}