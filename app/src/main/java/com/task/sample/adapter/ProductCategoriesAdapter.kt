package com.task.sample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonArray
import com.task.sample.R

class ProductCategoriesAdapter(
    private var listArray: JsonArray? = null,
    private var clickListener: View.OnClickListener
) : RecyclerView.Adapter<ProductCategoriesAdapter.MyViewHolder>() {

    class MyViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val tvCategoryName: TextView = itemView.findViewById(R.id.tvCategoryName)
    }

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_category_item, parent, false)
        return MyViewHolder(itemView)
    }

    override
    fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = listArray?.get(position)
        var item = listItem?.asString
        item = item?.substring(0, 1)?.uppercase() + item?.substring(1)?.lowercase()
        holder.tvCategoryName.text = item
        holder.itemView.setOnClickListener(clickListener)
    }

    fun setData(listArray: JsonArray) {
        this.listArray = listArray
        notifyDataSetChanged()
    }

    override
    fun getItemCount(): Int {
        return listArray?.size() ?: 0
    }

}