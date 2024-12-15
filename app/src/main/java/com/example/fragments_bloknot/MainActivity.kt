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
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(),OnFragment {

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

        if ( savedInstanceState == null) {supportFragmentManager.beginTransaction()
                               .add(R.id.fragment_container,Fragment_1())
                               .commit()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_exit,menu)
        return super.onCreateOptionsMenu(menu) }

    @SuppressLint("SuspiciousIndentation")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
           val builder = AlertDialog.Builder(this)
            builder.setTitle("Выход из программы")
               .setPositiveButton("Да") { _,_ -> finishAffinity() }
               .setNegativeButton("нет") { _,_ -> }.create().show()
        return super.onOptionsItemSelected(item) }

    override fun onData(data: String,position: Int) {

        val bundle = Bundle()
        bundle.putString("key1",data)
        bundle.putInt("keyid",position)
        val transaction = this.supportFragmentManager.beginTransaction()
        val fragment2 = Fragment_2()
        fragment2.arguments = bundle

        transaction.replace(R.id.fragment_container,fragment2)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()

    }

















}