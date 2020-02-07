package com.quintype.oxygen.models.bookmark

import com.google.gson.annotations.SerializedName

data class BulkBookmarksResponse(
    @SerializedName("data")
    val data: List<BulkData>?
)