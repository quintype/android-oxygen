package com.quintype.oxygen.components

import com.quintype.oxygen.TYPE_COLLECTION
import com.quintype.oxygen.TYPE_STORY
import com.quintype.oxygen.models.CollectionAdapterItem
import com.quintype.oxygen.models.collection.AssociatedMetadata
import com.quintype.oxygen.models.collection.Metadata
import com.quintype.oxygen.models.latest.home.ChildItem
import com.quintype.prothomalo.R
import com.quintype.prothomalo.main.viewholders.ViewHolderType
import com.quintype.prothomalo.utils.*

class ComponentImpl : IComponent {

    /**
     * This component consists of n stories where
     *  0th molecule will be Large image with title below and
     *  a Widget and
     *  1st to 4th molecule will be LEFT_IMAGE_CHILD_ITEM and
     *  an Ad and
     *  remaining will be TITLE_NO_IMAGE_CHILD molecule
     */
    override fun prepareComponentOne(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem) {
        try {
            val isShowCollectionName = parentItem.associatedMetadata?.isShowCollectionName
            if (isShowCollectionName!!)
                collectionAdapterItemList.add(getCollectionNameItem(parentItem)!!)
            var storyItemCount = 0
            storyItemCount = if (isShowCollectionName) 1 else 0

            parentItem.grandChildItem?.forEachIndexed { index, childItem ->
                storyItemCount++
                when (index) {
                    0 -> collectionAdapterItemList.add(getTitleBelowLargeImageStoryItem(parentItem, childItem, WIDTH_PERCENTAGE_100)!!)
                    in 1..4 -> collectionAdapterItemList.add(getLeftImageChildStoryItem(ViewHolderType.LEFT_IMAGE_CHILD_ITEM, parentItem, childItem)!!)
                    else -> collectionAdapterItemList.add(getLeftImageChildStoryItem(ViewHolderType.TITLE_NO_IMAGE_CHILD, parentItem, childItem)!!)
                }
            }
            if (IS_ADS_REQUIRED) {
                var lastPosition = collectionAdapterItemList.size
                if (storyItemCount > COMPONENT_1_WIDGET_POSITION) {
                    prepareWidgetAdItem(collectionAdapterItemList, COMPONENT_1_WIDGET_POSITION, parentItem)
                    lastPosition += 1
                }
                if (storyItemCount > COMPONENT_1_AD_POSITION) {
                    lastPosition += 1
                    prepareNativeAdItem(collectionAdapterItemList, COMPONENT_1_AD_POSITION, parentItem)
                } else {
                    lastPosition += 1
                    prepareNativeAdItem(collectionAdapterItemList, parentItem.grandChildItem?.size!! - 1, parentItem)
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    /**
     * This component consists of n collection n stories where stories from 1st collection must be displayed as follows
     *  0th molecule will be Large image with title below it and
     *  remaining molecules will be of type LEFT_IMAGE_CHILD_ITEM
     *  and show Ad at DEFAULT_AD_POSITION if total_stories is more than DEFAULT_AD_POSITION else at the end of list
     *
     *  remaining molecules will be of type LEFT_IMAGE_CHILD_ITEM
     */
    override fun prepareComponentTwo(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem) {
        try {
            val isShowCollectionName = parentItem.associatedMetadata?.isShowCollectionName!!
            if (isShowCollectionName)
                collectionAdapterItemList.add(getCollectionNameItem(parentItem)!!)

            var isFirstIteration = true
            parentItem.grandChildItem?.forEachIndexed { index, childItem ->
                if (childItem.type.equals(TYPE_COLLECTION) && index < parentItem.associatedMetadata?.numberOfCollectionsToShow!!) {
                    val limit = if (childItem.grandChildItem?.size!! > parentItem.associatedMetadata?.numberOfCollectionsToShow!!)
                        parentItem.associatedMetadata?.numberOfCollectionsToShow!!
                    else childItem.grandChildItem?.size!!

                    val showLoadMoreTopPadding: Boolean
                    /* Don't show top padding to the load more view if the sub collection title is followed by collection name.*/
                    if (isFirstIteration) {
                        showLoadMoreTopPadding = false
                        isFirstIteration = false
                    } else
                        showLoadMoreTopPadding = true

                    when (index) {
                        limit - 1 -> {
                            if (childItem.grandChildItem?.isNotEmpty()!!) {
                                for (grandChildPosition in 0 until limit) {
                                    val grandChildItem: ChildItem = childItem.grandChildItem?.get(grandChildPosition)!!
                                    if (grandChildPosition == 0) {
                                        collectionAdapterItemList.add(getCollectionNameLoadMoreItem(childItem, showLoadMoreTopPadding, parentItem.associatedMetadata)!!)
                                        collectionAdapterItemList.add(getLargeImageStoryItem(parentItem, grandChildItem)!!)
                                    } else {
                                        collectionAdapterItemList.add(getLeftImageChildStoryItem(ViewHolderType.LEFT_IMAGE_CHILD_ITEM, parentItem, grandChildItem)!!)
                                    }
                                }
                                if (IS_ADS_REQUIRED) {
                                    if (parentItem.grandChildItem?.size!! > N_COLLECTION_N_STORIES_DEFAULT_AD_POSITION) {
                                        prepareNativeAdItem(collectionAdapterItemList, N_COLLECTION_N_STORIES_DEFAULT_AD_POSITION, parentItem)
                                    } else {
                                        prepareNativeAdItem(collectionAdapterItemList, parentItem.grandChildItem?.size!! - 1, parentItem)
                                    }
                                }
                            }
                        }
                        else -> {
                            if (childItem.grandChildItem?.isNotEmpty()!!) {
                                collectionAdapterItemList.add(getCollectionNameLoadMoreItem(childItem, showLoadMoreTopPadding, parentItem.associatedMetadata)!!)
                                for (grandChildPosition in 0 until limit) {
                                    val grandChildItem: ChildItem = childItem.grandChildItem?.get(grandChildPosition)!!
                                    collectionAdapterItemList.add(getLeftImageChildStoryItem(ViewHolderType.LEFT_IMAGE_CHILD_ITEM, parentItem, grandChildItem)!!)
                                }
                            }
                        }
                    }
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    /**
     * This component consists of n collection n stories where stories from 1st collection must be displayed as following
     *  0th molecule will be Large image with title below it and
     *  remaining molecules will be of type LEFT_IMAGE_CHILD_ITEM
     *
     *  and stories from remaining collections must be displayed horizontally
     *
     *  Show Ad/Widget after 1st collection
     */
    override fun prepareComponentThree(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem) {
        try {
            if (parentItem.associatedMetadata?.isShowCollectionName!!)
                collectionAdapterItemList.add(getCollectionNameItem(parentItem)!!)

            parentItem.grandChildItem?.forEachIndexed { index, childItem ->
                if (childItem.type.equals(TYPE_COLLECTION) && index < parentItem.associatedMetadata?.numberOfStoriesInsideCollectionToShow!!) {
                    val limit = if (childItem.grandChildItem?.size!! > parentItem.associatedMetadata?.numberOfStoriesInsideCollectionToShow!!) {
                        parentItem.associatedMetadata?.numberOfStoriesInsideCollectionToShow!!
                    } else {
                        childItem.grandChildItem?.size!!
                    }

                    if (index == 0) {
                        if (childItem.grandChildItem?.isNotEmpty()!!) {
                            for (grandChildPosition in 0 until limit) {
                                val grandChildItem: ChildItem = childItem.grandChildItem?.get(grandChildPosition)!!
                                if (grandChildPosition == 0) {
                                    collectionAdapterItemList.add(getLargeImageStoryItem(parentItem, grandChildItem)!!)
                                } else {
                                    collectionAdapterItemList.add(getLeftImageChildStoryItem(ViewHolderType.LEFT_IMAGE_CHILD_ITEM, parentItem, grandChildItem)!!)
                                }
                            }
                            if (IS_ADS_REQUIRED) {
                                if (parentItem.associatedMetadata?.isShowCollectionName!!)
                                    prepareWidgetAdItem(collectionAdapterItemList, limit + 1, parentItem)
                                else
                                    prepareWidgetAdItem(collectionAdapterItemList, limit, parentItem)
                            }
                        }
                    } else {
                        val horizontalStoryList: ArrayList<ChildItem> = ArrayList()
                        if (childItem.grandChildItem?.isNotEmpty()!!) {
                            for (grandChildPosition in 0 until limit) {
                                val grandChildItem: ChildItem = childItem.grandChildItem?.get(grandChildPosition)!!
                                if (grandChildItem != null) {
                                    horizontalStoryList.add(grandChildItem)
                                }
                            }
                            collectionAdapterItemList.add(
                                getHorizontalStoryListItem(horizontalStoryList, ViewHolderType.TITLE_BELOW_LARGE_IMAGE_ITEM_HORIZONTAL, parentItem, WIDTH_PERCENTAGE_75)!!
                            )
                        }
                    }
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    /**
     * This component consists of n stories with TITLE_NO_IMAGE_CHILD molecule
     */
    override fun prepareComponentFour(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem) {
        try {
            val isShowCollectionName = parentItem.associatedMetadata?.isShowCollectionName
            if (isShowCollectionName!!)
                collectionAdapterItemList.add(getCollectionNameItem(parentItem)!!)

            var storyItemCount = 0
            storyItemCount = if (isShowCollectionName) 1 else 0

            parentItem.grandChildItem?.forEach { childItem ->
                storyItemCount++
                collectionAdapterItemList.add(getLeftImageChildStoryItem(ViewHolderType.TITLE_NO_IMAGE_CHILD, parentItem, childItem)!!)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    /**
     * This component consists of n stories with horizontal scroll which includes TITLE_BELOW_LARGE_IMAGE_ITEM_HORIZONTAL molecule
     */
    override fun prepareComponentFive(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem) {
        try {
            val horizontalStoryList: ArrayList<ChildItem> = ArrayList()
            if (parentItem.associatedMetadata?.isShowCollectionName!!)
                collectionAdapterItemList.add(getCollectionNameItem(parentItem)!!)
            parentItem.grandChildItem?.forEachIndexed { index, childItem ->
                if (parentItem.grandChildItem?.get(index) != null) {
                    horizontalStoryList.add(parentItem.grandChildItem?.get(index)!!)
                }
            }
            collectionAdapterItemList.add(
                getHorizontalStoryListItem(horizontalStoryList, ViewHolderType.TITLE_BELOW_LARGE_IMAGE_ITEM_HORIZONTAL, parentItem, WIDTH_PERCENTAGE_75)!!
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    /**
     * This component consists of n stories where
     *  0th molecule will be Large half featured image and
     *  remaining molecules will be of type LEFT_IMAGE_CHILD_ITEM
     *
     *  Adding Native Ad at DEFAULT_AD_POSITION(3) if total number of stories is more then DEFAULT_AD_POSITION
     *  else adding Ad at bottom of the list
     */
    override fun prepareComponentSix(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem) {
        try {
            parentItem.grandChildItem?.forEachIndexed { index, childItem ->
                when (index) {
                    0 -> {
                        if (parentItem.associatedMetadata?.isShowCollectionName!!)
                            collectionAdapterItemList.add(getCollectionNameItem(parentItem)!!)
                        collectionAdapterItemList.add(getHalfTitleBelowLargeImageStoryItem(parentItem, childItem)!!)
                    }
                    else ->
                        collectionAdapterItemList.add(getLeftImageChildStoryItem(ViewHolderType.LEFT_IMAGE_CHILD_ITEM, parentItem, childItem)!!)
                }
            }
            if (IS_ADS_REQUIRED) {
                if (parentItem.grandChildItem?.size!! > DEFAULT_AD_POSITION) {
                    prepareNativeAdItem(collectionAdapterItemList, DEFAULT_AD_POSITION, parentItem)
                } else {
                    prepareNativeAdItem(collectionAdapterItemList, parentItem.grandChildItem?.size!! - 1, parentItem)
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun prepareComponentSeven(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem) {
        try {
            val isShowCollectionName = parentItem.associatedMetadata?.isShowCollectionName
            if (isShowCollectionName!!)
                collectionAdapterItemList.add(getCollectionNameItem(parentItem)!!)

            var storyItemCount = 0
            storyItemCount = if (isShowCollectionName) 1 else 0

            parentItem.grandChildItem?.forEach { childItem ->
                storyItemCount++
                collectionAdapterItemList.add(getLeftImageChildStoryItem(ViewHolderType.TITLE_OPINION_CHILD, parentItem, childItem)!!)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    /**
     * This component consists of n stories with horizontal scroll which includes TITLE_INSIDE_IMAGE_GRADIENT_ITEM molecule
     */
    override fun prepareComponentEight(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem) {
        try {
            val horizontalStoryList: ArrayList<ChildItem> = ArrayList()

            parentItem.grandChildItem?.forEachIndexed { index, childItem ->
                if (index == 0) {
                    if (parentItem.associatedMetadata?.isShowCollectionName!!)
                        collectionAdapterItemList.add(getCollectionNameItem(parentItem)!!)
                }
                if (parentItem.grandChildItem?.get(index) != null) {
                    horizontalStoryList.add(parentItem.grandChildItem?.get(index)!!)
                }
            }
            collectionAdapterItemList.add(getHorizontalStoryListItem(horizontalStoryList, ViewHolderType.TITLE_INSIDE_IMAGE_GRADIENT_ITEM, parentItem, WIDTH_PERCENTAGE_75)!!)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    /**
     * This component consists of n stories where
     *  0th molecule will be Large image with title below it and
     *  remaining molecules will be of type LEFT_IMAGE_CHILD_ITEM
     */
    override fun prepareComponentNine(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem) {
        try {
            val isShowCollectionName = parentItem.associatedMetadata?.isShowCollectionName
            if (isShowCollectionName!!)
                collectionAdapterItemList.add(getCollectionNameItem(parentItem)!!)

            var storyItemCount = 0
            storyItemCount = if (isShowCollectionName) 1 else 0

            parentItem.grandChildItem?.forEachIndexed { index, childItem ->
                storyItemCount++
                when (index) {
                    0 -> collectionAdapterItemList.add(getTitleBelowLargeImageStoryItem(parentItem, childItem, WIDTH_PERCENTAGE_100)!!)
                    else -> collectionAdapterItemList.add(getLeftImageChildStoryItem(ViewHolderType.TITLE_NO_IMAGE_CHILD, parentItem, childItem)!!)
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    /**
     * This component consists of n stories with LEFT_IMAGE_CHILD_ITEM molecules
     */
    override fun prepareComponentTen(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem) {
        try {
            val isShowCollectionName = parentItem.associatedMetadata?.isShowCollectionName
            if (isShowCollectionName!!)
                collectionAdapterItemList.add(getCollectionNameItem(parentItem)!!)

            var storyItemCount = 0
            storyItemCount = if (isShowCollectionName) 1 else 0

            parentItem.grandChildItem?.forEach { childItem ->
                storyItemCount++
                collectionAdapterItemList.add(getLeftImageChildStoryItem(ViewHolderType.LEFT_IMAGE_CHILD_ITEM, parentItem, childItem)!!)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun prepareBreakingNews(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem) {
        //breaking news should be at first position
        collectionAdapterItemList.add(
            0, CollectionAdapterItem.Builder()
                .setOuterViewHolderType(ViewHolderType.BREAKING_NEWS_ITEM)
                .setChildItemList(parentItem.grandChildItem!!).build()
        )
    }

    override fun prepareSingleStory(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem) {
        try {
            collectionAdapterItemList.add(getLeftImageChildStoryItem(ViewHolderType.LEFT_IMAGE_CHILD_ITEM, parentItem, parentItem)!!)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun prepareDefault(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem) {
        try {
            if (parentItem.associatedMetadata?.isShowCollectionName!!)
                collectionAdapterItemList.add(getCollectionNameItem(parentItem)!!)
            val grandChildList = ArrayList<ChildItem>()
            parentItem.grandChildItem?.forEach { grandChildItem ->
                grandChildList.add(grandChildItem)
            }
            collectionAdapterItemList.add(getHorizontalStoryListItem(grandChildList, ViewHolderType.TITLE_INSIDE_IMAGE_GRADIENT_ITEM, parentItem, WIDTH_PERCENTAGE_75)!!)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun prepareSliderStories(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem) {
        try {
            if (parentItem.associatedMetadata?.isShowCollectionName!!)
                collectionAdapterItemList.add(getCollectionNameItem(parentItem)!!)
            val sliderStoryList = ArrayList<ChildItem>()
            parentItem.grandChildItem?.filter { innerCollectionItemModel ->
                innerCollectionItemModel.type?.equals(TYPE_STORY)!!
            }?.forEach { grandChild -> sliderStoryList.add(grandChild) }
            collectionAdapterItemList.add(getStorySliderItem(sliderStoryList, parentItem)!!)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun prepareCollectionAndStories(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem) {
        try {
            if (parentItem.associatedMetadata?.isShowCollectionName!!)
                collectionAdapterItemList.add(getCollectionNameItem(parentItem)!!)
            var isFirstIteration = true
            parentItem.grandChildItem?.forEachIndexed { index, childItem ->
                if (childItem.type.equals(TYPE_COLLECTION) && index < parentItem.associatedMetadata?.numberOfCollectionsToShow!!) {
                    if (childItem.grandChildItem?.isNotEmpty()!! && parentItem.type.equals(TYPE_COLLECTION)) {
                        for (grandChildPosition in 0 until parentItem.associatedMetadata?.numberOfStoriesInsideCollectionToShow!!) {
                            val grandChildItem: ChildItem = childItem.grandChildItem?.get(grandChildPosition)!!
                            if (grandChildPosition == 0) {
                                val showLoadMoreTopPadding: Boolean
                                /* Don't show top padding to the load more view if the sub collection title is followed by collection name.*/
                                if (isFirstIteration) {
                                    showLoadMoreTopPadding = false
                                    isFirstIteration = false
                                } else
                                    showLoadMoreTopPadding = true

                                collectionAdapterItemList.add(getCollectionNameLoadMoreItem(childItem, showLoadMoreTopPadding, parentItem.associatedMetadata)!!)
                                collectionAdapterItemList.add(getLargeImageStoryItem(parentItem, grandChildItem)!!)
                            } else {
                                collectionAdapterItemList.add(getLeftImageChildStoryItem(ViewHolderType.LEFT_IMAGE_CHILD_ITEM, parentItem, grandChildItem)!!)
                            }
                        }
                    }
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    override fun prepareMediaStories(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem) {
        try {
            val horizontalStoryList: ArrayList<ChildItem> = ArrayList()
            if (parentItem.associatedMetadata?.isShowCollectionName!!)
                collectionAdapterItemList.add(getCollectionNameItem(parentItem)!!)
            parentItem.grandChildItem?.forEachIndexed { index, childItem ->
                if (parentItem.grandChildItem?.get(index) != null) {
                    horizontalStoryList.add(parentItem.grandChildItem?.get(index)!!)
                }
            }
            collectionAdapterItemList.add(
                getHorizontalStoryListItem(
                    horizontalStoryList,
                    ViewHolderType.TITLE_BELOW_LARGE_IMAGE_ITEM_HORIZONTAL,
                    parentItem,
                    WIDTH_PERCENTAGE_75
                )!!
            )

        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun prepareHorizontalGradientFeature(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem) {
        try {
            val horizontalStoryList: ArrayList<ChildItem> = ArrayList()

            parentItem.grandChildItem?.forEachIndexed { index, childItem ->
                //                if (index == 0) {
//                    collectionAdapterItemList.add(getCollectionNameItem(parentItem)!!)
//                    collectionAdapterItemList.add(getTitleBelowLargeImageStoryItem(parentItem, childItem, WIDTH_PERCENTAGE_100)!!)
//                } else {
                if (index == 0) {
                    if (parentItem.associatedMetadata?.isShowCollectionName!!)
                        collectionAdapterItemList.add(getCollectionNameItem(parentItem)!!)
                }
                if (parentItem.grandChildItem?.get(index) != null) {
                    horizontalStoryList.add(parentItem.grandChildItem?.get(index)!!)
                }
//                }
            }
            collectionAdapterItemList.add(getHorizontalStoryListItem(horizontalStoryList, ViewHolderType.TITLE_INSIDE_IMAGE_GRADIENT_ITEM, parentItem, WIDTH_PERCENTAGE_75)!!)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun prepareStoriesWithAd(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem) {
        try {
            if (parentItem.associatedMetadata?.isShowCollectionName!!)
                collectionAdapterItemList.add(getCollectionNameItem(parentItem)!!)

            val isShowCollectionName = parentItem.associatedMetadata?.isShowCollectionName
            var storyItemCount = 0
            storyItemCount = if (isShowCollectionName!!) 1 else 0

            parentItem.grandChildItem?.forEachIndexed { index, childItem ->
                storyItemCount++
                when (index) {
                    0 -> {
                        collectionAdapterItemList.add(getTitleBelowLargeImageStoryItem(parentItem, childItem, WIDTH_PERCENTAGE_100)!!)
                    }
                    in 1..4 -> {
                        collectionAdapterItemList.add(getLeftImageChildStoryItem(ViewHolderType.LEFT_IMAGE_CHILD_ITEM, parentItem, childItem)!!)
                    }
                    else -> {
                        collectionAdapterItemList.add(getLeftImageChildStoryItem(ViewHolderType.LEFT_IMAGE_CHILD_ITEM, parentItem, childItem)!!)
                    }
                }
            }
            if (IS_ADS_REQUIRED) {
                if (storyItemCount > NINTEEN_STORY_1AD_COMPONENT_AD_POSITION)
                    prepareNativeAdItem(collectionAdapterItemList, NINTEEN_STORY_1AD_COMPONENT_AD_POSITION, parentItem)
                else if (storyItemCount > FIVE_STORY_COMPONENT_AD_POSITION)
                    prepareNativeAdItem(collectionAdapterItemList, FIVE_STORY_COMPONENT_AD_POSITION, parentItem)
                else
                    prepareNativeAdItem(collectionAdapterItemList, storyItemCount, parentItem)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun prepareNormalStories(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem) {
        try {
            if (parentItem.associatedMetadata?.isShowCollectionName!!)
                collectionAdapterItemList.add(getCollectionNameItem(parentItem)!!)

            val isShowCollectionName = parentItem.associatedMetadata?.isShowCollectionName
            var storyItemCount = 0
            storyItemCount = if (isShowCollectionName!!) 1 else 0

            parentItem.grandChildItem?.forEachIndexed { index, childItem ->
                storyItemCount++
                when (index) {
                    0 -> {
                        collectionAdapterItemList.add(getTitleBelowLargeImageStoryItem(parentItem, childItem, WIDTH_PERCENTAGE_100)!!)
                    }
                    in 1..4 -> {
                        collectionAdapterItemList.add(getLeftImageChildStoryItem(ViewHolderType.LEFT_IMAGE_CHILD_ITEM, parentItem, childItem)!!)
                    }
                    else -> {
                        collectionAdapterItemList.add(getLeftImageChildStoryItem(ViewHolderType.LEFT_IMAGE_CHILD_ITEM, parentItem, childItem)!!)
                    }
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }


    override fun prepareStoriesWithAdAndWidget(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem) {
        try {
            if (parentItem.associatedMetadata?.isShowCollectionName!!)
                collectionAdapterItemList.add(getCollectionNameItem(parentItem)!!)

            val isShowCollectionName = parentItem.associatedMetadata?.isShowCollectionName

            var storyItemCount = 0
            storyItemCount = if (isShowCollectionName!!) 1 else 0

            parentItem.grandChildItem?.forEachIndexed { index, childItem ->
                if (childItem.type.equals(TYPE_STORY)) {
                    storyItemCount++
                    if (index == 0) {
                        collectionAdapterItemList.add(getTitleBelowLargeImageStoryItem(parentItem, childItem, WIDTH_PERCENTAGE_100)!!)
                    } else {
                        collectionAdapterItemList.add(getLeftImageChildStoryItem(ViewHolderType.LEFT_IMAGE_CHILD_ITEM, parentItem, childItem)!!)
                    }
                }
            }
            /**
             * Checking wither the storyItemCount is greater than Ad position
             * if true then add the Ad at FIVE_STORY_COMPONENT_AD_POSITION and Widget below Ad
             * or else add Ad and Widget at bottom of the list
             */
            if (IS_ADS_REQUIRED) {
                if (storyItemCount > FIVE_STORY_COMPONENT_AD_POSITION) {
                    prepareNativeAdItem(collectionAdapterItemList, FIVE_STORY_COMPONENT_AD_POSITION, parentItem)

                    prepareWidgetAdItem(collectionAdapterItemList, FIVE_STORY_COMPONENT_WIDGET_POSITION, parentItem)
                } else {
                    prepareNativeAdItem(collectionAdapterItemList, storyItemCount, parentItem)
                    prepareWidgetAdItem(collectionAdapterItemList, storyItemCount + 1, parentItem)
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun prepareStoriesHalfFeatured(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem) {
        try {
            parentItem.grandChildItem?.forEachIndexed { index, childItem ->
                if (index == 0) {
                    if (parentItem.associatedMetadata?.isShowCollectionName!!)
                        collectionAdapterItemList.add(getCollectionNameItem(parentItem)!!)
                    collectionAdapterItemList.add(getTitleBelowLargeImageStoryItem(parentItem, childItem, WIDTH_PERCENTAGE_100)!!)
                } else {
                    collectionAdapterItemList.add(getLeftImageChildStoryItem(ViewHolderType.LEFT_IMAGE_CHILD_ITEM, parentItem, childItem)!!)
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }


    /****************************************ATOMS**************************************************************************************/
    /****************************************ATOMS**************************************************************************************/
    /**
     * Function to prepare collectionName
     * "VIEWHOLDER_TYPE_COLLECTION_NAME"
     */
    private fun getCollectionNameItem(mChildItem: ChildItem): CollectionAdapterItem? {
        try {
            val mOuterCollectionAssociatedMetadata = mChildItem.associatedMetadata

            val builder = CollectionAdapterItem.Builder()
                .setOuterCollectionName(mChildItem.name)
                .setAssociatedMetadata(mOuterCollectionAssociatedMetadata)
                .setOuterCollectionSlug(mChildItem.slug!!)
                .setOuterViewHolderType(ViewHolderType.COLLECTION_NAME_ITEM)
                .setRecyclerViewColor(getThemeColor(mChildItem))
            return builder.build()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    private fun getHorizontalStoryListItem(
        mGrandChildItemList: ArrayList<ChildItem>,
        mViewHolderType: ViewHolderType,
        mChildItem: ChildItem,
        mComponentWidth: Double
    ): CollectionAdapterItem? {
        try {
            val builder = CollectionAdapterItem.Builder()
                .setChildItemList(mGrandChildItemList)
                .setOuterViewHolderType(ViewHolderType.HORIZONTAL_STORY_LIST_ITEM)
                .setInnerViewHolderType(mViewHolderType)
                .setAssociatedMetadata(mChildItem.associatedMetadata)
                .setOuterCollectionName(mChildItem.name)
                .setViewWidthPercentage(mComponentWidth)
            return builder.build()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null

    }


    /**
     * Function to prepare stories in slider
     * "VIEWHOLDER_TYPE_HOME_TOP_SLIDER"
     */
    private fun getStorySliderItem(
        childItemList: ArrayList<ChildItem>?,
        parentItem: ChildItem?
    ): CollectionAdapterItem? {
        try {
            val builder = CollectionAdapterItem.Builder()
                .setChildItemList(childItemList!!)
                .setOuterViewHolderType(ViewHolderType.HOME_TOP_SLIDER)
                .setAssociatedMetadata(parentItem?.associatedMetadata)
                .setOuterCollectionName(parentItem?.name)
                .setRecyclerViewColor(getThemeColor(parentItem!!))
            return builder.build()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null

    }


    /**
     * Function to prepare a half featured childItemList item
     * "VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HALF_FEATURED_ITEM"
     */
    private fun getTitleBelowHalfFeaturedStoryItem(
        childItem: ChildItem?,
        mOuterCollectionAssociatedMetadata: AssociatedMetadata,
        outerCollectionName: String?
    ): CollectionAdapterItem? {
        try {
            val builder = CollectionAdapterItem.Builder()
                .setChildItem(childItem)
                .setOuterViewHolderType(ViewHolderType.TITLE_BELOW_HALF_FEATURED_ITEM)
                .setAssociatedMetadata(mOuterCollectionAssociatedMetadata)
                .setOuterCollectionName(outerCollectionName)
            if (!mOuterCollectionAssociatedMetadata.isLightTheme) {
                builder.setRecyclerViewColor(R.color.colorAccent)
            } else {
                builder.setRecyclerViewColor(R.color.white)
            }
            return builder.build()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    /**
     * Function to prepare a horizontal scrolling CollectionItemList
     * viewWidthPercentage(decide the width of the layout)
     * innerViewHolderType(To display different view holder inside horizontal recyclerview)
     * bundleCollectionList(list of collectionItem to show)
     * "VIEWHOLDER_TYPE_HORIZONTAL_COLLECTION_LIST_ITEM"
     */
    private fun prepareHorizontalCollectionListItem(
        iPosition: Int,
        collectionAdapterItemList: ArrayList<CollectionAdapterItem>,
        bundleCollectionList: ArrayList<ChildItem>,
        innerViewHolderType: ViewHolderType,
        mOuterCollectionAssociatedMetadata: AssociatedMetadata,
        outerCollectionName: String?,
        viewWidthPercentage: Double
    ) {
        try {
            val builder = CollectionAdapterItem.Builder()
                .setChildItemList(bundleCollectionList)
                .setOuterViewHolderType(
                    ViewHolderType.HORIZONTAL_COLLECTION_LIST_ITEM
                )
                .setInnerViewHolderType(innerViewHolderType)
                .setAssociatedMetadata(mOuterCollectionAssociatedMetadata)
                .setOuterCollectionName(outerCollectionName)
                .setViewWidthPercentage(viewWidthPercentage)

            if (!mOuterCollectionAssociatedMetadata.isLightTheme) {
                builder.setRecyclerViewColor(R.color.colorAccent)
            } else {
                builder.setRecyclerViewColor(R.color.white)
            }

            if (mOuterCollectionAssociatedMetadata.isShowCollectionName) {
                collectionAdapterItemList.add(
                    iPosition,
                    builder.build()
                )
            } else {
                collectionAdapterItemList.add(
                    iPosition - 1,
                    builder.build()
                )
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }


    /**
     * Function to prepare a horizontal scrolling CollectionItemList
     * viewWidthPercentage(decide the width of the layout)
     * innerViewHolderType(To display different view holder inside horizontal recyclerview)
     * bundleCollectionList(list of collectionItem to show)
     */
    private fun getFocusListItem(
        bundleCollectionList: ArrayList<ChildItem>,
        innerViewHolderType: ViewHolderType,
        mOuterCollectionAssociatedMetadata: AssociatedMetadata,
        outerCollectionName: String?,
        viewWidthPercentage: Double
    ): CollectionAdapterItem? {
        try {
            val builder = CollectionAdapterItem.Builder()
                .setChildItemList(bundleCollectionList)
                .setOuterViewHolderType(ViewHolderType.VIEWPAGER_GROUP)
                .setInnerViewHolderType(innerViewHolderType)
                .setAssociatedMetadata(mOuterCollectionAssociatedMetadata)
                .setOuterCollectionName(outerCollectionName)
                .setViewWidthPercentage(viewWidthPercentage)
            if (!mOuterCollectionAssociatedMetadata.isLightTheme) {
                builder.setRecyclerViewColor(R.color.black)
            } else {
                builder.setRecyclerViewColor(R.color.white)
            }
            return builder.build()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null

    }


    /**
     * Function to prepare a childItemList with TitleBelowLargeImageItem View
     * viewWidthPercentage(decide the width of the layout)
     * "VIEWHOLDER_TYPE_TITLE_BELOW_LARGE_IMAGE_ITEM"
     */
    private fun getTitleBelowLargeImageStoryItem(
        parentItem: ChildItem?,
        mChildItem: ChildItem?,
        mComponentWidth: Double
    ): CollectionAdapterItem? {
        try {
            val builder = CollectionAdapterItem.Builder()
                .setChildItem(mChildItem)
                .setOuterViewHolderType(ViewHolderType.TITLE_BELOW_LARGE_IMAGE_ITEM)
                .setAssociatedMetadata(parentItem?.associatedMetadata)
                .setOuterCollectionName(parentItem?.name)
                .setViewWidthPercentage(mComponentWidth)
                .setRecyclerViewColor(getThemeColor(parentItem!!))
            return builder.build()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    /**
     *  Func to prepare native Ads on storyItemCount position
     *  "TYPE_NATIVE_ADS"
     */
    private fun prepareNativeAdItem(
        collectionAdapterItemList: ArrayList<CollectionAdapterItem>,
        childItemCount: Int,
        parentItem: ChildItem?
    ) {
        try {
            val builder = CollectionAdapterItem.Builder()
                .setOuterViewHolderType(ViewHolderType.NATIVE_ADS_ITEM)
                .setAssociatedMetadata(parentItem?.associatedMetadata)
                .setOuterCollectionName(parentItem?.name)

            if (collectionAdapterItemList.size >= childItemCount) {
                if (parentItem?.associatedMetadata?.isShowCollectionName!!) {
                    collectionAdapterItemList.add(
                        childItemCount,
                        builder.build()
                    )
                } else {
                    collectionAdapterItemList.add(
                        childItemCount - 1,
                        builder.build()
                    )
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }


    /**
     *  Func to prepare native Ads on storyItemCount position
     *  "TYPE_NATIVE_ADS"
     */
    private fun prepareNativeAdItem2(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, childItemCount: Int, parentItem: ChildItem?) {
        try {
            val builder = CollectionAdapterItem.Builder()
                .setOuterViewHolderType(ViewHolderType.NATIVE_ADS_ITEM2)
                .setAssociatedMetadata(parentItem?.associatedMetadata)
                .setOuterCollectionName(parentItem?.name)

            if (collectionAdapterItemList.size >= childItemCount) {
                if (parentItem?.associatedMetadata?.isShowCollectionName!!) {
                    collectionAdapterItemList.add(childItemCount, builder.build())
                } else {
                    collectionAdapterItemList.add(childItemCount - 1, builder.build())
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }


    /**
     *  Func to prepare widget Ads on storyItemCount position
     *  "TYPE_WIDGET_ADS"
     */
    private fun prepareWidgetAdItem(
        collectionAdapterItemList: ArrayList<CollectionAdapterItem>,
        childItemCount: Int,
        parentItem: ChildItem?
    ) {
        try {
            val builder = CollectionAdapterItem.Builder()
                .setOuterViewHolderType(ViewHolderType.WIDGET_ADS_ITEM)
                .setAssociatedMetadata(parentItem?.associatedMetadata)
                .setOuterCollectionName(parentItem?.name)
            if (collectionAdapterItemList.size >= childItemCount) {
                if (parentItem?.associatedMetadata?.isShowCollectionName!!) {
                    collectionAdapterItemList.add(childItemCount, builder.build())
                } else {
                    collectionAdapterItemList.add(
                        childItemCount - 1,
                        builder.build()
                    )
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    /**
     * Adding widget at the end of every component
     */
    fun prepareWidget(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem?) {
        try {
            val builder = CollectionAdapterItem.Builder()
                .setOuterViewHolderType(ViewHolderType.WIDGET_ADS_ITEM)
                .setAssociatedMetadata(parentItem?.associatedMetadata)
                .setOuterCollectionName(parentItem?.name)
            collectionAdapterItemList.add(builder.build())
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun prepareBundleContainer(
        collectionAdapterItemList: ArrayList<CollectionAdapterItem>,
        childItem: ChildItem?,
        metadata: Metadata,
        count: Int,
        outerCollectionName: String?
    ) {
        try {
            val builder = CollectionAdapterItem.Builder()
                .setMetaData(metadata)
                .setOuterViewHolderType(ViewHolderType.BUNDLE_SNAPSHOT_LABEL_CONTAINER)
                .setTotalItemCount(count)
                .setOuterCollectionName(outerCollectionName)
            if (null != childItem) {
                builder.setChildItem(childItem)
            }
            collectionAdapterItemList.add(0, builder.build())
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun getLeftImageChildStoryItem(viewHolderType: ViewHolderType, parentItem: ChildItem?, mChildItem: ChildItem?): CollectionAdapterItem? {
        try {
            val builder = CollectionAdapterItem.Builder()
                .setChildItem(mChildItem)
                .setOuterViewHolderType(viewHolderType)
                .setRecyclerViewColor(getThemeColor(parentItem!!))
            if (parentItem.associatedMetadata != null) {
                builder.setAssociatedMetadata(parentItem.associatedMetadata)
            }
            if (parentItem.name != null) {
                builder.setOuterCollectionName(parentItem.name)
            }
            return builder.build()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    /**
     * Function to prepare a childItemList with LeftImageMedium UI
     * "LEFT_IMAGE_MEDIUM_ITEM"
     */
    private fun getMediumStoryItem(
        parentItem: ChildItem?,
        mChildItem: ChildItem?
    ): CollectionAdapterItem? {
        try {
            val builder = CollectionAdapterItem.Builder()
                .setChildItem(mChildItem)
                .setOuterViewHolderType(ViewHolderType.LEFT_IMAGE_MEDIUM_ITEM)
                .setOuterCollectionName(parentItem?.name)
                .setRecyclerViewColor(getThemeColor(parentItem!!))

            if (parentItem.associatedMetadata != null) {
                builder.setAssociatedMetadata(parentItem.associatedMetadata)
            }

            return builder.build()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    /**
     * Function to prepare a childItemList view with LargeImageStory UI
     * "VIEWHOLDER_TYPE_LARGE_IMAGE_STORY"
     */
    private fun getLargeImageStoryItem(
        parentItem: ChildItem?,
        mChildItem: ChildItem?
    ): CollectionAdapterItem? {
        try {
            val builder = CollectionAdapterItem.Builder()
                .setChildItem(mChildItem)
                .setOuterViewHolderType(ViewHolderType.LARGE_IMAGE_STORY_ITEM)
                .setAssociatedMetadata(parentItem?.associatedMetadata)
                .setOuterCollectionName(parentItem?.name)
                .setRecyclerViewColor(getThemeColor(parentItem!!))
            return builder.build()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    /**
     * Function to prepare a childItemList view with LargeImageStory UI
     * "TITLE_BELOW_HALF_FEATURED_ITEM"
     */
    private fun getHalfTitleBelowLargeImageStoryItem(
        parentItem: ChildItem?,
        mChildItem: ChildItem?
    ): CollectionAdapterItem? {
        try {
            val builder = CollectionAdapterItem.Builder()
                .setChildItem(mChildItem)
                .setOuterViewHolderType(ViewHolderType.TITLE_BELOW_HALF_FEATURED_ITEM)
                .setAssociatedMetadata(parentItem?.associatedMetadata)
                .setOuterCollectionName(parentItem?.name)
                .setRecyclerViewColor(getThemeColor(parentItem!!))
            return builder.build()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null

    }

    /**
     * Function to prepare collectionNameLoadMore
     * "VIEWHOLDER_TYPE_LOAD_MORE"
     */
    private fun getCollectionNameLoadMoreItem(
        parentItem: ChildItem?,
        showTopPadding: Boolean,
        associatedMetadata: AssociatedMetadata?
    ): CollectionAdapterItem? {
        try {
            val builder = CollectionAdapterItem.Builder()
                .setOuterViewHolderType(ViewHolderType.TYPE_LOAD_MORE)
                .setAssociatedMetadata(associatedMetadata)
                .setOuterCollectionName(parentItem?.name)
                .setOuterCollectionSlug(parentItem?.slug!!)
                .setRecyclerViewColor(getThemeColor(parentItem))
                .setLoadMoreTopPadding(showTopPadding)
            return builder.build()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null

    }

    /**
     * Function to prepare a horizontal scrolling childItemList
     * viewWidthPercentage(decide the width of the layout)
     * innerViewHolderType(To display different view holder inside horizontal recyclerview)
     * horizontalStoryList(list of storylist to show)
     * "VIEWHOLDER_TYPE_HORIZONTAL_STORY_LIST_ITEM"
     */
    private fun getTrendingStoriesItem(
        collectionAdapterItemList: ArrayList<CollectionAdapterItem>,
        trendingChildItemList: ArrayList<ChildItem>,
        trendingStoriesPosition: Int,
        mOuterCollectionAssociatedMetadata: AssociatedMetadata,
        outerCollectionName: String?,
        outerCollectionSlug: String
    ) {
        try {
            val trendingCollectionItemList = ArrayList<CollectionAdapterItem>()

            trendingChildItemList.forEachIndexed { index, childItem ->
                var builder = CollectionAdapterItem.Builder()
                if (index == 0) {
                    if (!mOuterCollectionAssociatedMetadata.isLightTheme) {
                        builder.setRecyclerViewColor(R.color.colorAccent)
                    } else {
                        builder.setRecyclerViewColor(R.color.white)
                    }

                    builder.setOuterViewHolderType(
                        ViewHolderType.COLLECTION_NAME_ITEM
                    )
                    builder.setAssociatedMetadata(
                        mOuterCollectionAssociatedMetadata
                    )
                    builder.setOuterCollectionName(outerCollectionName)
                    builder.setOuterCollectionSlug(outerCollectionSlug)
                    builder.isTrendingCollection(true)
                    trendingCollectionItemList.add(builder.build())
                }
                //create a new component
                builder = CollectionAdapterItem.Builder()
                builder.setChildItem(trendingChildItemList[index])
                builder.setOuterViewHolderType(ViewHolderType.TRENDING_STORIES_TYPE)
                builder.setAssociatedMetadata(mOuterCollectionAssociatedMetadata)
                builder.setOuterCollectionName(outerCollectionName)
                builder.setTrendingStoryNumber(index)
                trendingCollectionItemList.add(builder.build())
            }

            if (collectionAdapterItemList.size >= trendingStoriesPosition) {
                collectionAdapterItemList.addAll(
                    trendingStoriesPosition,
                    trendingCollectionItemList
                )
            } else {
                collectionAdapterItemList.addAll(
                    trendingStoriesPosition - 1,
                    trendingCollectionItemList
                )
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun getThemeColor(parentItem: ChildItem): Int {
        return if (null != parentItem.associatedMetadata && !parentItem.associatedMetadata?.isLightTheme!!) {
            R.color.colorAccent
        } else {
            R.color.white
        }
    }
}