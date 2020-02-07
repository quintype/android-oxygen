package com.quintype.oxygen.models.story

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import org.json.JSONObject


@Parcelize
data class WaterMark(
    @SerializedName("social")
    var social: Social? = null
) : Parcelable


@Parcelize
data class Social(
    @SerializedName("image-s3-key")
    var imageS3Key: String? = null,
    @SerializedName("overlay-data")
    var overlayData: @RawValue JSONObject? = JSONObject()
) : Parcelable