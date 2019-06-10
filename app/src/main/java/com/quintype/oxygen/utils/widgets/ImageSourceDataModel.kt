package com.quintype.oxygen.utils.widgets

import android.os.Parcel
import android.os.Parcelable
import com.quintype.oxygen.models.story.ImageMetaData

class ImageSourceDataModel() : Parcelable {
    var imageS3Key: String? = null
    var imageMetaData: ImageMetaData? = null
    var isGifImage: Boolean = false
    var avatarUrl: String? = null

    constructor(parcel: Parcel) : this() {
        this.imageS3Key = parcel.readString()
        this.imageMetaData = parcel.readParcelable(ImageMetaData::class.java.classLoader)
        this.isGifImage = parcel.readByte() != 0.toByte()
        this.avatarUrl = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(this.imageS3Key)
        parcel.writeParcelable(this.imageMetaData, flags)
        parcel.writeByte(if (isGifImage) 1 else 0)
        parcel.writeString(this.avatarUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageSourceDataModel> {
        override fun createFromParcel(parcel: Parcel): ImageSourceDataModel {
            return ImageSourceDataModel(parcel)
        }

        override fun newArray(size: Int): Array<ImageSourceDataModel?> {
            return arrayOfNulls(size)
        }
    }
}