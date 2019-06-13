package com.quintype.oxygen.models.search

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.story.Story


class AdvancedSearchStorieResults() : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("total")
    @Expose
    var total: Int? = null
    @SerializedName("items")
    @Expose
    var stories: List<Story>? = null

    constructor(parcel: Parcel) : this() {
        total = parcel.readValue(Int::class.java.classLoader) as? Int
        stories = parcel.createTypedArrayList(Story.CREATOR)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(total)
        parcel.writeTypedList(stories)
    }

    companion object CREATOR : Parcelable.Creator<AdvancedSearchStorieResults> {
        override fun createFromParcel(parcel: Parcel): AdvancedSearchStorieResults {
            return AdvancedSearchStorieResults(parcel)
        }

        override fun newArray(size: Int): Array<AdvancedSearchStorieResults?> {
            return arrayOfNulls(size)
        }
    }
}