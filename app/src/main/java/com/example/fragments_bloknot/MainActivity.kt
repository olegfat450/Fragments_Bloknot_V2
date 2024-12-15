package com.example.fragments_bloknot

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

     private lateinit var toolbar: Toolbar

     val list: MutableList<Description> = mutableListOf()


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setBackgroundColor(getColor(R.color.color2))
        toolbar.setTitleTextColor(Color.BLUE)
        title = "Мои заметки"

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_exit,menu)
        return super.onCreateOptionsMenu(menu) }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
          val builder = AlertDialog.Builder(this)
            builder.setTitle("Выход из программы")
               .setPositiveButton("Да") { _,_ -> finishAffinity() }
               .setNegativeButton("нет") { _,_ -> }.create().show()
        return super.onOptionsItemSelected(item) }
}