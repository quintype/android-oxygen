package com.quintype.oxygen.models.story

import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StoryEntity(
    @SerializedName("updated-at")
    var updatedAt: Long? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("slug")
    var slug: String? = null,
    @SerializedName("publisher-id")
    var publisherId: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("entity-type-id")
    var entityTypeId: Long? = null,
    @SerializedName("deleted-at")
    var deletedAt: Long? = null,
    @SerializedName("created-by")
    var createdBy: Long? = null,
    @SerializedName("photo")
    var photo: List<StoryEntityPhoto>? = null,
    @SerializedName("id")
    var id: Long? = null,
    @SerializedName("last-updated-by")
    var lastUpdatedBy: Long? = null,
    @SerializedName("created-at")
    var createdAt: Long? = null,
    @SerializedName("bio")
    var storyEntityBio: String? = null
) : Parcelable