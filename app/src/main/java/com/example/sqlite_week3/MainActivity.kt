package com.example.sqlite_week3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
            myData.addMusic(title, artist, year)
            Toast.makeText(this, "You have added this song ", Toast.LENGTH_LONG).show()
        }
        btnSearch.setOnClickListener {
            val id = etId.text.toString().toLong()
            val data = myData.viewMusic(id)
            etTitle.setText(data?.title)
            etArtist.setText(data?.artist)
            etYear.setText(data?.year.toString())
            Toast.makeText ( this, "You have search for the song with id: " + id, Toast.LENGTH_LONG).show()
        }
        btnUpdate.setOnClickListener {
            val id = etId.text.toString().toLong()
            val title = etTitle.text.toString()
            val artist = etArtist.text.toString()
            val year = etYear.text.toString().toLong()
            myData.updateDb(id, title, artist, year)
        }
        btnDelete.setOnClickListener {
            val id = etId.text.toString().toLong()
            myData.delete(id)
        }
    }

}
