package com.quintype.oxygen.models.latest.home.magazines

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.collection.CoverImage
import com.quintype.oxygen.models.collection.PdfSourceKey

data class IssuesItem(
    @SerializedName("collectionSlug")
    val collectionSlug: String = "",
    @SerializedName("createdAt")
    val createdAt: Long = 0,
    @SerializedName("coverImage")
    val coverImage: CoverImage,
    @SerializedName("collectionId")
    val collectionId: Int = 0,
    @SerializedName("collectionDate")
    val collectionDate: Long = 0,
    @SerializedName("pdf-src-key")
    @Expose
    var pdfSrcKey: PdfSourceKey
)