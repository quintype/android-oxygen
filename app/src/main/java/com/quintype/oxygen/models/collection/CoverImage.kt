package com.quintype.oxygen.models.collection

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.story.ImageMetaData

/**
 * Created TemplateCollectionWithRx by rakshith on 8/31/18.
 */

class CoverImage protected constructor(parcel: Parcel) : Parcelable {

    @SerializedName("cover-image-url")
    @Expose
    val coverImageUrl: String = parcel.readString()
    @SerializedName("cover-image-metadata")
    @Expose
    val coverImageMetadata: ImageMetaData = parcel.readParcelable(ImageMetaData::class.java.classLoader)
    @SerializedName("caption")
    @Expose
    val caption: String = parcel.readString()
    @SerializedName("cover-image-s3-key")
    @Expose
    val coverImageS3Key: String = parcel.readString()

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.coverImageUrl)
        dest.writeParcelable(this.coverImageMetadata, flags)
        dest.writeString(this.caption)
        dest.writeString(this.coverImageS3Key)
    }

    companion object CREATOR : Parcelable.Creator<CoverImage> {
        override fun createFromParcel(parcel: Parcel): CoverImage {
            return CoverImage(parcel)
        }

        override fun newArray(size: Int): Array<CoverImage?> {
            return arrayOfNulls(size)
        }
    }
}
