package com.example.kontaktownia

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kontaktownia.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val ImageButton = binding.imageButton3
        val MaterialToolbar = binding.materialToolbar
        val textView = binding.wyszukiwanie
        szukanie(ImageButton, textView)
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_wybieranie, R.id.navigation_kontakty, R.id.navigation_dodajkontakt
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        println("Aktualna zakladka")
        navController.addOnDestinationChangedListener{
            controller, destination, arguments -> println(destination.label)
            if(destination.label == "Lista") {
                ImageButton.visibility = View.VISIBLE
                MaterialToolbar.visibility = View.VISIBLE

            } else {
                ImageButton.visibility = View.GONE
                MaterialToolbar.visibility = View.GONE
                textView.visibility = View.GONE
            }
        }


        navView.setupWithNavController(navController)

    }
}