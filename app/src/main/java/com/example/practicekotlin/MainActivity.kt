package com.example.practicekotlin

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicekotlin.Common.Common
import com.example.practicekotlin.Interface.RetrofitService
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {
    lateinit var dialog: AlertDialog
    lateinit var mService: RetrofitService
    lateinit var adapter: CustomAdapter
    private lateinit var foodViewModel: FoodViewModel

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //khoi tao actionbar
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = "Thông tin sản phẩm"
        title = actionBar?.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true);



        foodViewModel =  ViewModelProviders.of(this).get(FoodViewModel::class.java)
        mService = Common.retrofitService.create(RetrofitService::class.java)

        //khoi tao layout
        recycle_view.layoutManager = GridLayoutManager(this, 2)
        recycle_view.setHasFixedSize(true)

        dialog = SpotsDialog.Builder().setCancelable(false).setContext(this).build()!!
        adapter = CustomAdapter(baseContext,  mutableListOf())
        adapter.onClick = {
             val intent: Intent = Intent(this@MainActivity, InfoProduct::class.java)
            intent.putExtra("DATA", it)
            startActivity(intent)
        }
        recycle_view.adapter = adapter


        foodViewModel.result.observe(this, Observer {
           adapter.setData(it.result)
            dialog.dismiss()

        })

        foodViewModel.errorMessage?.observe(this, Observer {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("khong the tai du lieu")
            builder.setPositiveButton("OK",DialogInterface.OnClickListener { dialog, i -> finish() })
            dialog = builder.create()
            dialog.show()

        })


        getAllProducts()


    }

    private fun getAllProducts() {
        dialog.show()
        foodViewModel.getProduct()
//        imgViewFood.setOnClickListener {
//            val intent: Intent = Intent(this@MainActivity, InforProduct::class.java)
//            startActivity(intent)
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbaritem, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.ic_favorite -> {
                Log.d("MainActivity", "onOptionsItemSelected" )
            }
        }
        return true
    }


}