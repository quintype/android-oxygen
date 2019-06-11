package com.quintype.oxygen.models.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class MagazineEntityModel(parcel: Parcel) : Parcelable {
    @SerializedName("updated-at")
    var updatedAt: Long = 0

    @SerializedName("publisher-id")
    var publisherId: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("type")
    var type: String? = null

    @SerializedName("entity-type-id")
    var entityTypeId: String? = null

    @SerializedName("deleted-at")
    var deletedAt: Long = 0

    @SerializedName("created-by")
    var createdBy: String? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("last-updated-by")
    var lastUpdatedBy: String? = null

    @SerializedName("created-at")
    var createdAt: String? = null

    init {
        updatedAt = parcel.readLong()
        publisherId = parcel.readString()
        name = parcel.readString()
        type = parcel.readString()
        entityTypeId = parcel.readString()
        deletedAt = parcel.readLong()
        createdBy = parcel.readString()
        id = parcel.readString()
        lastUpdatedBy = parcel.readString()
        createdAt = parcel.readString()
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        dest.writeLong(updatedAt)
        dest.writeString(publisherId)
        dest.writeString(name)
        dest.writeString(type)
        dest.writeString(entityTypeId)
        dest.writeLong(deletedAt)
        dest.writeString(createdBy)
        dest.writeString(id)
        dest.writeString(lastUpdatedBy)
        dest.writeString(createdAt)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MagazineEntityModel> = object : Parcelable.Creator<MagazineEntityModel> {
            override fun createFromParcel(source: Parcel): MagazineEntityModel =
                MagazineEntityModel(source)

            override fun newArray(size: Int): Array<MagazineEntityModel?> = arrayOfNulls(size)
        }
    }
}
