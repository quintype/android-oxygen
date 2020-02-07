package com.quintype.oxygen.models.bookmark

import com.google.gson.annotations.SerializedName

data class BulkBookmarksRequest(
    @SerializedName("data")
    val data: BulkData = BulkData()
)