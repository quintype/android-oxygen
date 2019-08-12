package com.quintype.oxygen.models.latest.home

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.collection.Metadata
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CollectionResponse(
    @SerializedName("updated-at")
    @Expose
    var updatedAt: Long? = null,
    @SerializedName("slug")
    @Expose
    var slug: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("summary")
    @Expose
    var summary: String? = null,
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("collection-date")
    @Expose
    var collectionDate: Long? = null,
    @SerializedName("items")
    @Expose
    var items: List<ChildItem>? = null,
    @SerializedName("total-count")
    @Expose
    var totalCount: Int? = null,
    @SerializedName("metadata")
    @Expose
    var metadata: Metadata? = null
) : Parcelable