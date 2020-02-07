package com.quintype.oxygen.models.polltype

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PollResults(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("opinions")
    var opinions: List<Opinion>? = null,
    @SerializedName("settings")
    var settings: PollSettings? = null,
    @SerializedName("last-voted-at")
    var lastVotedAt: Long? = null,
    @SerializedName("total-votes")
    var totalVotes: Int? = null,
    @SerializedName("voted-on")
    var votedOn: VotedOn? = null,
    @SerializedName("metadata")
    var metadata: PollMetadata? = null
) : Parcelable