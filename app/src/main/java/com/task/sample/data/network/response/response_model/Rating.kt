package com.task.sample.data.network.response.response_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Rating: Serializable {
    @SerializedName("rate")
    @Expose
    var rate: Double? = null

    @SerializedName("count")
    @Expose
    var count: Int? = null
}