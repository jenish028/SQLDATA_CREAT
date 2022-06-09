package com.example.sqldata_creat

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DB_Helper(
    context: Context?,
) : SQLiteOpenHelper(context, "RNW.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {

        var quary =
            "CREATE TABLE student (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,mobile TEXT,std TEXT)"

        db!!.execSQL(quary)


    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun insertData(n1: String, m1: String, s1: String) {


        var db = writableDatabase
        var cv = ContentValues()

        cv.put("name", n1)
        cv.put("mobile", m1)
        cv.put("std", s1)

        var res = db.insert("student", null, cv)


        println(res)


    }

    @SuppressLint("Range")
    fun readData(): ArrayList<ModelData>

    {
        var list = arrayListOf<ModelData>()
        var db = readableDatabase
//        var query="SELECT * FROM student WHERE std = $value"
        var query = "SELECT * FROM student"

        var cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {

                var id = cursor.getString(cursor.getColumnIndex("id"))
                var name = cursor.getString(cursor.getColumnIndex("name"))
                var mobile = cursor.getString(cursor.getColumnIndex("mobile"))
                var std = cursor.getString(cursor.getColumnIndex("std"))

                var m1 = ModelData(id, name, mobile, std)

                list.add(m1)

            } while (cursor.moveToNext())

        }
        return list
    }

    fun deletData(id: String) {
        var db = writableDatabase
        db.delete("student", "id = $id", null)

    }

    fun updateData(id: String, name: String, mobile: String, std: String) {

        var db = writableDatabase

        var cv = ContentValues()
        cv.put("name",name)
        cv.put("mobile",mobile)
        cv.put("std",std)

        db.update("student",cv,"id = $id",null)

    }

}