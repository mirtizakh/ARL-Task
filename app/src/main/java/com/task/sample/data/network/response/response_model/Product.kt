package com.task.sample.data.network.response.response_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Product:Serializable{
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("price")
    @Expose
    var price: Double? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("category")
    @Expose
    var category: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null

    var count: Int? = null

    @SerializedName("rating")
    @Expose
    var rating: Rating? = null
}