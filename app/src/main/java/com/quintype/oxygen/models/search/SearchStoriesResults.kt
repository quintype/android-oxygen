package com.quintype.oxygen.models.search

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.story.Story
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchStoriesResults(
    @SerializedName("from")
    var from: Int? = null,
    @SerializedName("size")
    var size: Int? = null,
    @SerializedName("total")
    var total: Int? = null,
    @SerializedName("fallback")
    var fallback: Boolean? = null,
    @SerializedName("stories")
    var stories: List<Story>? = null,
    @SerializedName("term")
    var term: String? = null
) : Parcelable