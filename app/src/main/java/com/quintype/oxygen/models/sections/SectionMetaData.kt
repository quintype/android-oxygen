package com.quintype.oxygen.models.sections

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SectionMetaData(
    @SerializedName("color")
    val mColor: String?
) : Parcelable