package com.example.sqlite_week3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var myData: MyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar1)
        myData = MyDatabase(ctx = this)


        btnAdd.setOnClickListener {
            val title = etTitle.text.toString()
            val artist = etArtist.text.toString()
            val year = etYear.text.toString().toLong()
            val id = myData.addMusic(title, artist, year)


        }
        btnSearch.setOnClickListener {
            val findId = etId.text.toString().toLong()
            val data = myData.viewMusic(findId)
            etTitle.setText(data?.title)
            etArtist.setText(data?.artist)
        }
    }

}
