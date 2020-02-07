package com.quintype.oxygen.models.bookmark

import com.google.gson.annotations.SerializedName

data class CreateBookmarkRequest(
    @SerializedName("data")
    var data: Data? = null
)