package com.quintype.oxygen.models.bookmark

import com.google.gson.annotations.SerializedName

data class BookmarkResponse(@SerializedName("data")
                            val data: Data?=null)