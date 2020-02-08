package com.quintype.oxygen.models.polltype

import android.os.Parcelable
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
class VoteRequest(
    @SerializedName("vote")
    @Expose
    var vote: Vote? = null
) : Parcelable

@Parcelize
data class Vote(
    @SerializedName("opinion-id")
    var opinionId: String? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("attributes")
    var attribute: PollAttribute? = null
) : Parcelable

@Parcelize
data class PollAttribute(
    @SerializedName("profile")
    var profile: @RawValue JsonObject? = null
) : Parcelable

@Parcelize
data class Profile(
    @SerializedName("verification-status")
    var verificationStatus: String? = null,
    @SerializedName("updated-at")
    var updatedAt: Int? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("slug")
    var slug: String? = null,
    @SerializedName("last-name")
    var lastName: String? = null,
    @SerializedName("publisher-id")
    var publisherId: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("source")
    var source: String? = null,
    @SerializedName("first-name")
    var firstName: String? = null,
    @SerializedName("communication-email")
    var communicationEmail: String? = null,
    @SerializedName("bio")
    var bio: String? = null,
    @SerializedName("can-login")
    var canLogin: Boolean? = null,
    @SerializedName("id")
    var id: Long? = null,
    @SerializedName("avatar-s3-key")
    var avatarS3Key: String? = null,
    @SerializedName("twitter-handle")
    var twitterHandle: String? = null,
    @SerializedName("created-at")
    var createdAt: Int? = null,
    @SerializedName("metadata")
    var metadata: PollMetadata? = null
) : Parcelable