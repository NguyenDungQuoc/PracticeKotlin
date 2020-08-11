package com.example.practicekotlin

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.practicekotlin.Common.Common
import com.example.practicekotlin.Interface.RetrofitService
import com.example.practicekotlin.Model.Food
import com.example.practicekotlin.Model.FoodModel
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    //    private val foodList = generateDummyList(20)
    lateinit var dialog: AlertDialog
    lateinit var mService: RetrofitService
    lateinit var  adapter : CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        mService = Common.retrofitService.create(RetrofitService::class.java)

        recycle_view.layoutManager =  GridLayoutManager(this, 2)
        recycle_view.setHasFixedSize(true)

        dialog =  SpotsDialog.Builder().setCancelable(false).setContext(this).build()!!
        getAllProducts()

    }

    private fun getAllProducts() {
        dialog.show()

        mService.getAllProducts().enqueue(object : Callback<FoodModel> {

            override fun onResponse(call: Call<FoodModel>, response: Response<FoodModel>) {
                adapter = CustomAdapter(baseContext, response.body()?.result ?: mutableListOf())
                adapter.notifyDataSetChanged()
                recycle_view.adapter = adapter

                dialog.dismiss()
        }

            override fun onFailure(call: Call<FoodModel>, t: Throwable) {

            }

        })
    }

//    fun insertItem(view : View) {
//        val index: Int = Random.nextInt(8)
//
//        val newItem = Food(
//            "Banh hoi",
//            R.drawable.hunterpastry,
//            15000
//        )
//
//        foodList.add(index, newItem)
//        adapter.notifyItemInserted(index)
//    }
//
//    fun removeItem(view : View) {
//        val index: Int = Random.nextInt(8)
//
//        foodList.removeAt(index)
//        adapter.notifyItemRemoved(index)
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.toolbaritem, menu)
////        val searchItem = menu?.findItem(R.id.app_bar_search)
////        val searchView = searchItem?.actionView as SearchView
//
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    private fun generateDummyList(size: Int): MutableList<Food> {
//        val list = mutableListOf<Food>()
//
//        for(i in 0 until size) {
//            val drawable = when (i % 3) {
//                0 -> R.drawable.cherries
//                1-> R.drawable.hunterpastry
//                2 -> R.drawable.pastrydish
//                3-> R.drawable.sweet
//                4 -> R.drawable.brownie
//                else -> R.drawable.fruittart
//            }
//            val item = Food("Mon $i", drawable, 15000 )
//            list += item
//        }
//        return list
//    }


}