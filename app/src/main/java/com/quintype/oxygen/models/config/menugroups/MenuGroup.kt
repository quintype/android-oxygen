package com.quintype.oxygen.models.config.menugroups

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import com.quintype.oxygen.MOBILE_MENU

class MenuGroup {

    @Expose
    @SerializedName(MOBILE_MENU)
    var mMenuItem: MenuItem? = null
}