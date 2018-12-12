package com.quintype.androidoxygen.models

import android.os.Parcel
import android.os.Parcelable

class MenuItemModel() : Parcelable {
    var slug: String? = null
        get() = field
        set(value) {
            field = value
        }
    var title: String? = null
        get() = field
        set(value) {
            field = value
        }
    var color: Int = 0
        get() = field
        set(value) {
            field = value
        }
    var isSelected: Boolean = false
        get() = field
        set(value) {
            field = value
        }


    override fun describeContents(): Int {
        return 0
    }

    constructor(parcel: Parcel) : this() {
        this.slug = parcel.readString()
        this.title = parcel.readString()
        this.color = parcel.readInt()
        this.isSelected = parcel.readByte().toInt() != 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(slug)
        parcel.writeString(title)
        parcel.writeInt(color)
        parcel.writeByte(if (isSelected) 1.toByte() else 0.toByte())
    }


    companion object CREATOR : Parcelable.Creator<MenuItemModel> {
        override fun createFromParcel(parcel: Parcel): MenuItemModel {
            return MenuItemModel(parcel)
        }

        override fun newArray(size: Int): Array<MenuItemModel?> {
            return arrayOfNulls(size)
        }
    }
}
