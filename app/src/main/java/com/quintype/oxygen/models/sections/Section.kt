package com.quintype.oxygen.models.sections

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 8/31/18.
 */

open class Section : Parcelable {

    @SerializedName("name")
    var name: String?=null
    @SerializedName("id")
    var id: String?=null
    @SerializedName("display-name")
    var displayName: String?=null

    override fun toString(): String {
        return "Section{" +
                "name='" + name + '\''.toString() +
                ", id='" + id + '\''.toString() +
                ", displayName='" + displayName + '\''.toString() +
                '}'.toString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.name)
        dest.writeString(this.id)
        dest.writeString(this.displayName)
    }

    protected constructor(parcel: Parcel) {
        this.name = parcel.readString()
        this.id = parcel.readString()
        this.displayName = parcel.readString()
    }

    constructor(mSectionName: String) {
        this.name = mSectionName
    }

    companion object CREATOR : Parcelable.Creator<Section> {
        override fun createFromParcel(parcel: Parcel): Section {
            return Section(parcel)
        }

        override fun newArray(size: Int): Array<Section?> {
            return arrayOfNulls(size)
        }
    }
}