package com.example.nztrip

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class MyAdapter(private val context : Activity, private  val arrayList: ArrayList<Detail>) :
    ArrayAdapter<Detail>(context, R.layout.list_item,arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.list_item, null)

        val imageView : ImageView = view.findViewById(R.id.profile_pic)
        val username : TextView = view.findViewById(R.id.personalName)
        val lastMsg: TextView = view.findViewById(R.id.lastMessage)
        val lastMsgTime : TextView = view.findViewById(R.id.msgtime)

        imageView.setImageResource(arrayList[position].imageId)
        username.text = arrayList[position].name
        lastMsg.text = arrayList[position].lastMessage
        lastMsgTime.text = arrayList[position].lastMsgTime

        return view
    }
}