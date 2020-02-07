package com.quintype.oxygen.models.bookmark

import com.google.gson.annotations.SerializedName

data class AllBookmarksResponse(@SerializedName("data") var data: ArrayList<DataItem>? = null)