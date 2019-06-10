package com.quintype.oxygen.models.collection

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.story.ImageMetaData

class MagazineImages(parcel: Parcel) : Parcelable {

    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("image-s3-key")
    @Expose
    var imageS3Key: String? = null
    @SerializedName("image-metadata")
    @Expose
    var imageMetadata: ImageMetaData? = null

    init {
        type = parcel.readString()
        imageS3Key = parcel.readString()
        imageMetadata = parcel.readParcelable(ImageMetaData::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeString(imageS3Key)
        parcel.writeParcelable(imageMetadata, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MagazineImages> {
        override fun createFromParcel(parcel: Parcel): MagazineImages {
            return MagazineImages(parcel)
        }

        override fun newArray(size: Int): Array<MagazineImages?> {
            return arrayOfNulls(size)
        }
    }
}