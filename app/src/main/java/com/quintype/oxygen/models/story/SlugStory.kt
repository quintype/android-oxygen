package com.quintype.oxygen.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class SlugStory protected constructor(parcel: Parcel) : Parcelable, Serializable {
    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("story")
    val story: Story = parcel.readParcelable(Story::class.java.classLoader)

    override fun toString(): String {
        return "SlugStory{" +
                "story=" + story +
                '}'.toString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(this.story, 0)
    }

    companion object CREATOR : Parcelable.Creator<SlugStory> {
        override fun createFromParcel(source: Parcel): SlugStory {
            return SlugStory(source)
        }

        override fun newArray(size: Int): Array<SlugStory?> {
            return arrayOfNulls(size)
        }
    }
}
