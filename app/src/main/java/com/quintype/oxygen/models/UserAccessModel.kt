package com.quintype.oxygen.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Parcelize
data class UserAccessModel(
    @SerializedName("granted")
    @Expose
    var granted: Boolean? = false,
    @SerializedName("grantReason")
    @Expose
    var grantReason: String? = null,
    @SerializedName("remainingStoriesCount")
    @Expose
    var remainingStoriesCount: Int? = 0
) : Parcelable