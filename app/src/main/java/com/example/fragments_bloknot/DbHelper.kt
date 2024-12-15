package com.example.fragments_bloknot

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase.CursorFactory

class DbHelper(context: Context?, factory: CursorFactory?): SQLiteOpenHelper(context,DATADASE_NAME,factory,DATABASE_VERSION) {

    companion object{
                      private val DATADASE_NAME = "DESCRIPTION_DATABASE4"
                      private val DATABASE_VERSION = 2
                              val TABLE_NAME = "description_table4"
                              val KEY_ID = "number"
                              val KEY_DATE = "date"
                              val KEY_DESCRIPTION = "description"
                              val KEY_CHECK = "checkbox"

    }

    override fun onCreate(db: SQLiteDatabase?) {
                                                    val query = ("CREATE TABLE " + TABLE_NAME + " (" +
                                                                KEY_ID + " INTEGER PRIMARY KEY, " +
                                                                KEY_DATE + " TEXT, " +
                                                                KEY_DESCRIPTION + " TEXT, " +
                                                                KEY_CHECK + " TEXT " + ") " )
                                                      db?.execSQL(query) }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
                                                         db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
                                                        onCreate(db)
    }

                             fun addDescription(description: Description){
                                                  val db = this.writableDatabase
                                                  val values = ContentValues()
                                                  values.put(KEY_ID,description.number)
                                                  values.put(KEY_DATE,description.date)
                                                  values.put(KEY_DESCRIPTION,description.description)
                                                  values.put(KEY_CHECK,description.checkbox.toString())
                                                  db.insert(TABLE_NAME,null,values)
                                         db.close() }

                             @SuppressLint("Range", "SuspiciousIndentation")
                             fun readDescription(): MutableList<Description>{
                                 val list: MutableList<Description> = mutableListOf()
                                 val selectQuery = "SELECT * FROM $TABLE_NAME"
                                 val db = this.readableDatabase
                                  var cursor: Cursor? = null

                                                          try { cursor = db.rawQuery(selectQuery,null) } catch (e: SQLException) { db.execSQL(selectQuery); return list}

                                               var namber: Int
                                               var date: String
                                               var description: String
                                               var checkbox: String
                                               var check1: Boolean = false

                                               if (cursor.moveToFirst()){
                                                                           do{
                                                                               namber = cursor.getInt(cursor.getColumnIndex("number"))
                                                                               date = cursor.getString(cursor.getColumnIndex("date"))
                                                                               description = cursor.getString(cursor.getColumnIndex("description"))
                                                                               checkbox = cursor.getString(cursor.getColumnIndex("checkbox"))
                                                                             if (checkbox == "true") check1 = true else check1 = false
                                                                               list.add(Description(namber,date,description,check1))

                                                                           } while (cursor.moveToNext()) }
                                                                     return list }

               fun removeAll() {val db = this.writableDatabase; db.delete(TABLE_NAME,null,null)}

               fun deleteDescription(description: Description){
                   val db = this.writableDatabase
                   val values = ContentValues()
                   values.put(KEY_ID,description.number)
                   db.delete(TABLE_NAME, "number=" + description.number,null)
                   db.close() }

               fun updateDescription(description: Description) {

                   val db = this.writableDatabase
                   val values = ContentValues()

                   values.put(KEY_ID,description.number)
                   values.put(KEY_DATE,description.date)
                   values.put(KEY_DESCRIPTION,description.description)
                   values.put(KEY_CHECK,description.checkbox.toString())
                   db.update(TABLE_NAME,values,"number=" + description.number,null)
                   db.close()
               }
















}
