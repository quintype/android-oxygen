package com.quintype.oxygen.models.collection

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.story.Story
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class StoryCollection(
    @SerializedName("updated-at")
    private val updatedAt: Long,
    @SerializedName("publisher-id")
    private val publisherId: String,
    @SerializedName("name")
    private val name: String,
    @SerializedName("published-at")
    private val publishedAt: Long,
    @SerializedName("collection-date")
    private val collectionDate: Long,
    @SerializedName("deleted-at")
    private val deletedAt: Long,
    @SerializedName("summary")
    private val summary: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("story-content-ids")
    private var storyContentIds: ArrayList<String>? = null,
    @SerializedName("created-at")
    private val createdAt: Long,
    @SerializedName("metadata")
    private val metadata: Metadata,
    @SerializedName("stories")
    private var stories: ArrayList<Story>? = null
) : Parcelable

