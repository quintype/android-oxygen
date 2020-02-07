package com.quintype.oxygen.models.story

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

@Parcelize
data class StoryResponse(
    @SerializedName("story")
    val story: Story
) : Parcelable