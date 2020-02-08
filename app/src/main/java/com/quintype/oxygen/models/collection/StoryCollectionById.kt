package com.quintype.oxygen.models.collection

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.story.Story
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class StoryCollectionById(
    @SerializedName("updated-at")
    private val updatedAt: Long?,
    @SerializedName("publisher-id")
    private val publisherId: Long?,
    @SerializedName("name")
    private val name: String,
    @SerializedName("type")
    private val type: String,
    @SerializedName("published-at")
    private val publishedAt: Long?,
    @SerializedName("stories")
    private var stories: ArrayList<Story>? = null,
    @SerializedName("deleted-at")
    private val deletedAt: Long?,
    @SerializedName("summary")
    private val summary: String,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("created-at")
    private val createdAt: Long?,
    @SerializedName("metadata")
    val metadata: Metadata
) : Parcelable
