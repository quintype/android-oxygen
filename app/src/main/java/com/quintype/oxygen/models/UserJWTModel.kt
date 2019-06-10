package com.quintype.oxygen.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserJWTModel(
    @SerializedName("user")
    @Expose
    var user: UserDetailModel? = null
) : Parcelable

@Parcelize
data class UserDetailModel(
    @SerializedName("userId")
    @Expose
    var userId: String? = null,
    @SerializedName("username")
    @Expose
    var username: String? = null,
    @SerializedName("userType")
    @Expose
    var userType: String? = null,
    @SerializedName("userEmail")
    @Expose
    var userEmail: String? = null,
    @SerializedName("userJoinDate")
    @Expose
    var userJoinDate: String? = null,
    @SerializedName("metypeJWT")
    @Expose
    var metypeJWT: String? = null
) : Parcelable