package com.example.practicekotlin

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.practicekotlin.Model.Food
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_infor_product.*


class InfoProduct : AppCompatActivity() {

    private var menu: Menu? = null
    private var foodItem: Food? = null
    private var sharedPre: SharedPreferences? = null
    private var prefsEditor: SharedPreferences.Editor? = null
    private var listIdString: String? = null
    private var lstId: MutableList<Int>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infor_product)

        getDataFromMainAct()
        createActionBar()
        getDataFromSharedPrefs()


    }

    private fun getDataFromSharedPrefs() {
        //tao 1 list để lưu giữ liệu lấy từ ShareReference
        sharedPre = getSharedPreferences("LIST_CHECKED", Activity.MODE_PRIVATE)
        prefsEditor = sharedPre?.edit()
        listIdString = sharedPre?.getString("LIST_ID", "") ?: ""
        lstId = Gson().fromJson<MutableList<Int>>(
            listIdString,
            object : TypeToken<MutableList<Int>>() {}.type
        ) ?: mutableListOf()
        //Khoi tao bien isLike bang cach lay gia tri cu tu ShareReference
        var sharedPre: SharedPreferences = getSharedPreferences("LIST_CHECKED", MODE_PRIVATE)
        val productJsonArray: String? = sharedPre?.getString("LIST_CHECKED", "")
        for (id in (lstId ?: mutableListOf())) {
            if (id == foodItem?.id) {
                foodItem?.isLike = true
//                item?.setIcon(R.drawable.ic_heart_click)
            }
        }
    }

    private fun getDataFromMainAct() {
        foodItem = intent?.getParcelableExtra("DATA")
        Glide.with(imgProduct.context).load(foodItem?.url).into(imgProduct)
        textNameProduct.text = foodItem?.title
        textPriceProduct.text = foodItem?.price.toString()
    }

    private fun createActionBar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = "Thông tin sản phẩm"
        title = actionBar?.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbaritem, menu)
        this.menu = menu
        if (foodItem?.isLike == true) {
            val item = menu?.getItem(0)
            item?.setIcon(R.drawable.ic_heart_click)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ic_favorite -> {
                foodItem?.isLike = !(foodItem?.isLike ?: false)
                var idCheck: Int? = foodItem?.id
                if (foodItem?.isLike == true) {
                    item.setIcon(R.drawable.ic_heart_click)
                } else {
                    item.setIcon(R.drawable.ic_heart)
                }

                if (foodItem?.isLike == true) {
                    lstId?.add(foodItem?.id ?: 0)//them 1 id item ShareReference
                } else {
                    for (id in (lstId ?: mutableListOf())) {
                        if (id == foodItem?.id) {
                            lstId?.remove(id)//go bỏ ra khỏi list ShareReference
                            break
                        }
                    }
                }
                prefsEditor?.putString("LIST_ID", Gson().toJson(lstId))
                prefsEditor?.apply()
            }
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }


}