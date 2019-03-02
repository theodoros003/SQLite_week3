package com.example.sqlite_week3

import android.app.Person
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.FontsContract

data class Song(val id: Long, val title: String, val artist: String, val year: Long)

class MyDatabase(ctx: Context) : SQLiteOpenHelper(ctx,"MusicDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL ("CREATE TABLE IF NOT EXISTS Music (Id INTEGER PRIMARY KEY, Title VARCHAR(255), Artist VARCHAR(255), Year LONG)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion:Int, newVersion:Int) {
        db.execSQL ("DROP TABLE IF EXISTS Music")
        onCreate(db)
    }

    fun addMusic(title: String, artist: String, year: Long) : Long{
        val db = writableDatabase
        val stmt = db.compileStatement("INSERT INTO Music (Title, Artist, Year) VALUES (?, ?, ?)");
        stmt.bindString(1, title)
        stmt.bindString(2, artist)
        stmt.bindLong(3, year)
        val id = stmt.executeInsert()
        return id
    }

    fun viewMusic(id: Long) : Song? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Music WHERE Id=?", arrayOf(id.toString()) )
        if (cursor.moveToFirst()){
            val p = Song(cursor.getLong(cursor.getColumnIndex("Id")),
                    cursor.getString(cursor.getColumnIndex("Title")),
                    cursor.getString(cursor.getColumnIndex("Artist")),
                    cursor.getLong((cursor.getColumnIndex("Year"))))
            cursor.close()
            return p
        }
        cursor.close()
        return null;
    }
}