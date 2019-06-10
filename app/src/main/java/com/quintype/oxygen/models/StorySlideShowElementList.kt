package com.quintype.oxygen.models

import android.os.Parcel
import android.os.Parcelable
import com.quintype.oxygen.models.story.StoryElement
import java.util.*

class StorySlideShowElementList(parcel: Parcel) : Parcelable {
//    var mItems: List<StoryElement> = ArrayList()
//    var mSelectedItem = 0

    override fun toString(): String {
        return "PhotoList{" +
                "mItems=" + mItems +
                ", mSelectedItem=" + mSelectedItem +
                '}'.toString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: android.os.Parcel, flags: Int) {
        dest.writeTypedList(mItems)
        dest.writeInt(mSelectedItem)
    }

    companion object CREATOR : Parcelable.Creator<StorySlideShowElementList> {
        var mItems: List<StoryElement> = ArrayList()
        var mSelectedItem = 0

        override fun createFromParcel(parcel: Parcel): StorySlideShowElementList {
            return StorySlideShowElementList(parcel)
        }

        override fun newArray(size: Int): Array<StorySlideShowElementList?> {
            return arrayOfNulls(size)
        }
    }

    init {
        mItems = parcel.createTypedArrayList(StoryElement.CREATOR)
        mSelectedItem = parcel.readInt()
    }
}
