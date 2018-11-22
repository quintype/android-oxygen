package com.quintype.androidoxygen.models

import android.os.Parcel
import android.os.Parcelable

class MenuItemModel : Parcelable {
    constructor(mSectionSlug: String?, title: String?) {
        this.sectionSlug = mSectionSlug
        this.mTitle = title
    }

    var sectionSlug: String?
    var mTitle: String?

    constructor(parcel: Parcel) {
        this.sectionSlug = parcel.readString()
        this.mTitle = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(sectionSlug)
        parcel.writeString(mTitle)
    }

    override fun describeContents(): Int {
        return 0
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