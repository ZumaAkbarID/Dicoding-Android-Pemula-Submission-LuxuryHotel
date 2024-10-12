package com.rwa.luxuryhotel

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rwa.luxuryhotel.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val KEY_HOTEL = "key_hotel"
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataHotel = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Hotel>(KEY_HOTEL, Hotel::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Hotel>(KEY_HOTEL)
        }

        title = dataHotel?.name.toString()

        Glide.with(binding.imgCardItem.context).load(dataHotel?.photo).into(binding.imgCardItem)
        binding.tvHotelName.text = dataHotel?.name
        binding.tvHotelLocation.text = dataHotel?.location
        binding.tvHotelDescription.text = dataHotel?.description

        val btnShare = binding.actionShare
        btnShare.setOnClickListener {
            shareHotel(dataHotel!!)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.about_page -> {
                val profile = Intent(this@DetailActivity, AboutActivity::class.java)
                startActivity(profile)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun shareHotel(data: Hotel) {
        val body = """
            Hotel : ${data.name}
            Lokasi : ${data.location}
            
            Deskripsi :
            ${data.description}
            
            Dikirim dari Aplikasi Luxury Hotel
        """.trimIndent()

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, body)
            type = "text/plain"
        }

        try {
            startActivity(sendIntent)
        } catch (e: ActivityNotFoundException) {
           Toast.makeText(this, "Activity tidak ditemukan!", Toast.LENGTH_SHORT).show()
        }
    }
}