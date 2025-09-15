package com.example.lab_week_03

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainerView
import com.example.lab_week_03.DetailFragment.Companion.COFFEE_AFFOGATO
import com.example.lab_week_03.DetailFragment.Companion.COFFEE_AMERICANO
import com.example.lab_week_03.DetailFragment.Companion.COFFEE_LATTE
import com.example.lab_week_03.ListFragment.CoffeeListener
import com.example.lab_week_03.R


class MainActivity : AppCompatActivity(), CoffeeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragment_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (savedInstanceState == null) {
            findViewById<FragmentContainerView>(R.id.fragment_container).let { containerLayout ->
                val listFragment = ListFragment()
                supportFragmentManager.beginTransaction()
                    .add(containerLayout.id, listFragment)
                    .commit()
            }
        }
    }

    override fun onSelected(id: Int) {
        findViewById<FragmentContainerView>(R.id.fragment_container).let { containerLayout ->
            val detailFragment = DetailFragment.newInstance(id)
            supportFragmentManager.beginTransaction()
                .replace(containerLayout.id, detailFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
