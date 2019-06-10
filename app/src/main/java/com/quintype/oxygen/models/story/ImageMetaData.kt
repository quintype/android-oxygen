package com.quintype.oxygen.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * An object instance representing Story ImageMetaData
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class ImageMetaData protected constructor(parcel: Parcel) : Parcelable {

    @SerializedName("width")
    var width: Int = parcel.readInt()
    @SerializedName("height")
    var height: Int = parcel.readInt()
    @SerializedName("focus-point")
    var focusPoints: IntArray? = null
    @SerializedName("mime-type")
    private var mimeType: String? = null

    override fun toString(): String {
        return "ImageMetaData{" +
                "width=" + width +
                ", height=" + height +
                ", mimeType=" + mimeType +
                ", focusPoints=" + Arrays.toString(focusPoints) +
                '}'.toString()
    }



    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.width)
        dest.writeInt(this.height)
        dest.writeIntArray(this.focusPoints)
        dest.writeString(this.mimeType)
    }

    init {
        this.focusPoints = parcel.createIntArray()
        this.mimeType = parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<ImageMetaData> {
        override fun createFromParcel(source: Parcel): ImageMetaData {
            return ImageMetaData(source)
        }

        override fun newArray(size: Int): Array<ImageMetaData?> {
            return arrayOfNulls(size)
        }
    }
}
