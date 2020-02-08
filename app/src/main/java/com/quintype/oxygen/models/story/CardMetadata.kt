package com.quintype.oxygen.models.story

import android.os.Parcelable
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */
@Parcelize
data class CardMetadata protected constructor(
    @SerializedName("social-share")
    var socialShare: SocialShare? = null,
    @SerializedName("attributes")
    private var attributes: @RawValue JsonObject? = null
) : Parcelable {
    fun attributes(): JsonObject? {
        return this.attributes
    }
}