package com.quintype.oxygen.models.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.collection.Metadata

class EntityItem() : Parcelable {
    @Expose
    @SerializedName("id")
    var id: Int = 0

    @Expose
    @SerializedName("name")
    var name: String? = null

    @Expose
    @SerializedName("metadata")
    var metadata: Metadata? = null

    @Expose
    @SerializedName("slug")
    var slug: String? = null

    @Expose
    @SerializedName("template")
    var template: String? = null

    @Expose
    @SerializedName("data-source")
    var dataSource: String? = null

    @SerializedName("collection-date")
    @Expose
    var collectionDate: String?=null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString().toInt()
        name = parcel.readString()
        metadata = parcel.readParcelable(Metadata::class.java.classLoader)
        slug = parcel.readString()
        template = parcel.readString()
        dataSource = parcel.readString()
        collectionDate = parcel.readString()
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        dest.writeInt(id)
        dest.writeString(name)
        dest.writeParcelable(metadata, flags)
        dest.writeString(slug)
        dest.writeString(template)
        dest.writeString(dataSource)
        dest.writeString(collectionDate)
    }

    companion object CREATOR : Parcelable.Creator<EntityItem> {
        override fun createFromParcel(source: Parcel): EntityItem = EntityItem(source)
        override fun newArray(size: Int): Array<EntityItem?> = arrayOfNulls(size)
    }
}