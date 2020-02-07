package com.quintype.oxygen.models.story

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CardShare(
    @SerializedName("shareable")
    var shareable: Boolean? = null
) : Parcelable