package com.quintype.oxygen.models.polltype

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Intention(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("organization-id")
    var organizationId: Int? = null,
    @SerializedName("poll-id")
    var pollId: Int? = null,
    @SerializedName("opinion-id")
    var opinionId: Int? = null,
    @SerializedName("voter-id")
    var voterId: String? = null,
    @SerializedName("ip-address")
    var ipAddress: String? = null,
    @SerializedName("metadata")
    var metadata: PollMetadata? = null,
    @SerializedName("created-at")
    var createdAt: Int? = null,
    @SerializedName("updated-at")
    var updatedAt: Int? = null
) : Parcelable