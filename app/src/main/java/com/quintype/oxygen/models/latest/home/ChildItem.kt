package com.quintype.oxygen.models.latest.home

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.author.Author
import com.quintype.oxygen.models.collection.AssociatedMetadata
import com.quintype.oxygen.models.collection.Metadata
import com.quintype.oxygen.models.story.Alternative
import com.quintype.oxygen.models.story.ImageMetaData
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChildItem(
    @SerializedName("type")
    @Expose
    var type: String? = null,
    @SerializedName("id")
    @Expose
    var id: String? = null,
    @SerializedName("hero-image-s3-key")
    @Expose
    var heroImageS3Key: String? = null,
    @SerializedName("headline")
    @Expose
    var headline: String? = null,
    @SerializedName("authors")
    @Expose
    var authors: List<Author>? = null,
    @SerializedName("alternative")
    @Expose
    var alternative: Alternative? = null,
    @SerializedName("hero-image-metadata")
    @Expose
    var heroImageMetadata: ImageMetaData? = null,
    @SerializedName("slug")
    @Expose
    var slug: String? = null,
    @SerializedName("subheadline")
    @Expose
    var subheadline: String? = null,
    @SerializedName("author-name")
    @Expose
    var authorName: String? = null,
    @SerializedName("url")
    @Expose
    var url: String? = null,
    @SerializedName("template")
    @Expose
    var template: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("metadata")
    @Expose
    var metaData: Metadata? = null,
    @SerializedName("summary")
    @Expose
    var summary: String? = null,
    @SerializedName("items")
    @Expose
    var grandChildItem: ArrayList<ChildItem>? = null,
    @SerializedName("collection-date")
    @Expose
    var collectionDate: String? = null,
    @SerializedName("associated-metadata")
    @Expose
    var associatedMetadata: AssociatedMetadata? = null
) : Parcelable