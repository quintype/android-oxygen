package com.quintype.oxygen.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class HeroImage protected constructor(parcel: Parcel) : Parcelable, Serializable {

    /**
     * @return
     * The heroImageUrl
     */
    @SerializedName("hero-image-url")
    @Expose
    var heroImageUrl: String? = null
    /**
     * @return
     * The heroImageMetadata
     */
    @SerializedName("hero-image-metadata")
    @Expose
    var heroImageMetadata: ImageMetaData? = null
    /**
     * @return
     * The heroImageCaption
     */
    @SerializedName("hero-image-caption")
    @Expose
    var heroImageCaption: String? = null
    /**
     * @return
     * The heroImageS3Key
     */
    @SerializedName("hero-image-s3-key")
    @Expose
    var heroImageS3Key: String? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.heroImageUrl)
        dest.writeParcelable(this.heroImageMetadata, flags)
        dest.writeString(this.heroImageCaption)
        dest.writeString(this.heroImageS3Key)
    }

    init {
        this.heroImageUrl = parcel.readString()
        this.heroImageMetadata = parcel.readParcelable(ImageMetaData::class.java.classLoader)
        this.heroImageCaption = parcel.readString()
        this.heroImageS3Key = parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<HeroImage> {
        override fun createFromParcel(parcel: Parcel): HeroImage {
            return HeroImage(parcel)
        }

        override fun newArray(size: Int): Array<HeroImage?> {
            return arrayOfNulls(size)
        }
    }
}