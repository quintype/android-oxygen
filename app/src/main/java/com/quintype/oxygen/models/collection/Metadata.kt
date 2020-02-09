package com.quintype.oxygen.models.collection

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.entities.EntitiesMetaData
import com.quintype.oxygen.models.sections.Section
import com.quintype.oxygen.models.story.CardShare
import com.quintype.oxygen.models.story.Tag
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Created TemplateCollectionWithRx by rakshith on 8/31/18.
 */
@Parcelize
data class Metadata(
    @SerializedName("cover-image")
    @Expose
    val coverImage: CoverImage,
    @SerializedName("type")
    @Expose
    var type: List<MetaDataType>? = null,
    @SerializedName("tags")
    @Expose
    var tags: ArrayList<Tag> = ArrayList(),
    @SerializedName("snapshot")
    @Expose
    val snapshot: CollectionSnapshot,
    /*For TheQuint publisher, if the collectionTemplate is 'big-story' then the key will be 'sections'*/
    @SerializedName("sections")
    @Expose
    val sections: List<Section>,
    /*For TheQuint publisher, if the collectionTemplate is not 'big-story' then the key will be 'section'*/
    @SerializedName("section")
    @Expose
    val section: List<Section>,
    @SerializedName("entities")
    @Expose
    var entities: EntitiesMetaData? = null,
    @SerializedName("pdf-upload-timestamp")
    @Expose
    var pdfUploadTimeStamp: String? = null,
    @SerializedName("issue-date")
    @Expose
    var issueDate: String? = null,
    @SerializedName("issue-id")
    @Expose
    var issueId: String? = null,
    @SerializedName("pdf-src-key")
    @Expose
    var pdfSrcKey: PdfSourceKey,
    @SerializedName("excerpt")
    var excerpt: String? = null,
    @SerializedName("card-share")
    var cardShare: CardShare? = null
) : Parcelable