package com.quintype.oxygen.models.search

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.story.Story


class SearchStoriesResults() : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("from")
    @Expose
    var from: Int? = null
    @SerializedName("size")
    @Expose
    var size: Int? = null
    @SerializedName("total")
    @Expose
    var total: Int? = null
    @SerializedName("fallback")
    @Expose
    var fallback: Boolean? = null
    @SerializedName("stories")
    @Expose
    var stories: List<Story>? = null
    @SerializedName("term")
    @Expose
    var term: String? = null

    constructor(parcel: Parcel) : this() {
        from = parcel.readValue(Int::class.java.classLoader) as? Int
        size = parcel.readValue(Int::class.java.classLoader) as? Int
        total = parcel.readValue(Int::class.java.classLoader) as? Int
        fallback = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        stories = parcel.createTypedArrayList(Story.CREATOR)
        term = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(from)
        parcel.writeValue(size)
        parcel.writeValue(total)
        parcel.writeValue(fallback)
        parcel.writeTypedList(stories)
        parcel.writeString(term)
    }

    companion object CREATOR : Parcelable.Creator<SearchStoriesResults> {
        override fun createFromParcel(parcel: Parcel): SearchStoriesResults {
            return SearchStoriesResults(parcel)
        }

        override fun newArray(size: Int): Array<SearchStoriesResults?> {
            return arrayOfNulls(size)
        }
    }
}