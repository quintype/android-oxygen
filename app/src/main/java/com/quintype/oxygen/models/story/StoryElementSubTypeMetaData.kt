package com.quintype.oxygen.models.story

import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.StringDef
import android.text.TextUtils
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.OxygenConstants.Companion.TYPE_GALLERY
import com.quintype.oxygen.OxygenConstants.Companion.TYPE_SLIDESHOW
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class StoryElementSubTypeMetaData(parcel: Parcel) : Parcelable {

    @SerializedName("tweet-url")
    val tweetUrl: String? = parcel.readString()
    @SerializedName("tweet-id")
    val tweetId: String? = parcel.readString()
    @SerializedName("attribution")
    val attribution: String? = parcel.readString()
    @SerializedName("content")
    val content: String? = parcel.readString()
    @SerializedName("type")
    val type: String? = parcel.readString()
    @SerializedName("question")
    val question: String? = parcel.readString()
    @SerializedName("answer")
    val answer: String? = parcel.readString()
    @SerializedName("video-id")
    var videoId: String? = parcel.readString()
    @SerializedName("file-name")
    private var fileName: String? = null
    @SerializedName("has-header")
    var hasHeader: Boolean = false
    @SerializedName("linked-story")
    private var linkedStory: LinkedStory?
    @SerializedName("linked-story-id")
    var linkedStoryId: String?
    @SerializedName("account-id")
    private val mBrightCoveAccountID: String?
    @SerializedName("poster-url")
    private val mBrightCovePosterURL: String?
    @SerializedName("name")
    val name: String?
    @SerializedName("url")
    val url: String?
    @SerializedName("interviewer")
    var interviewer: Interviewer?
    @SerializedName("interviewee")
    var interviewee: Interviewer?

    @Retention(RetentionPolicy.SOURCE)
    @StringDef(OxygenConstants.TYPE_SLIDESHOW, OxygenConstants.TYPE_GALLERY, OxygenConstants.TYPE_INVALID)
    annotation class Type

    override fun toString(): String {
        return "StoryElementSubTypeMetaData{" +
                "tweetUrl='" + tweetUrl + '\''.toString() +
                ", tweetId='" + tweetId + '\''.toString() +
                ", attribution='" + attribution + '\''.toString() +
                ", content='" + content + '\''.toString() +
                ", type='" + type + '\''.toString() +
                ", question='" + question + '\''.toString() +
                ", answer='" + answer + '\''.toString() +
                ", videoId='" + videoId + '\''.toString() +
                ", hasHeader='" + hasHeader + '\''.toString() +
                ", linkedStory='" + linkedStory + '\''.toString() +
                ", linkedStoryId='" + linkedStoryId + '\''.toString() +
                ", mBrightCoveAccountID='" + mBrightCoveAccountID + '\''.toString() +
                ", mBrightCovePosterURL='" + mBrightCovePosterURL + '\''.toString() +
                ", name='" + name + '\''.toString() +
                ", url='" + url + '\''.toString() +
                '}'.toString()
    }

    /**
     * Type of SubElement
     *
     * @return String [Type]
     */
    fun type(): String {
        return if (TextUtils.isEmpty(this.type)) {
            "invalid"
        } else if (this.isTypeSlideShow()) {
            "slideshow"
        } else {
            if (this.isTypeGallery()) "gallery" else "invalid"
        }
    }

    /**
     * Check if SubElement is of type gallery
     *
     * @return true if the element is gallery else false
     */
    fun isTypeGallery(): Boolean {
        return type.equals(TYPE_GALLERY, true)
    }

    /**
     * Check if SubElement is of type slide show
     *
     * @return true is the element is slideshow else false
     */
    fun isTypeSlideShow(): Boolean {
        return type.equals(TYPE_SLIDESHOW, true)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.tweetUrl)
        dest.writeString(this.tweetId)
        dest.writeString(this.attribution)
        dest.writeString(this.content)
        dest.writeString(this.type)
        dest.writeString(this.question)
        dest.writeString(this.answer)
        dest.writeString(this.videoId)
        dest.writeByte(if (hasHeader) 1.toByte() else 0.toByte())
        dest.writeParcelable(this.linkedStory, 0)
        dest.writeString(this.linkedStoryId)
        dest.writeString(this.mBrightCoveAccountID)
        dest.writeString(this.mBrightCovePosterURL)
        dest.writeString(this.name)
        dest.writeString(this.url)
        dest.writeParcelable(this.interviewer, 0)
        dest.writeParcelable(this.interviewee, 0)
    }

    init {
        this.hasHeader = parcel.readByte().toInt() != 0
        this.linkedStory = parcel.readParcelable(LinkedStory::class.java.classLoader)
        this.linkedStoryId = parcel.readString()
        this.mBrightCoveAccountID = parcel.readString()
        this.mBrightCovePosterURL = parcel.readString()
        this.name = parcel.readString()
        this.url = parcel.readString()
        this.interviewer = parcel.readParcelable(Interviewer::class.java.classLoader)
        this.interviewee = parcel.readParcelable(Interviewer::class.java.classLoader)
    }

    companion object CREATOR : Parcelable.Creator<StoryElementSubTypeMetaData> {
        override fun createFromParcel(parcel: Parcel): StoryElementSubTypeMetaData {
            return StoryElementSubTypeMetaData(parcel)
        }

        override fun newArray(size: Int): Array<StoryElementSubTypeMetaData?> {
            return arrayOfNulls(size)
        }
    }
}