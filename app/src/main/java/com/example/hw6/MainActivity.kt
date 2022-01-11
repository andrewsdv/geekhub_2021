package com.example.hw6

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.Toast
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
        if (isNetworkAvailable(this)) Toast.makeText(this, "No Internet connection", Toast.LENGTH_SHORT).show()
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

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}