package com.quintype.oxygen.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */
class Alternative protected constructor(`in`: Parcel) : Parcelable {

    /**
     * @return The home
     */
    @SerializedName("home")
    @Expose
    var home: Home? = null


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(this.home, flags)
    }

    init {
        this.home = `in`.readParcelable(Home::class.java.classLoader)
    }

    companion object CREATOR : Parcelable.Creator<Alternative> {
        override fun createFromParcel(parcel: Parcel): Alternative {
            return Alternative(parcel)
        }

        override fun newArray(size: Int): Array<Alternative?> {
            return arrayOfNulls(size)
        }
    }

}