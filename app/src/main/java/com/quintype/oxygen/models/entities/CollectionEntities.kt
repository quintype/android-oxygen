package com.quintype.oxygen.models.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CollectionEntities(source: Parcel) : Parcelable, Serializable {
    @SerializedName("magazine")
    var magazine: List<MagazineEntityModel>? = null

    init {
        this.magazine = source.createTypedArrayList(MagazineEntityModel.CREATOR)
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeTypedList(this.magazine)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CollectionEntities> = object : Parcelable.Creator<CollectionEntities> {
            override fun createFromParcel(source: Parcel): CollectionEntities =
                CollectionEntities(source)
            override fun newArray(size: Int): Array<CollectionEntities?> = arrayOfNulls(size)
        }
    }
}