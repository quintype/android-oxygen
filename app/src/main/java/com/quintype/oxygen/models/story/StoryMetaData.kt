package com.quintype.oxygen.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class StoryMetaData protected constructor(parcel: Parcel) : Parcelable, Serializable {

    /**
     * Check if Story is closed
     *
     * @return boolean true is closed else false
     */
    @SerializedName("is-closed")
    var isClosed: Boolean = false
    @SerializedName("review-title")
    var reviewTitle: String? = null
    @SerializedName("review-rating")
    var reviewRating: ExtraData? = null
    @SerializedName("story-attributes")
    var storyAttributes: JsonObject? = null
    @SerializedName("reference-url")
    var referenceUrl: String? = null
    @SerializedName("sponsored-by")
    var sponsoredBy: String? = null
    @SerializedName("linked-story")
    var linkedStory: LinkedStory? = null
    @SerializedName("linked-story-slug")
    var linkedStorySlug: String? = null

    override fun toString(): String {
        return "StoryMetaData{" +
                "isClosed=" + isClosed +
                "reviewTitle=" + reviewTitle +
                "reviewRating=" + reviewRating +
                "storyAttributes=" + storyAttributes +
                "sponsoredBy=" + sponsoredBy +
                '}'.toString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeByte(if (this.isClosed) 1.toByte() else 0.toByte())
        dest.writeString(this.reviewTitle)
        dest.writeParcelable(this.reviewRating, flags)
        dest.writeByte((if (storyAttributes == null) 0 else 1).toByte())
        if (storyAttributes != null)
            dest.writeString(this.storyAttributes!!.toString())
        dest.writeString(this.referenceUrl)
        dest.writeString(this.sponsoredBy)
        dest.writeParcelable(this.linkedStory, 0)
        dest.writeString(this.linkedStorySlug)
    }

    init {
        this.isClosed = parcel.readByte().toInt() != 0
        this.reviewTitle = parcel.readString()
        this.reviewRating = parcel.readParcelable(ExtraData::class.java.classLoader)
        if (parcel.readByte().toInt() != 0) {
            this.storyAttributes = JsonParser().parse(parcel.readString()) as JsonObject
        }
        this.referenceUrl = parcel.readString()
        this.sponsoredBy = parcel.readString()
        this.linkedStory = parcel.readParcelable(LinkedStory::class.java.classLoader)
        this.linkedStorySlug = parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<StoryMetaData> {
        override fun createFromParcel(parcel: Parcel): StoryMetaData {
            return StoryMetaData(parcel)
        }

        override fun newArray(size: Int): Array<StoryMetaData?> {
            return arrayOfNulls(size)
        }
    }
}