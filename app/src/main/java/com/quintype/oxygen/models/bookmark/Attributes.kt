package com.quintype.oxygen.models.bookmark

import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("metadata")
    var metadata: Metadata? = null,
    var updatedAt: String? = null,
    var heroImageUrl: String? = null,
    var createdAt: String? = null,
    @SerializedName("read_at")
    var readAt: String? = null,
    var headline: String? = null,
    var url: String? = null
)