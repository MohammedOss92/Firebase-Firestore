package com.sarrawi.firebasefirestore

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private var con:Context,private var userList: ArrayList<User>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder (ItemView:View) : RecyclerView.ViewHolder(ItemView) {

        val firstName : TextView = ItemView.findViewById(R.id.tvfirstName)
        val lastName : TextView = ItemView.findViewById(R.id.tvlastName)
        val age : TextView = ItemView.findViewById(R.id.tvage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user : User = userList[position]
//        holder.firstName.text = user.firstName
        holder.firstName.text = user.firstName
        holder.lastName.text = user.lastName
        holder.age.text = user.age.toString()
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}