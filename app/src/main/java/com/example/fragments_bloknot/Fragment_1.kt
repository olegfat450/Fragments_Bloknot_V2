package com.example.fragments_bloknot

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class Fragment_1 : Fragment() {

        var namber = 0

            private lateinit var description: EditText




            lateinit var db: DbHelper

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_1, container, false) }

    val list: MutableList<Description> = mutableListOf()
         private lateinit var onFragment: OnFragment

    val adapter = MyAdapter(list)




    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("NotifyDataSetChanged", "SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


             onFragment = requireActivity() as OnFragment

        val formatDate = SimpleDateFormat("dd MMMM yy \n HH : mm", Locale.getDefault())
          description = view.findViewById<EditText>(R.id.descriptionText)
         val button = view.findViewById<Button>(R.id.button)
         val listTv = view.findViewById<RecyclerView>(R.id.listTv)
             listTv.layoutManager = LinearLayoutManager(this.context)

         db = DbHelper(this@Fragment_1.context,null)


                      //  db.removeAll()
            list.clear()
      list.addAll(db.readDescription()); namber = list.maxOfOrNull { it.number }?: 0


         listTv.adapter = adapter

            adapter.setOnItemClick(object: MyAdapter.OnItemClick{

                override fun onItemClick(description: Description, position: Int) {

                   val bulder = AlertDialog.Builder(this@Fragment_1.context)
                        bulder.setTitle("Редактирование")
                            .setPositiveButton("Редактировать"){_,_ ->  onFragment.onData(description.description,position) }
                            .setNegativeButton("Удалить") {_,_ ->  list.removeAt(position); adapter.notifyDataSetChanged(); db.deleteDescription(description)}
                            .setNeutralButton("Отмена") {_,_ -> }.create().show() } } )

                adapter.setOnItemCheck(object: MyAdapter.OnItemCheck{

                    override fun onItemCheck(check: Boolean, position: Int) {

                        if ( check == false ) { list[position].checkbox = true } else { list[position].checkbox = false }

                             db.updateDescription(list[position])
                         }


                } )



        button.setOnClickListener {
              if (description.text.isEmpty()) return@setOnClickListener

            val date = Date(); val list1 = Description(++namber,
            formatDate.format(date).toString(),description.text.toString(), false );
                 list += list1;
            db.addDescription(list1)
            description.text.clear(); adapter.notifyDataSetChanged()  }

    }

    override fun onResume() {
        super.onResume()

        val descr = arguments?.getString("key2")
        val pos = arguments?.getInt("keyid2",-1)

        if ((descr != null) and (pos != -1))  { list[pos!!].description = descr.toString(); adapter.notifyDataSetChanged()
           db.updateDescription(list[pos])


        }






    }


}

