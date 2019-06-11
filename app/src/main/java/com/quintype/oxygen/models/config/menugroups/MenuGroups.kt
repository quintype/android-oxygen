package com.quintype.oxygen.models.config.menugroups

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class MenuGroups : Parcelable {
    @Expose
    @SerializedName("menu-groups")
    var mMenuGroups: MenuGroup? = null
}