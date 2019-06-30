package com.quintype.oxygen.models.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class EntitiesMetaData(source: Parcel) : Parcelable, Serializable {
    @SerializedName("collectionEntities")
    var collectionEntities: CollectionEntities? = null

    init {
        this.collectionEntities = source.readParcelable(CollectionEntities::class.java.classLoader)
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(this.collectionEntities, flags)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<EntitiesMetaData> = object : Parcelable.Creator<EntitiesMetaData> {
            override fun createFromParcel(source: Parcel): EntitiesMetaData =
                EntitiesMetaData(source)

            override fun newArray(size: Int): Array<EntitiesMetaData?> = arrayOfNulls(size)
        }
    }
}
