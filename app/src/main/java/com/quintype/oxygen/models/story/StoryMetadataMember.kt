package com.quintype.oxygen.models.story

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import org.json.JSONObject

@Parcelize
data class StoryMetadataMember(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("avatar-url")
    var avatarUrl: String? = null,
    @SerializedName("settings")
    var settings: @RawValue JSONObject? = null,
    @SerializedName("communication-email")
    var communicationEmail: String? = null,
    @SerializedName("bio")
    var bio: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("avatar-s3-key")
    var avatarS3Key: String? = null,
    @SerializedName("twitter-handle")
    var twitterHandle: String? = null,
    @SerializedName("metadata")
    var metadata: StoryMemberMetadata? = null
) : Parcelable

@Parcelize
data class StoryMemberMetadata(
    @SerializedName("Location")
    var location: String? = null
) : Parcelable