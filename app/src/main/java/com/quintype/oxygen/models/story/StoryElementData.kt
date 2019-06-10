package com.quintype.oxygen.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */
class StoryElementData(`in`: Parcel) : Parcelable {

    @SerializedName("content")
    var content: String = `in`.readString()
    @SerializedName("content-type")
    var contentType: String = `in`.readString()

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.content)
        dest.writeString(this.contentType)
    }

    companion object CREATOR : Parcelable.Creator<StoryElementData> {
        override fun createFromParcel(source: Parcel): StoryElementData {
            return StoryElementData(source)
        }

        override fun newArray(size: Int): Array<StoryElementData?> {
            return arrayOfNulls(size)
        }
    }
}
