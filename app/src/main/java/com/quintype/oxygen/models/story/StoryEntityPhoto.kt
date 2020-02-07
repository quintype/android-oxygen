package com.quintype.oxygen.models.story

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import org.json.JSONObject

@Parcelize
data class StoryEntityPhoto(
    @SerializedName("member-id")
    var memberId: Long? = null,
    @SerializedName("caption")
    var caption: String? = null,
    @SerializedName("key")
    var key: String? = null,
    @SerializedName("uploaded-at")
    var uploadedAt: Long? = null,
    @SerializedName("attribution")
    var attribution: String? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("creator-id")
    var creatorId: Long? = null,
    @SerializedName("extracted-data")
    var extractedData: @RawValue JSONObject? = null,
    @SerializedName("metadata")
    var metadata: EntityPhotoMetadata? = null
) : Parcelable

@Parcelize
data class EntityPhotoMetadata(
    @SerializedName("width")
    var width: Int? = null,
    @SerializedName("height")
    var height: Int? = null
) : Parcelable