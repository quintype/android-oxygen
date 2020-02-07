package com.quintype.oxygen.components

import com.quintype.oxygen.models.CollectionAdapterItem
import com.quintype.oxygen.models.latest.home.ChildItem

interface IComponent {

    fun prepareBreakingNews(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem)

    fun prepareSingleStory(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem)

    fun prepareDefault(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem)

    fun prepareSliderStories(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem)

    fun prepareCollectionAndStories(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem)

    fun prepareStoriesWithAd(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem)

    fun prepareMediaStories(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem)

    fun prepareStoriesWithAdAndWidget(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem)

    fun prepareNormalStories(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem)

    fun prepareStoriesHalfFeatured(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem)


    /**
     * Components for prothomalo
     */
    fun prepareComponentOne(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem)

    fun prepareComponentTwo(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem)

    fun prepareComponentThree(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem)

    fun prepareComponentFour(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem)

    fun prepareComponentFive(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem)

    fun prepareComponentSix(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem)

    fun prepareComponentSeven(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem)

    fun prepareComponentEight(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem)

    fun prepareComponentNine(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem)

    fun prepareComponentTen(collectionAdapterItemList: ArrayList<CollectionAdapterItem>, parentItem: ChildItem)
}

