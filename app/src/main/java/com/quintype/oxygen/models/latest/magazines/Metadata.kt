package com.quintype.oxygen.models.latest.home.magazines

import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("mime-type")
    val mimeType: String = "",
    @SerializedName("width")
    val width: Int = 0,
    @SerializedName("file-size")
    val fileSize: Int = 0,
    @SerializedName("file-name")
    val fileName: String = "",
    @SerializedName("height")
    val height: Int = 0
)