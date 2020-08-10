package com.example.practicekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val foodList = generateDummyList(7)
        recycle_view.adapter = CustomAdapter(foodList)
        recycle_view.layoutManager =  LinearLayoutManager(this)
        recycle_view.setHasFixedSize(true)

         setSupportActionBar(findViewById(R.id.toolbar))

//        var arrayfood: ArrayList<Food> = ArrayList()
//        arrayfood.add(Food("Cherry", R.drawable.cherries,15000))
//        arrayfood.add(Food("Banh Socola", R.drawable.brownie,15000))
//        arrayfood.add(Food("Banh Bong", R.drawable.sweet,15000))
//        arrayfood.add(Food("Banh Trai Cay", R.drawable.fruittart,15000))
//        arrayfood.add(Food("Banh Trung", R.drawable.pastrydish,15000))
//        arrayfood.add(Food("Banh Cuon", R.drawable.hunterpastry,15000))
//        listView.adapter = CustomAdapter(this@MainActivity, arrayfood)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbaritem, menu)
//        val searchItem = menu?.findItem(R.id.app_bar_search)
//        val searchView = searchItem?.actionView as SearchView

        return super.onCreateOptionsMenu(menu)
    }

    private fun generateDummyList(size: Int): List<Food> {
        val list = ArrayList<Food>()

        for(i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.cherries
                1-> R.drawable.hunterpastry
                2 -> R.drawable.pastrydish
                3-> R.drawable.sweet
                4 -> R.drawable.brownie
                else -> R.drawable.fruittart
            }
            val item = Food("Mon $i", drawable, 15000 )
            list += item
        }
        return list
    }


}