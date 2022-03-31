package com.example.pref1app2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import com.example.pref1app2.databinding.ActivityMainBinding


val navItemsList: MutableList<Int> = mutableListOf<Int>()

// TODO Geri Iki kere basinca geri gidiyor
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Yeni toolbar
        val newToolbar = binding.toolbar
        newToolbar.title = ""
        setSupportActionBar(newToolbar)

        binding.drawerButton.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        // Hamburger icon
//        val toggle = ActionBarDrawerToggle(
//            this,
//            binding.drawerLayout,
//            R.string.open_drawer,
//            R.string.close_drawer
//        )
//        toggle.syncState()
//        binding.drawerLayout.addDrawerListener(toggle)


        findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.scheduleFragment)
        binding.navigationBar.setItemSelected(R.id.nav_schedule, true)
        navItemsList?.add(R.id.nav_schedule)



        binding.navigationBar.setOnItemSelectedListener {
            selectItemFromNav(it)
            navItemsList?.add(it)
        }

    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
            navItemsList?.getOrNull(navItemsList.size - 2)?.let {
                updateBottomNav(it)
                navItemsList.removeLast()
            }
        }
    }


    private fun updateBottomNav(id: Int) {
        when (id) {
            R.id.nav_schedule -> {
                binding.navigationBar.setItemSelected(R.id.nav_schedule, true)
            }
            R.id.nav_teams -> {
                binding.navigationBar.setItemSelected(R.id.nav_teams, true)
            }
            R.id.nav_standings -> {
                binding.navigationBar.setItemSelected(R.id.nav_standings, true)

            }
        }

    }

    private fun selectItemFromNav(id: Int) {
        when (id) {
            R.id.nav_schedule -> {
                findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.scheduleFragment)
            }
            R.id.nav_teams -> {
                findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.teamsFragment)
            }
            R.id.nav_standings -> {
                findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.standingsFragment)

            }
        }
    }


}