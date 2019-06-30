package com.quintype.oxygen.models.story

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Interviewer(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
) : Parcelable, Serializable