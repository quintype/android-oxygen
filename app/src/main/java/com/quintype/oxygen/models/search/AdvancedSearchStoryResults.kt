package com.quintype.oxygen.models.search

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.latest.home.ChildItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AdvancedSearchStoryResults(
    @SerializedName("total-count")
    var total: Int? = null,
    @SerializedName("items")
    var stories: List<ChildItem>? = null
) : Parcelable