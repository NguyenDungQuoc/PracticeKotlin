package com.example.practicekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBar
import com.example.practicekotlin.Model.Food

class InfoProduct : AppCompatActivity() {

    var foodItem: Food?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infor_product)
        foodItem = intent?.getParcelableExtra("DATA")
               //khoi tao actionbar

        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = "Thông tin sản phẩm"
        title = actionBar?.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true);


    }

//    fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.toolbaritem, menu)
//        return true
//    }
}