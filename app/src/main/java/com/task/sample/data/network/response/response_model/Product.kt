package com.task.sample.data.network.response.response_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.task.sample.data.db.product_table
import java.io.Serializable

@Entity(tableName = product_table)
class Product : Serializable {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "id")
    var id: Int? = null

    @Ignore
    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("price")
    @Expose
    @ColumnInfo(name = "price")
    var price: Double? = null

    @Ignore
    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("category")
    @Expose
    @ColumnInfo(name = "category")
    var category: String? = null

    @SerializedName("image")
    @Expose
    @ColumnInfo(name = "image")
    var image: String? = null

    @ColumnInfo(name = "count")
    var count: Int? = null

    @ColumnInfo(name = "totalPrice")
    var totalPrice: Double? = null

    @Ignore
    @SerializedName("rating")
    @Expose
    var rating: Rating? = null
}