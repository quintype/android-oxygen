package com.quintype.oxygen.models.latest.home.magazines

import com.google.gson.annotations.SerializedName

data class MagazinesItem(
    @SerializedName("entitySlug")
    val entitySlug: String = "",
    @SerializedName("color")
    val color: String? = null,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("entityId")
    val entityId: Int = 0,
    @SerializedName("issues")
    val issues: ArrayList<IssuesItem>?
)