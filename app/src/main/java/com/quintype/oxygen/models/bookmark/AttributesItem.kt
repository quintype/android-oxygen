package com.quintype.oxygen.models.bookmark

import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.database.entities.BulkBookmarkMetadata

data class AttributesItem(
    @SerializedName("page_id")
    val pageId: String = "",
    @SerializedName("metadata")
    val metadata: BulkBookmarkMetadata? = null,
    @SerializedName("read_at")
    val readAt: String = ""
)