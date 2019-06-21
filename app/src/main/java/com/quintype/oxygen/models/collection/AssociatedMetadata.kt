package com.quintype.oxygen.models.collection

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created TemplateCollectionWithRx by rakshith on 8/31/18.
 */
@Parcelize
data class AssociatedMetadata(
    @SerializedName("layout")
    @Expose
    var associatedMetadataLayout: String? = null,
    @SerializedName("show_arrows")
    @Expose
    var associatedMetadataShowArrow: Boolean = false,
    @SerializedName("show_author_name")
    @Expose
    var associatedMetadataShowAuthorName: Boolean = true,
    @SerializedName("slider_type_dots")
    @Expose
    var associatedMetadataSliderTypeDots: Boolean = false,
    @SerializedName("show_section_tag")
    @Expose
    var associatedMetadataShowSectionTag: Boolean = false,
    @SerializedName("show_time_of_publish")
    @Expose
    var associatedMetadataShowTimeToPublish: Boolean = false,
    @SerializedName("show_collection_name")
    @Expose
    var associatedMetadataShowCollectionName: Boolean = true,
    @SerializedName("scroll_speed_ms")
    @Expose
    var associatedMetadataScrollSpeedMs: Int = 0,
    @SerializedName("number_of_stories_to_show")
    @Expose
    var associatedMetadataNumberOfStoriesToShow: Int = 0,
    @SerializedName("theme")
    @Expose
    var associatedMetadataTheme: String? = null,
    @SerializedName("slider_type_dashes")
    @Expose
    var associatedMetadataSliderTypeDashes: Boolean = false,
    @SerializedName("enable_auto_play")
    @Expose
    var associatedMetadataEnableAutoPlay: Boolean = false,
    @SerializedName("show_comment_count")
    @Expose
    var associatedMetadataShowCommentCount: Boolean = false,
    @SerializedName("show_share_count")
    @Expose
    var associatedMetadataShowShareCount: Boolean = false,
    @SerializedName("get_inner_collection")
    @Expose
    var associatedMetadataGetInnerCollection: Boolean = false,
    @SerializedName("number_of_slider_stories_to_show")
    @Expose
    var associatedMetadataNumberOfSliderStoriesToShow: Int = 0,
    @SerializedName("number_of_child_stories_to_show")
    @Expose
    var associatedMetadataNumberOfChildStoriesToShow: Int = 0,
    @SerializedName("number_of_stories_inside_collection_to_show")
    @Expose
    var associatedMetadataNumberOfStoriesInsideCollectionToShow: Int = 0,
    @SerializedName("number_of_collections_to_show")
    @Expose
    var associatedMetadataNumberOfCollectionsToShow: Int = 0,
    @SerializedName("saving")
    @Expose
    var associatedMetadataMagazineSaving: Int = 0,
    @SerializedName("amount")
    @Expose
    var associatedMetadataMagazineAmount: Int = 0,
    @SerializedName("headline")
    @Expose
    var associatedMetadataMagazineHeadline: String? = null,
    @SerializedName("saving-label")
    @Expose
    var associatedMetadataMagazineSavingLabel: String? = null,
    @SerializedName("amount-label")
    @Expose
    var associatedMetadataMazanieAmountLabel: String? = null,
    @SerializedName("subscription-text")
    @Expose
    var associatedMetadataMagazineSubscriptionText: String? = null,
    @SerializedName("label")
    @Expose
    var associatedMetadataMagazineLabels: List<String>? = null,
    @SerializedName("pagenumber")
    @Expose
    var associatedMetadataMagazineStoryPageNumber: List<Int>? = null
) : Parcelable
//
//constructor(parcel: Parcel) : this() {
//    associatedMetadataLayout = parcel.readString()
//    associatedMetadataShowArrow = parcel.readByte() != 0.toByte()
//    associatedMetadataShowAuthorName = parcel.readByte() != 1.toByte()
//    associatedMetadataSliderTypeDots = parcel.readByte() != 0.toByte()
//    associatedMetadataShowSectionTag = parcel.readByte() != 0.toByte()
//    associatedMetadataShowTimeToPublish = parcel.readByte() != 0.toByte()
//    associatedMetadataShowCollectionName = parcel.readByte() != 1.toByte()
//    associatedMetadataScrollSpeedMs = parcel.readInt()
//    associatedMetadataNumberOfStoriesToShow = parcel.readInt()
//    associatedMetadataTheme = parcel.readString()
//    associatedMetadataSliderTypeDashes = parcel.readByte() != 0.toByte()
//    associatedMetadataEnableAutoPlay = parcel.readByte() != 0.toByte()
//
//    associatedMetadataShowCommentCount = parcel.readByte() != 0.toByte()
//    associatedMetadataShowShareCount = parcel.readByte() != 0.toByte()
//    associatedMetadataGetInnerCollection = parcel.readByte() != 0.toByte()
//    associatedMetadataNumberOfSliderStoriesToShow = parcel.readInt()
//    associatedMetadataNumberOfChildStoriesToShow = parcel.readInt()
//    associatedMetadataNumberOfCollectionsToShow = parcel.readInt()
//    associatedMetadataNumberOfStoriesInsideCollectionToShow = parcel.readInt()
//    associatedMetadataMagazineSaving = parcel.readInt()
//    associatedMetadataMagazineAmount = parcel.readInt()
//    associatedMetadataMagazineHeadline = parcel.readString()
//    associatedMetadataMagazineSavingLabel = parcel.readString()
//    associatedMetadataMazanieAmountLabel = parcel.readString()
//    associatedMetadataMagazineSubscriptionText = parcel.readString()
//    associatedMetadataMagazineLabels = parcel.createStringArrayList()
//    associatedMetadataMagazineStoryPageNumber = parcel.createTypedArrayList(Int)
//}
//
//override fun writeToParcel(parcel: Parcel, flags: Int) {
//    parcel.writeString(associatedMetadataLayout)
//    parcel.writeByte(if (associatedMetadataShowArrow) 1 else 0)
//    parcel.writeByte(if (associatedMetadataShowAuthorName) 0 else 1)
//    parcel.writeByte(if (associatedMetadataSliderTypeDots) 1 else 0)
//    parcel.writeByte(if (associatedMetadataShowSectionTag) 1 else 0)
//    parcel.writeByte(if (associatedMetadataShowTimeToPublish) 1 else 0)
//    parcel.writeByte(if (associatedMetadataShowCollectionName) 0 else 1)
//    parcel.writeInt(associatedMetadataScrollSpeedMs)
//    parcel.writeInt(associatedMetadataNumberOfStoriesToShow)
//    parcel.writeString(associatedMetadataTheme)
//    parcel.writeByte(if (associatedMetadataSliderTypeDashes) 1 else 0)
//    parcel.writeByte(if (associatedMetadataEnableAutoPlay) 1 else 0)
//
//    parcel.writeByte(if (associatedMetadataShowCommentCount) 1 else 0)
//    parcel.writeByte(if (associatedMetadataShowShareCount) 1 else 0)
//    parcel.writeByte(if (associatedMetadataGetInnerCollection) 1 else 0)
//    parcel.writeInt(associatedMetadataNumberOfSliderStoriesToShow)
//    parcel.writeInt(associatedMetadataNumberOfChildStoriesToShow)
//    parcel.writeInt(associatedMetadataNumberOfStoriesInsideCollectionToShow)
//    parcel.writeInt(associatedMetadataNumberOfCollectionsToShow)
//    parcel.writeInt(associatedMetadataMagazineSaving)
//    parcel.writeInt(associatedMetadataMagazineAmount)
//    parcel.writeString(associatedMetadataMagazineHeadline)
//    parcel.writeString(associatedMetadataMagazineSavingLabel)
//    parcel.writeString(associatedMetadataMazanieAmountLabel)
//    parcel.writeString(associatedMetadataMagazineSubscriptionText)
//    parcel.writeStringList(associatedMetadataMagazineLabels)
//    parcel.writeTypedList(associatedMetadataMagazineStoryPageNumber)
//}
//
//override fun describeContents(): Int {
//    return 0
//}
//
//override fun toString(): String {
//    return "AssociatedMetadata{" +
//            "associatedMetadataLayout=" + associatedMetadataLayout +
//            ", associatedMetadataShowArrow=" + associatedMetadataShowArrow +
//            ", associatedMetadataShowAuthorName=" + associatedMetadataShowAuthorName +
//            ", associatedMetadataSliderTypeDots=" + associatedMetadataSliderTypeDots +
//            ", associatedMetadataShowSectionTag=" + associatedMetadataShowSectionTag +
//            ", associatedMetadataShowTimeToPublish=" + associatedMetadataShowTimeToPublish +
//            ", associatedMetadataShowCollectionName=" + associatedMetadataShowCollectionName +
//            ", associatedMetadataScrollSpeedMs=" + associatedMetadataScrollSpeedMs +
//            ", associatedMetadataNumberOfStoriesToShow=" + associatedMetadataNumberOfStoriesToShow +
//            ", associatedMetadataTheme=" + associatedMetadataTheme +
//            ", associatedMetadataSliderTypeDashes=" + associatedMetadataSliderTypeDashes +
//            ", associatedMetadataEnableAutoPlay=" + associatedMetadataEnableAutoPlay +
//            ", associatedMetadataShowCommentCount=" + associatedMetadataShowCommentCount +
//            ", associatedMetadataShowShareCount=" + associatedMetadataShowShareCount +
//            ", associatedMetadataGetInnerCollection=" + associatedMetadataGetInnerCollection +
//            ", associatedMetadataNumberOfSliderStoriesToShow=" + associatedMetadataNumberOfSliderStoriesToShow +
//            ", associatedMetadataNumberOfChildStoriesToShow=" + associatedMetadataNumberOfChildStoriesToShow +
//            ", associatedMetadataNumberOfStoriesInsideCollectionToShow=" + associatedMetadataNumberOfStoriesInsideCollectionToShow +
//            ", associatedMetadataNumberOfCollectionsToShow=" + associatedMetadataNumberOfCollectionsToShow +
//            ", associatedMetadataMagazineSaving=" + associatedMetadataMagazineSaving +
//            ", associatedMetadataMagazineAmount=" + associatedMetadataMagazineAmount +
//            ", associatedMetadataMagazineHeadline=" + associatedMetadataMagazineHeadline +
//            ", associatedMetadataMagazineSavingLabel=" + associatedMetadataMagazineSavingLabel +
//            ", associatedMetadataMazanieAmountLabel=" + associatedMetadataMazanieAmountLabel +
//            ", associatedMetadataMagazineSubscriptionText=" + associatedMetadataMagazineSubscriptionText +
//            ", associatedMetadataMagazineStoryPageNumber=" + associatedMetadataMagazineStoryPageNumber +
//            '}'.toString()
//}
//
//companion object CREATOR : Parcelable.Creator<AssociatedMetadata> {
//    override fun createFromParcel(parcel: Parcel): AssociatedMetadata {
//        return AssociatedMetadata(parcel)
//    }
//
//    override fun newArray(size: Int): Array<AssociatedMetadata?> {
//        return arrayOfNulls(size)
//    }
//}
//}