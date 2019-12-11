package com.quintype.oxygen.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MenuItemModel(
    @SerializedName("color")
    @Expose
    var sectionColor: String? = null,
    @SerializedName("isSelected")
    @Expose
    var isSelected: String? = null,
    @SerializedName("name")
    @Expose
    var title: String? = null,
    @SerializedName("slug")
    @Expose
    var slug: String? = null,
    @SerializedName("image-url")
    @Expose
    var imageURL: String? = null
) : Parcelable