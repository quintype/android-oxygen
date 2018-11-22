package com.quintype.androidoxygen.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created TemplateCollectionWithRx by rakshith on 10/8/18.
 */

class MenuItemModel() : Parcelable {

    /* var sectionSlug: String? = mSectionSlug
     var mTitle: String? = title*/

    var sectionSlug: String? = null
        get() = field
        set(value) {
            field = value
        }
    var mTitle: String? = null
        get() = field
        set(value) {
            field = value
        }


    override fun describeContents(): Int {
        return 0
    }

    constructor(parcel: Parcel) : this() {
        this.sectionSlug = parcel.readString()
        this.mTitle = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(sectionSlug)
        parcel.writeString(mTitle)
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

/*
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
}*/
