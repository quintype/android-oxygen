package com.quintype.oxygen.models.collection

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.entities.EntityItem
import kotlinx.android.parcel.Parcelize

/**
 * Created TemplateCollectionWithRx by rakshith on 8/31/18.
 */

@Parcelize
data class CollectionResponse(
    @SerializedName("id")
    @Expose
    var id: Int = 0,
    @SerializedName("slug")
    @Expose
    var slug: String,
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("summary")
    @Expose
    var summary: String?,
    @SerializedName("created-at")
    @Expose
    var createdAt: Long,
    @SerializedName("collection-date")
    @Expose
    var collectionDate: Long? = null,
    @SerializedName("updated-at")
    @Expose
    var updatedAt: Long,
    @Expose
    @SerializedName("collections")
    var collections: ArrayList<EntityItem>? = null,
    @Expose
    @SerializedName("items")
    var items: List<CollectionItem>? = null,
    @SerializedName("total-count")
    @Expose
    var totalCount: Int = 0,
    @SerializedName("metadata")
    @Expose
    var collectionMetadata: Metadata,
    @SerializedName("template")
    @Expose
    var template: String?
) : Parcelable