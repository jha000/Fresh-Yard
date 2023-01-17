package com.example.milkaggregatorapplication

import android.content.Context
import com.example.milkaggregatorapplication.User.product
import com.example.milkaggregatorapplication.User.name
import com.example.milkaggregatorapplication.User.mobile
import com.example.milkaggregatorapplication.User.address
import com.example.milkaggregatorapplication.User.amount
import com.example.milkaggregatorapplication.MyAdapter.MyViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.milkaggregatorapplication.R
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MyAdapter(context: Context?, var list: ArrayList<User>) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = list[position]
        holder.Product.text = user.product
        holder.name1.text = user.name
        holder.mobile1!!.text = user.mobile
        holder.address1!!.text = user.address
        holder.amount.text = user.amount
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Product: TextView
        var amount: TextView
        var name1: TextView
        var mobile1: TextView? = null
        var address1: TextView? = null

        init {
            Product = itemView.findViewById(R.id.tvfirstName)
            amount = itemView.findViewById(R.id.tvage)
            name1 = itemView.findViewById(R.id.nameid)
            //
//            mobile1 = itemView.findViewById(R.id.mobileid);
//
//            address1 = itemView.findViewById(R.id.addressid);
        }
    }
}