package com.example.fragments_bloknot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction


class Fragment_2 : Fragment(),OnFragment {
                     private lateinit var onFragment: OnFragment
                     private lateinit var button: Button
                     private lateinit var textTv: EditText
                     private var description: String? = null
                     private var positoin: Int = 0

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

           onFragment = requireActivity() as OnFragment
           val view = inflater.inflate(R.layout.fragment_2,container,false)

               button = view.findViewById(R.id.button)
               textTv = view.findViewById(R.id.textTv)

                description = arguments?.getString("key1")
                positoin = arguments?.getInt("keyid",0)!!

                 textTv.setText(description.toString())



                 button.setOnClickListener { onData(textTv.text.toString(),positoin) }



           return view
    }

    override fun onData(data: String,position: Int) {

         val bundle = Bundle()
             bundle.putString("key2",data)
             bundle.putInt("keyid2",position)

        val transaction = this.fragmentManager?.beginTransaction()
        val fragment1 = Fragment_1()
        fragment1.arguments = bundle

        transaction?.replace(R.id.fragment_container,fragment1)
        transaction?.addToBackStack(null)
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction?.commit()
    }


}
