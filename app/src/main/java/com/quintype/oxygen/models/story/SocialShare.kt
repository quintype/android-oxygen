package com.quintype.oxygen.models.story

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

@Parcelize
data class SocialShare(
    @SerializedName("shareable")
    var shareable: Boolean = false,
    @SerializedName("title")
    var socialShareTitle: String? = null,
    @SerializedName("message")
    var socialShareMessage: String? = null
) : Parcelable