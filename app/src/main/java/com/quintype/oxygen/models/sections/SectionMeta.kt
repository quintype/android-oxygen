package com.quintype.oxygen.models.sections

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.collection.CollectionMeta
import kotlinx.android.parcel.Parcelize


/**
 * Created TemplateCollectionWithRx by rakshith on 8/31/18.
 */

@Parcelize
data class SectionMeta(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("display-name")
    val displayName: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("parent-id")
    val parentId: String? = null,
    @SerializedName("slug")
    val slug: String? = null,
    @SerializedName("collection")
    var collectionMeta: CollectionMeta? = null,
    @SerializedName("data")
    @Expose
    val data: SectionMetaData? = null
) : Parcelable