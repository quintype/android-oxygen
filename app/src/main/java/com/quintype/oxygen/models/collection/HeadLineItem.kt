package com.quintype.oxygen.models.collection

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeadLineItem(
    @SerializedName("headline")
    @Expose
    var headline: List<String>? = null
) : Parcelable