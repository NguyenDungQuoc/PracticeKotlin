package com.example.practicekotlin

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
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
import com.example.practicekotlin.Model.Food
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var dialog: AlertDialog
    lateinit var mService: RetrofitService
    lateinit var adapter: CustomAdapter
    private lateinit var foodViewModel: FoodViewModel
    private var menu: Menu? = null


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createActionBar()

        createCycleView()

        dialog = SpotsDialog.Builder().setCancelable(false).setContext(this).build()!!
        //show list san pham
        adapter = CustomAdapter(baseContext, mutableListOf()) //list can show
        //click san pham show thong tin san pham
        adapter.onClick = {
            val intent: Intent = Intent(this@MainActivity, InfoProduct::class.java)
            intent.putExtra("DATA", it)
            startActivity(intent)
        }
        recycle_view.adapter = adapter

        resultOfViewModel()

        getAllProducts()

    }

    private fun createCycleView() {
        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel::class.java)
        mService = Common.retrofitService.create(RetrofitService::class.java)
        recycle_view.layoutManager = GridLayoutManager(this, 2)
        recycle_view.setHasFixedSize(true)
    }

    private fun resultOfViewModel() {
        foodViewModel.result.observe(this, Observer {
            adapter.setData(it.result)
            dialog.dismiss()

        })

        foodViewModel.errorMessage?.observe(this, Observer {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("khong the tai du lieu")
            builder.setPositiveButton(
                "OK",
                DialogInterface.OnClickListener { dialog, i -> finish() })
            dialog = builder.create()
            dialog.show()

        })
    }

    private fun createActionBar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = "Thông tin sản phẩm"
        title = actionBar?.title
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
        menuInflater.inflate(R.menu.menu_product, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item?.itemId) {
            R.id.ic_list_favorite -> {
                val intent: Intent = Intent(this@MainActivity, ListFavoriteProduct::class.java)
            intent.putParcelableArrayListExtra("DATA", ArrayList(foodViewModel.result.value?.result ?: mutableListOf()))
                    startActivity(intent)
        }
        }
        return true
    }


}