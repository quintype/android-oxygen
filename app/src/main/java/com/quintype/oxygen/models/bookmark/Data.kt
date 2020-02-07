package com.quintype.oxygen.models.bookmark

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("type")
    var type: String = "bookmark",
    @SerializedName("attributes")
    var attributes: Attributes? = null
)