package com.quintype.oxygen.models.story

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CreatedBy(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("external-id")
    var externalId: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("username")
    var username: String? = null
) : Parcelable