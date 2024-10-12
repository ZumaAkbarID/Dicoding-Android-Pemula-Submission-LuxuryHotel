package com.rwa.luxuryhotel

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rwa.luxuryhotel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvHotels: RecyclerView
    private val list = ArrayList<Hotel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvHotels = binding.rvHotels
        rvHotels.setHasFixedSize(true)

        list.addAll(getListHotels())
        showRecyclerList()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        when(newConfig.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                rvHotels.layoutManager = LinearLayoutManager(this)
            }
            Configuration.ORIENTATION_LANDSCAPE -> {
                rvHotels.layoutManager = GridLayoutManager(this, 2)
            }
            else -> {
                Toast.makeText(this, "Error Orientation", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showRecyclerList() {
        rvHotels.layoutManager = LinearLayoutManager(this)
        val listHotelAdapter = ListHotelAdapter(list)
        rvHotels.adapter = listHotelAdapter
    }

    private fun getListHotels(): ArrayList<Hotel> {
        val dataName = resources.getStringArray(R.array.hotel_names)
        val dataLocation = resources.getStringArray(R.array.hotel_regions)
        val dataDescription = resources.getStringArray(R.array.hotel_descriptions)
        val dataPhoto = resources.getStringArray(R.array.hotel_images)
        val listHotel = ArrayList<Hotel>()
        for (i in dataName.indices) {
            val hotel = Hotel(dataName[i], dataLocation[i], dataPhoto[i], dataDescription[i])
            listHotel.add(hotel)
        }
        return listHotel
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.about_page -> {
                val profile = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(profile)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}