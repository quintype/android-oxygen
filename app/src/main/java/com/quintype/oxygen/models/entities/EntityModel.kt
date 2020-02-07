package com.quintype.oxygen.models.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EntityModel(
    @SerializedName("updated-at")
    var updatedAt: Long = 0,
    @SerializedName("publisher-id")
    var publisherId: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("entity-type-id")
    var entityTypeId: String? = null,
    @SerializedName("deleted-at")
    var deletedAt: Long = 0,
    @SerializedName("created-by")
    var createdBy: String? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("last-updated-by")
    var lastUpdatedBy: String? = null,
    @SerializedName("created-at")
    var createdAt: String? = null
) : Parcelable
