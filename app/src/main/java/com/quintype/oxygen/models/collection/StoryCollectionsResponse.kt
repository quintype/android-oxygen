package com.quintype.oxygen.models.collection

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class StoryCollectionsResponse(
    @SerializedName("story-collections")
    private var storyCollections: List<StoryCollection> = ArrayList()
) : Parcelable {

    fun storyCollections(): List<StoryCollection> {
        return storyCollections
    }

    fun storyCollections(storyCollections: List<StoryCollection>) {
        this.storyCollections = storyCollections
    }
}