package com.quintype.oxygen.models.polltype

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IntentionRequest(
    @SerializedName("intention")
    var intention: IntentionOpinionRequest? = null
) : Parcelable

@Parcelize
data class IntentionOpinionRequest(
    @SerializedName("opinion-id")
    var opinionId: String? = null
) : Parcelable