package com.quintype.oxygen.models.author

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContributorModel(
    @SerializedName("authors")
    val authors: List<Author>,
    @SerializedName("role-id")
    val roleId: Int,
    @SerializedName("role-name")
    val roleName: String
) : Parcelable