package com.quintype.oxygen.models

import android.text.TextUtils
import com.quintype.oxygen.OxygenConstants.Companion.COLLECTION_SLUG
import com.quintype.oxygen.TYPE_INVALID_TYPE
import com.quintype.oxygen.WIDTH_PERCENTAGE_100
import com.quintype.oxygen.models.collection.AssociatedMetadata
import com.quintype.oxygen.models.collection.BreakingNewsModel
import com.quintype.oxygen.models.collection.Metadata
import com.quintype.oxygen.models.latest.home.ChildItem
import com.quintype.prothomalo.main.viewholders.ViewHolderType

class CollectionAdapterItem private constructor(val mCollectionInnerListBuilder: Builder) {
    var childItem: ChildItem? = mCollectionInnerListBuilder.childItem
    var outerViewHolderType: ViewHolderType =
        mCollectionInnerListBuilder.outerViewHolderType//fallback
    var innerViewHolderType: ViewHolderType =
        mCollectionInnerListBuilder.innerViewHolderType//fallback
    var associatedMetadata: AssociatedMetadata? = mCollectionInnerListBuilder.associatedMetadata
    var childItemList: List<ChildItem>? = mCollectionInnerListBuilder.childItemList
    var outerCollectionName: String? = mCollectionInnerListBuilder.outerCollectionName
    var viewWidthPercentage: Double = mCollectionInnerListBuilder.viewWidthPercentage
    private var mRecyclerViewColor: Int? = mCollectionInnerListBuilder.mRecyclerViewColor
    private var mBreakingNewsModel: BreakingNewsModel? =
        mCollectionInnerListBuilder.mBreakingNewsModel
    val mOuterCollectionSlug: String? = mCollectionInnerListBuilder.mOuterCollectionSlug
    val trendingStoryNumber: Int? = mCollectionInnerListBuilder.mTrendingStoryNumber
    val mTotalItemCount: Int? = mCollectionInnerListBuilder.mTotalItemCount
    val mMetaData: Metadata? = mCollectionInnerListBuilder.mMetaData
    val isTrendingCollection: Boolean? = mCollectionInnerListBuilder.isTrendingCollection
    val showLoadMoreTopPadding: Boolean? = mCollectionInnerListBuilder.mShowLoadMoreTopPadding

    override fun toString(): String {
        return mCollectionInnerListBuilder.toString()
    }

    data class Builder(
        var childItem: ChildItem? = null,
        var outerViewHolderType: ViewHolderType = ViewHolderType.TITLE_INSIDE_IMAGE_GRADIENT_ITEM,//fallback
        var innerViewHolderType: ViewHolderType = ViewHolderType.HORIZONTAL_STORY_LIST_ITEM,//fallback
        var associatedMetadata: AssociatedMetadata? = null,
        var childItemList: List<ChildItem>? = ArrayList(),
        var outerCollectionName: String? = null,
        var viewWidthPercentage: Double = WIDTH_PERCENTAGE_100,
        var mRecyclerViewColor: Int? = TYPE_INVALID_TYPE,
        var mBreakingNewsModel: BreakingNewsModel? = BreakingNewsModel(),
        var mOuterCollectionSlug: String? = COLLECTION_SLUG,
        var mTrendingStoryNumber: Int? = 0,
        var mTotalItemCount: Int = 0,
        var mMetaData: Metadata? = null,
        var isTrendingCollection: Boolean? = false,
        var mShowLoadMoreTopPadding: Boolean? = true
    ) {
        /**
         * set childItemList for the builder
         */
        fun setChildItem(mChildItem: ChildItem?) = apply {
            this.childItem = mChildItem
        }

        fun setOuterViewHolderType(outerViewHolderType: ViewHolderType) = apply {
            this.outerViewHolderType = outerViewHolderType
        }

        fun setMetaData(metadata: Metadata) = apply {
            this.mMetaData = metadata
        }

        fun setInnerViewHolderType(innerViewHolderType: ViewHolderType) = apply {
            this.innerViewHolderType = innerViewHolderType
        }

        fun setTotalItemCount(totalItemCount: Int) = apply {
            this.mTotalItemCount = totalItemCount
        }

        fun setTrendingStoryNumber(mTrendingStoryNumber: Int) = apply {
            this.mTrendingStoryNumber = mTrendingStoryNumber
        }

        fun setAssociatedMetadata(associatedMetadata: AssociatedMetadata?) = apply {
            this.associatedMetadata = associatedMetadata
        }

        fun setChildItemList(mStoryList: List<ChildItem>) = apply {
            this.childItemList = mStoryList
        }

        fun setOuterCollectionName(outerCollectionName: String?) = apply {
            if (!TextUtils.isEmpty(outerCollectionName))
                this.outerCollectionName = outerCollectionName
        }

        fun setViewWidthPercentage(viewWidthPercentage: Double) = apply {
            this.viewWidthPercentage = viewWidthPercentage
        }

        fun setRecyclerViewColor(mRecyclerViewColor: Int) = apply {
            this.mRecyclerViewColor = mRecyclerViewColor
        }

        fun setBreakingNewsItem(mBreakingNewsModel: BreakingNewsModel) = apply {
            this.mBreakingNewsModel = mBreakingNewsModel
        }

        fun setOuterCollectionSlug(slug: String) = apply {
            this.mOuterCollectionSlug = slug
        }

        fun isTrendingCollection(isTrending: Boolean) = apply {
            this.isTrendingCollection = isTrending
        }

        fun setLoadMoreTopPadding(showPadding: Boolean) = apply {
            this.mShowLoadMoreTopPadding = showPadding
        }

        fun build(): CollectionAdapterItem {
            return CollectionAdapterItem(this)
        }
    }
}