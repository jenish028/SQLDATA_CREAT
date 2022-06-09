package com.example.sqldata_creat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqldata_creat.databinding.ActivityMainBinding as ActivityMainBinding1

class MainActivity : AppCompatActivity() {
    lateinit var blinding: ActivityMainBinding1
    var list = arrayListOf<ModelData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_main)

        blinding = ActivityMainBinding1 .inflate(layoutInflater)
        setContentView(blinding.root)


        var db = DB_Helper(this)

        blinding.btnCreat.setOnClickListener {
            db.insertData(
                blinding.nameEdt.text.toString(),
                blinding.mobileEdt.text.toString(),
                blinding.StdEdt.text.toString()
            )

            list = db.readData()
            setUpRv(list)

        }

        blinding.btnRead.setOnClickListener {

            list = db.readData()
            setUpRv(list)

        }

    }



    fun setUpRv(l1: ArrayList<ModelData>) {


        var adpter = Home_Adpter(this, l1)
        var lm = LinearLayoutManager(this)
        blinding.rvHome.layoutManager = lm
        blinding.rvHome.adapter=adpter


    }

}