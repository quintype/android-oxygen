package com.quintype.oxygen.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class Default protected constructor(`in`: Parcel) : Parcelable {

    /**
     * @return
     * The heroImage
     */
    @SerializedName("hero-image")
    @Expose
    var heroImage: HeroImage? = null
    /**
     * @return
     * The headline
     */
    @SerializedName("headline")
    @Expose
    var headline: String? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(this.heroImage, flags)
        dest.writeString(this.headline)
    }

    init {
        this.heroImage = `in`.readParcelable(HeroImage::class.java.classLoader)
        this.headline = `in`.readString()
    }

    companion object CREATOR : Parcelable.Creator<Default> {
        override fun createFromParcel(parcel: Parcel): Default {
            return Default(parcel)
        }

        override fun newArray(size: Int): Array<Default?> {
            return arrayOfNulls(size)
        }
    }

}