package com.quintype.oxygen.models.polltype

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PollStatistics(
    @SerializedName("metadata")
    var metadata: PollMetadata? = null,
    @SerializedName("created-at")
    var createdAt: String? = null,
    @SerializedName("updated-at")
    var updatedAt: String? = null,
    @SerializedName("views")
    var views: Int? = null,
    @SerializedName("votes")
    var votes: Int? = null
) : Parcelable