package com.quintype.oxygen.models.config.menugroups

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.NavMenu
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MenuItem(@SerializedName("name")
                    val name: String = "",
                    @SerializedName("id")
                    val id: Int = 0,
                    @SerializedName("items")
                    val items: List<NavMenu>?,
                    @SerializedName("slug")
                    val slug: String = ""):Parcelable