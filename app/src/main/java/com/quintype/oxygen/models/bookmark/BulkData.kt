package com.quintype.oxygen.models.bookmark

import com.google.gson.annotations.SerializedName

data class BulkData(
    @SerializedName("type")
    var type: String = "bookmark",
    @SerializedName("attributes")
    var attributes: ArrayList<AttributesItem>? = ArrayList()
)