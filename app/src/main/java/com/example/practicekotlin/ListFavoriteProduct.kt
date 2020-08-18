package com.example.practicekotlin

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.practicekotlin.Common.Common
import com.example.practicekotlin.Interface.RetrofitService
import com.example.practicekotlin.Model.Food
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_infor_product.*
import kotlinx.android.synthetic.main.activity_list_favorite_product.*
import kotlinx.android.synthetic.main.activity_main.*

class ListFavoriteProduct : AppCompatActivity() {

    private var sharedPre: SharedPreferences? = null
    private var prefsEditor: SharedPreferences.Editor? = null
    lateinit var adapter: CustomAdapter
    private var lstId: MutableList<Int>? = null
    private var listIdString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_favorite_product)

        createActionBar()
        createCycleView()

        //list favorite đã lưu từ InfoProductActiviti
        sharedPre = getSharedPreferences("LIST_CHECKED", Activity.MODE_PRIVATE)
        prefsEditor = sharedPre?.edit()
        listIdString = sharedPre?.getString("LIST_ID", "") ?: ""
        lstId = Gson().fromJson<MutableList<Int>>(
            listIdString,
            object : TypeToken<MutableList<Int>>() {}.type
        ) ?: mutableListOf()
        //list Food từ truyền từ MainActivity
        val listItem: MutableList<Food> = intent?.getParcelableArrayListExtra<Food>("DATA") ?: mutableListOf()
        val lstResult: MutableList<Food> = mutableListOf()//khoi tao 1 list mới de luu dữ liệu
        for(i in listItem){//so sanh id 2 list
            for( id in (lstId ?: mutableListOf())){
                if(id == i.id) {//neu id của list
                    lstResult.add(i)
                }
            }
        }

        adapter = CustomAdapter(baseContext, lstResult)//list can show
        adapter.onClick = {
            val intent: Intent = Intent(this@ListFavoriteProduct, InfoProduct::class.java)
            intent.putExtra("DATA",  it)
            startActivity(intent)
        }
        recycle_view1.adapter = adapter //do du lieu len recycle view


}

    private fun createCycleView() {
//        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel::class.java)
//        mService = Common.retrofitService.create(RetrofitService::class.java)
        recycle_view1.layoutManager = GridLayoutManager(this, 2)
        recycle_view1.setHasFixedSize(true)
    }

    private fun createActionBar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = "Sản phẩm yêu thích"
        title = actionBar?.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.ic_favorite -> {
                item.setIcon(R.drawable.ic_heart_click)
                //invalidateOptionsMenu()
            }
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return true
    }

}