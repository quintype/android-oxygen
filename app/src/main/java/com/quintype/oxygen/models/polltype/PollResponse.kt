package com.quintype.oxygen.models.polltype

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.services.latest.PollIntention
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PollResponse(
    @SerializedName("me")
    var me: PollTypeMe? = null,
    @SerializedName("statistics")
    var statistics: PollStatistics? = null,
    @SerializedName("poll")
    var poll: Poll? = null,
    @SerializedName("results")
    var pollResults: PollResults? = null,
    @SerializedName("intention")
    var pollIntention: PollIntention? = null
) : Parcelable