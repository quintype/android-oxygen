package com.quintype.oxygen.models

import android.text.TextUtils
import com.quintype.oxygen.OxygenConstants.Companion.COLLECTION_SLUG
import com.quintype.oxygen.TYPE_INVALID_TYPE
import com.quintype.oxygen.WIDTH_PERCENTAGE_100
import com.quintype.oxygen.models.collection.AssociatedMetadata
import com.quintype.oxygen.models.collection.BreakingNewsModel
import com.quintype.oxygen.models.collection.CollectionItem
import com.quintype.oxygen.models.collection.Metadata
import com.quintype.oxygen.models.story.Story

class CollectionInnerListModel(mCollectionInnerListBuilder: CollectionInnerListBuilder) {
    var story: Story? = mCollectionInnerListBuilder.story
    var outerViewHolderType = mCollectionInnerListBuilder.outerViewHolderType
    var innerViewHolderType = mCollectionInnerListBuilder.innerViewHolderType
    var associatedMetadata: AssociatedMetadata? = mCollectionInnerListBuilder.associatedMetadata
    var storyList: List<Story>? = mCollectionInnerListBuilder.storyList
    var collectionItemList: List<CollectionItem>? = mCollectionInnerListBuilder.collectionItemList
    var outerCollectionName: String? = mCollectionInnerListBuilder.outerCollectionName
    var viewWidthPercentage: Double = mCollectionInnerListBuilder.viewWidthPercentage
    var collectionItem: CollectionItem? = mCollectionInnerListBuilder.collectionItem
    private var mRecyclerViewColor: Int? = mCollectionInnerListBuilder.mRecyclerViewColor
    private var mBreakingNewsModel: BreakingNewsModel? = mCollectionInnerListBuilder.mBreakingNewsModel
    val mOuterCollectionSlug: String? = mCollectionInnerListBuilder.mOuterCollectionSlug
    val trendingStoryNumber: Int? = mCollectionInnerListBuilder.mTrendingStoryNumber
    val mTotalItemCount: Int? = mCollectionInnerListBuilder.mTotalItemCount
    val mMetaData: Metadata? = mCollectionInnerListBuilder.mMetaData
    val isTrendingCollection: Boolean? = mCollectionInnerListBuilder.isTrendingCollection

    override fun toString(): String {
        return "CollectionInnerListModel{" +
                "story=" + story +
                ", outerViewHolderType=" + outerViewHolderType +
                ", innerViewHolderType=" + innerViewHolderType +
                ", associatedMetadata=" + associatedMetadata +
                ", storyList=" + storyList +
                ", collectionItemList=" + collectionItemList +
                ", outerCollectionName=" + outerCollectionName +
                ", viewWidthPercentage=" + viewWidthPercentage +
                ", mTrendingStorynumber=" + trendingStoryNumber +
                ", collectionItem=" + collectionItem +
                ", mRecyclerViewColor=" + mRecyclerViewColor +
                ", mBreakingNewsItem=" + mBreakingNewsModel +
                ", mOuterCollectionSlug=" + mOuterCollectionSlug +
                ", mTotalItemCount=" + mTotalItemCount +
                ", mMetaData=" + mMetaData +
                ", isTrendingCollection=" + isTrendingCollection +
                '}'.toString()
    }

    class CollectionInnerListBuilder {
        var story: Story? = null
        var outerViewHolderType: Int = TYPE_INVALID_TYPE
        var innerViewHolderType: Int = TYPE_INVALID_TYPE
        var associatedMetadata: AssociatedMetadata? = null
        var storyList: List<Story>? = ArrayList()
        var collectionItemList: List<CollectionItem>? = ArrayList()
        var outerCollectionName: String? = null
        var viewWidthPercentage: Double = WIDTH_PERCENTAGE_100
        var collectionItem: CollectionItem? = null
        var mRecyclerViewColor: Int? = TYPE_INVALID_TYPE
        var mBreakingNewsModel: BreakingNewsModel? = BreakingNewsModel()
        var mOuterCollectionSlug: String? = COLLECTION_SLUG
        var mTrendingStoryNumber: Int? = 0
        var mTotalItemCount: Int = 0
        var mMetaData: Metadata? = null
        var isTrendingCollection: Boolean? = false

        /**
         * set story for the builder
         */
        fun setStory(mStory: Story?): CollectionInnerListBuilder {
            this.story = mStory
            return this
        }

        fun setOuterViewHolderType(outerViewHolderType: Int): CollectionInnerListBuilder {
            this.outerViewHolderType = outerViewHolderType
            return this
        }

        fun setMetaData(metadata: Metadata): CollectionInnerListBuilder {
            this.mMetaData = metadata
            return this
        }

        fun setInnerViewHolderType(innerViewHolderType: Int): CollectionInnerListBuilder {
            this.innerViewHolderType = innerViewHolderType
            return this
        }

        fun setTotalItemCount(totalItemCount: Int): CollectionInnerListBuilder {
            this.mTotalItemCount = totalItemCount
            return this
        }

        fun setTrendingStoryNumber(mTrendingStoryNumber: Int): CollectionInnerListBuilder {
            this.mTrendingStoryNumber = mTrendingStoryNumber
            return this
        }

        fun setAssociatedMetadata(associatedMetadata: AssociatedMetadata?): CollectionInnerListBuilder {
            this.associatedMetadata = associatedMetadata
            return this
        }

        fun setStoryList(mStoryList: List<Story>): CollectionInnerListBuilder {
            this.storyList = mStoryList
            return this
        }

        fun setCollectionItemList(mCollectionItemList: List<CollectionItem>): CollectionInnerListBuilder {
            this.collectionItemList = mCollectionItemList
            return this
        }

        fun setOuterCollectionName(outerCollectionName: String?): CollectionInnerListBuilder {
            if (!TextUtils.isEmpty(outerCollectionName))
                this.outerCollectionName = outerCollectionName
            return this
        }

        fun setViewWidthPercentage(viewWidthPercentage: Double): CollectionInnerListBuilder {
            this.viewWidthPercentage = viewWidthPercentage
            return this
        }

        fun setCollectionItem(mCollectionItem: CollectionItem): CollectionInnerListBuilder {
            this.collectionItem = mCollectionItem
            return this
        }

        fun setRecyclerViewColor(mRecyclerViewColor: Int): CollectionInnerListBuilder {
            this.mRecyclerViewColor = mRecyclerViewColor
            return this
        }

        fun setBreakingNewsItem(mBreakingNewsModel: BreakingNewsModel): CollectionInnerListBuilder {
            this.mBreakingNewsModel = mBreakingNewsModel
            return this
        }

        fun setOuterCollectionSlug(slug: String): CollectionInnerListBuilder {
            this.mOuterCollectionSlug = slug
            return this
        }

        fun isTrendingCollection(isTrending: Boolean): CollectionInnerListBuilder {
            this.isTrendingCollection = isTrending
            return this
        }

        fun buildCollectionInnerListModel(): CollectionInnerListModel {
            return CollectionInnerListModel(this)
        }
    }
}