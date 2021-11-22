package com.task.sample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.task.sample.R
import com.task.sample.app.AppController
import com.task.sample.data.network.response.response_model.Product
import com.task.sample.util.empty

class ProductListAdapter(
    private var listArray: ArrayList<Product>? = null,
    private var clickListener: View.OnClickListener
) : RecyclerView.Adapter<ProductListAdapter.MyViewHolder>() {

    class MyViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val tvCategoryName: TextView = itemView.findViewById(R.id.tvCategoryName)
        val textDetails: TextView = itemView.findViewById(R.id.textDetails)
        val icProduct: ImageView = itemView.findViewById(R.id.icProduct)
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
        val item = listItem?.title + "\nPrice: " + listItem?.price + " Rs"
        holder.tvCategoryName.text = item
        holder.textDetails.text = holder.itemView.context.getString(R.string.view_product)
        loadProduct(listItem?.image ?: String.empty, holder.icProduct)
        holder.itemView.setOnClickListener(clickListener)
    }

    fun setData(listArray: ArrayList<Product>) {
        this.listArray = listArray
        notifyDataSetChanged()
    }

    private fun loadProduct(imageUrl: String, imageView: ImageView) {
        Glide.with(AppController.applicationContext())
            .load(imageUrl)
            .thumbnail(0.3f)
            .centerCrop()
            .placeholder(R.drawable.ic_products)
            .error(R.drawable.ic_products)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }

    override
    fun getItemCount(): Int {
        return listArray?.size ?: 0
    }

}