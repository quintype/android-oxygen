package com.quintype.androidoxygen.services

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.quintype.androidoxygen.OxygenConstants
import com.quintype.androidoxygen.ErrorHandler
import com.quintype.androidoxygen.models.BulkTableModel
import com.quintype.androidoxygen.models.collection.CollectionResponse
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

/**
 * Created TemplateCollectionWithRx by rakshith on 7/23/18.
 */

class CollectionService {
    var TAG = CollectionService::class.java.simpleName
    var collectionApiService: CollectionApiService =
        RetrofitApiClient.getRetrofitApiClient().create(CollectionApiService::class.java)
    var collectionData: MutableLiveData<BulkTableModel> = MutableLiveData()
    var collectionModelList = ArrayList<BulkTableModel>()

    /*Avoid using companion object to create new instances.*/

    fun getCollectionResponse(
        collectionSlug: String,
        pageNumber: Int, iPageLimit: Int,
        iCollectionLimit: Int,
        errorHandler: ErrorHandler?
    ): LiveData<BulkTableModel> {
        /*We are clearing the list when ever the page count is '0'. On swipe to refresh this will get executed. */
        if (pageNumber == 0 && collectionModelList.size > 0)
            collectionModelList.clear()
        Log.d(
            TAG,
            "First Iteration Collection Slug - " + collectionSlug + " Limit - " + iCollectionLimit + " Offset - " + pageNumber * iCollectionLimit
        )
        Log.d(TAG, "CollectionModelList Size before API call - " + collectionModelList.size)
        val subscribeWith = collectionApiService.getCollectionApiService(
            collectionSlug,
            iCollectionLimit,
            pageNumber * iCollectionLimit,
            OxygenConstants.STORY_FIELDS
        )
            .doOnError { error -> Log.d(TAG, "error is " + error.message) }
            .retry(3)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { mCollectionResponse ->
                Log.d(TAG, "OnSuccess the total collectionItems is - " + mCollectionResponse.items?.size)
                for (index in 0 until mCollectionResponse.items?.size as Int) {
                    val mCollectionItem = mCollectionResponse.items?.get(index)
                    if (mCollectionItem?.type == OxygenConstants.TYPE_COLLECTION) {
                        Log.d(
                            TAG,
                            "The " + index + "th collection Item is COLLECTION type, the slug is " + mCollectionItem.slug
                        )
                        val bulkTableModel = BulkTableModel(
                            mCollectionItem.slug,
                            null,
                            mCollectionItem.name,
                            mCollectionItem.associatedMetadata,
                            mCollectionItem.template,
                            null,
                            null,
                            null,
                            null
                        )
                        collectionModelList.add(bulkTableModel)
                        collectionData.value = bulkTableModel
                    } else if (mCollectionItem?.type == OxygenConstants.TYPE_STORY) {
                        Log.d(
                            TAG,
                            "The " + index + "th collection Item is STORY type, the headline is " + mCollectionItem.story?.headline
                        )
                        val bulkTableModel = BulkTableModel(
                            mCollectionItem.story?.slug,
                            mCollectionItem.story,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null
                        )
                        //                            var collectionHashMap = collectionOrderHashMap.put(mCollectionItem?.story?.slug as String, bulkTableModel)
                        collectionModelList.add(bulkTableModel)
                        collectionData.value = bulkTableModel
                    }
                }
                Flowable.fromIterable(mCollectionResponse.items)
            }
            .filter { mCollectionItem -> mCollectionItem.type.equals(OxygenConstants.TYPE_COLLECTION) }
            .concatMapEager { mCollectionItem ->
                var PAGE_LIMIT_CHILD = iPageLimit
                val noOfStoriesToShow = mCollectionItem.associatedMetadata?.associatedMetadataNumberOfStoriesToShow
                if (noOfStoriesToShow != null && noOfStoriesToShow > 0) {
                    PAGE_LIMIT_CHILD = noOfStoriesToShow
                }
//                    return@concatMapEager collectionApiService.getCollectionApiService(mCollectionItem?.slug as String, PAGE_LIMIT_CHILD, 0)
                /**
                 * Using getCollectionOnlyStoriesApiService for getting only stories
                 */
                return@concatMapEager collectionApiService.getCollectionOnlyStoriesApiService(
                    mCollectionItem.slug as String,
                    PAGE_LIMIT_CHILD,
                    0,
                    OxygenConstants.TYPE_STORY,
                    OxygenConstants.STORY_FIELDS
                )
                    .doOnError { error -> Log.d(TAG, "error is " + error.message) }
                    .retry(3)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            .subscribeWith(object : ResourceSubscriber<CollectionResponse>() {
                override fun onComplete() {
                    Log.d(TAG, "onComplete of First iteration")
//                        collectionData.value = collectionModelList
                    Log.d(TAG, "CollectionModelList Size onComplete - " + collectionModelList.size)
                    errorHandler?.onAPISuccess()
                }

                override fun onNext(mCollectionsModel: CollectionResponse) {
                    val mCollectionSlug = mCollectionsModel.slug
                    val mCollectionItems = mCollectionsModel.items
                    val mCollectionSize = mCollectionItems?.size as Int
                    Log.d(TAG, " onNext of collectionItem slug - ${mCollectionsModel.slug}")
//                        if (mCollectionSize > 4)
//                            mCollectionSize = 4
                    for (index in 0 until collectionModelList.size) {
                        if (collectionModelList[index].slug?.equals(mCollectionSlug) == true) {
                            val bulkModel: BulkTableModel = collectionModelList[index]
                            if (mCollectionsModel.items?.size as Int > 0) {
                                val innerCollectionFirstItem = mCollectionsModel.items?.get(0)
                                bulkModel.outerCollectionInnerSlug = innerCollectionFirstItem?.slug
                                bulkModel.outerCollectionInnerItem = innerCollectionFirstItem
                            }
                        }
                    }
                    for (index in 0 until mCollectionSize) {
                        val mCollectionItem = mCollectionItems.get(index)
                        if (index == 0 && mCollectionItem.type?.equals(OxygenConstants.TYPE_COLLECTION) as Boolean) {
                            if (mCollectionItem.template?.equals(OxygenConstants.WIDGET_TEMPLATE) == false) {
                                getChildRxResponse(mCollectionItem.slug as String, iPageLimit)
                                //todo call child collection for 1st position if 0th position is widget
                            }
                        } else //todo call child collection for 1st position if 0th position is widget
                        {
                            if (mCollectionItem.type?.equals(OxygenConstants.TYPE_STORY) as Boolean) {
                                for (collectionListIndex in 0 until collectionModelList.size) {
                                    if (collectionModelList.get(collectionListIndex).slug?.equals(mCollectionSlug) == true) {
                                        val bulkModel: BulkTableModel = collectionModelList.get(collectionListIndex)
                                        bulkModel.innerCollectionResponse = mCollectionsModel
                                        collectionData.value = bulkModel
                                    }
                                    //Log.d(TAG, "api call success and onNext collectionSlug == $mCollectionSlug &&  collectionStory headline is ${mCollectionItem.story?.headline}")
                                }
                            }
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    errorHandler?.onAPIFailure()
                    Log.d(
                        TAG,
                        "First iteration API failed for collection SLUG " + collectionSlug + ", getting " + e.message
                    )
                }
            })
        return collectionData
    }

    fun getChildRxResponse(collectionSlug: String, iPageLimit: Int) {
        val collectionApiService: CollectionApiService =
            RetrofitApiClient.getRetrofitApiClient().create(CollectionApiService::class.java)
        Log.d(TAG, "Second Iteration Collection Slug - " + collectionSlug)
        val subscribeWith = collectionApiService.getCollectionOnlyStoriesApiService(
            collectionSlug,
            iPageLimit,
            0,
            OxygenConstants.TYPE_STORY,
            OxygenConstants.STORY_FIELDS
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
//                .flatMap({ mCollectionResponse -> Flowable.fromIterable(mCollectionResponse.items) })
            .subscribeWith(object : ResourceSubscriber<CollectionResponse>() {
                override fun onComplete() {
                    Log.d(TAG, " Second iteration api call completed")
                }

                override fun onNext(mCollectionsModel: CollectionResponse) {
                    val mCollectionSlug = mCollectionsModel.slug
                    val mCollectionItems = mCollectionsModel.items
                    val mCollectionSize = mCollectionItems?.size as Int
                    for (index in 0 until collectionModelList.size) {
                        if (collectionModelList[index].outerCollectionInnerSlug?.equals(mCollectionSlug) == true && collectionModelList[index].outerCollectionInnerSlug?.isEmpty() == false) {
                            val bulkModel: BulkTableModel = collectionModelList.get(index)
                            bulkModel.innerCollectionResponse = mCollectionsModel
                            collectionData.value = bulkModel
                        }
                    }
                    Log.d(TAG, "onNext of second iteration collectionItem slug - ${mCollectionsModel.slug}")
                    for (index in 0 until mCollectionSize) {
                        val mCollectionItem = mCollectionItems[index]
                        if (index == 0 && mCollectionItem.type?.equals(OxygenConstants.TYPE_COLLECTION) as Boolean) {
                            if (mCollectionItem.template?.equals(OxygenConstants.WIDGET_TEMPLATE) == false) {
                                getChildRxResponse(mCollectionItem.slug as String, iPageLimit)
                            }
                        } else if (mCollectionItem.type?.equals(OxygenConstants.TYPE_STORY) as Boolean) {
                            //Log.d(TAG, " second iteration api call success and onNext collectionSlug == $mCollectionSlug &&  collectionStory headline is ${mCollectionItem.story?.headline}")
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d(
                        TAG,
                        "Second iteration API failed for collection SLUG " + collectionSlug + ", getting " + e.message
                    )
                }
            })
    }
}