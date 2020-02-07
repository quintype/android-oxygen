package com.quintype.oxygen.services.latest

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.polltype.PollMetadata
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PollIntention(
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
    var createdAt: Long? = null,
    @SerializedName("updated-at")
    var updatedAt: Long? = null
) : Parcelable