package com.quintype.oxygen.models.entities

import com.google.gson.annotations.SerializedName

data class MagazineResponse(
    @SerializedName("total")
    val total: Int = 0,
    @SerializedName("entities")
    val entities: List<EntitiesItem>?
)