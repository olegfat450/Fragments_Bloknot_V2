package com.example.fragments_bloknot

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val description: MutableList<Description>): RecyclerView.Adapter<MyAdapter.UserViewHolder>() {

     private var onItemClick: OnItemClick? = null
     private var onItemCheck: OnItemCheck? = null

    interface OnItemClick{ fun onItemClick (description: Description,position: Int)}
    interface OnItemCheck{ fun onItemCheck (check: Boolean,position: Int)}

     class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

         val namber = itemView.findViewById<TextView>(R.id.namber_item)
         val date = itemView.findViewById<TextView>(R.id.date_item)
         val description = itemView.findViewById<TextView>(R.id.description_item)
         val checkbox = itemView.findViewById<CheckBox>(R.id.checkBox_item)


     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
           val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
            return UserViewHolder(itemView)
     }

     override fun getItemCount() = description.size



     @SuppressLint("SetTextI18n")
     override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

          holder.namber.text = "№: ${(description[position].number.toString()).padStart(3,'0')}"
          holder.date.text = description[position].date
          holder.description.setText(description[position].description)

         if ( description[position].checkbox == true ) holder.checkbox.isChecked = true else holder.checkbox.isChecked = false

          holder.itemView.setOnClickListener {
              if ( onItemClick != null) { onItemClick!!.onItemClick(description[position],position)}
          }
           holder.checkbox.setOnClickListener {
               if (onItemCheck != null) {  onItemCheck!!.onItemCheck(description[position].checkbox,position)}

           }

     }

    fun setOnItemClick(onItemClick: OnItemClick){
        this.onItemClick = onItemClick }

    fun setOnItemCheck(onItemCheck: OnItemCheck){
        this.onItemCheck = onItemCheck
    }

}