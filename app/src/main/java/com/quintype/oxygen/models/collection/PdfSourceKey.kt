package com.quintype.oxygen.models.collection

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class PdfSourceKey(
    @SerializedName("s3-key")
    @Expose
    var s3Key: String? = null,
    @SerializedName("pdf-file-url")
    @Expose
    var pdfFileUrl: String? = null
) : Parcelable, Serializable