package com.quintype.oxygen.models.config

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.NavMenu
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ConfigLayout(
    @SerializedName("stories-between-stacks")
    @Expose
    var storiesBetweenStacks: Int? = null,
    @SerializedName("menu")
    @Expose
    var menu: List<NavMenu>? = null,
    @SerializedName("stacks")
    @Expose
    var stacks: List<ConfigLayoutStack>? = null
) : Parcelable {
}
