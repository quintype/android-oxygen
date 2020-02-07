package com.quintype.oxygen.models.polltype

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PollTypeMe(
    @SerializedName("has-account")
    var hasAccount: Boolean? = null,
    @SerializedName("has-qlitics")
    var hasQlitics: Boolean? = null
) : Parcelable