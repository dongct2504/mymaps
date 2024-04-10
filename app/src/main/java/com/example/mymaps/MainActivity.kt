package com.example.mymaps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymaps.activities.DisplayMapActivity
import com.example.mymaps.adapters.MapsAdapter
import com.example.mymaps.databinding.ActivityMainBinding
import com.example.mymaps.models.Place
import com.example.mymaps.models.UserMap

const val EXTRA_USER_MAP = "EXTRA_USER_MAP"
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userMaps: List<UserMap> = generateSampleData()

        // set adapter on the recycler view
        // binding.rvMaps.adapter = MapsAdapter(this, emptyList<UserMap>())
        binding.rvMaps.adapter = MapsAdapter(this, userMaps, object : MapsAdapter.OnClickListener {
            override fun onItemClick(position: Int) {
                Log.i(TAG, "onItemClick $position")

                // when user taps on view in RV, navigate to new activity
                val i = Intent(this@MainActivity, DisplayMapActivity::class.java)
                i.putExtra(EXTRA_USER_MAP, userMaps[position])
                startActivity(i)
            }
        })

        // set layout manager on the recycler view
        binding.rvMaps.layoutManager = LinearLayoutManager(this)

        binding.fabCreateMap.setOnClickListener {
            Log.i(TAG, "tap on FAB")
        }
    }

    private fun generateSampleData(): List<UserMap> {
        return listOf(
            UserMap(
                "test user map title 1",
                listOf(
                    Place("test place title 1", "test place desc 1", 37.426, -122.163),
                    Place("test place title 2", "test place desc 2", 37.430, -122.173),
                    Place("test place title 3", "test place desc 3", 37.444, -122.170)
                )
            ),
            UserMap(
                "test user map title 2",
                listOf(
                    Place("test place title 4", "test place desc 4", 35.67, 139.65),
                    Place("test place title 5", "test place desc 5", 23.34, 85.31),
                    Place("test place title 6", "test place desc 6", 1.35, 103.82)
                )
            )
        )
    }
}