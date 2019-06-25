package com.quintype.oxygen.services

import android.os.Handler
import android.os.Looper
import com.quintype.oxygen.COLLECTION_METADATA_TYPE_BUNDLE
import com.quintype.oxygen.COMPONENT_FIVE_STORY_1AD
import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.OxygenConstants.Companion.STORY_LIMIT
import com.quintype.oxygen.OxygenConstants.Companion.TRENDING_STORY_LIMIT
import com.quintype.oxygen.isInnerCollectionRequired
import com.quintype.oxygen.models.BulkTableModel
import com.quintype.oxygen.models.InnerCollectionItemModel
import com.quintype.oxygen.models.collection.AssociatedMetadata
import com.quintype.oxygen.models.collection.CollectionItem
import com.quintype.oxygen.models.collection.CollectionResponse
import com.quintype.oxygen.utils.widgets.logdExt
import com.quintype.oxygen.utils.widgets.logeExt
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

/**
 * Used to notify the data which is received from the webservices.
 *
 * Note: Facing the issue with live data as livedata wont notify when activity/fragment is in STOP state.
 *
 * here we are using the listeners to notify the data.
 */

class HomeCollectionService {
    private var mCollectionApiService: CollectionApiService =
        RetrofitApiClient.getRetrofitApiClient().create(CollectionApiService::class.java)
    var mCollectionModelList = ArrayList<BulkTableModel>()
    private var mObserver: Disposable? = null

    /*Avoid using companion object to create new instances.*/
    private var itemType: String? = null

    fun getCollectionResponse(
        collectionSlug: String, pageNumber: Int, iPageLimit: Int, iCollectionLimit: Int, listener:IListener?
    ){
        mCollectionModelList.clear()
        mObserver?.dispose()
        logdExt("First Iteration Collection Slug - " + collectionSlug + "  Limit - " + iCollectionLimit + " Offset - " + pageNumber * iCollectionLimit)
        mObserver = mCollectionApiService.getCollectionApiService(
            collectionSlug,
            iCollectionLimit,
            pageNumber * iCollectionLimit,
            OxygenConstants.STORY_FIELDS
        )
            .concatMap { mCollectionResponse ->
                logdExt("Collection Response home level :${mCollectionResponse.slug}, Items Size :${mCollectionResponse.items?.size}")

                val isBundleCollection =
                    COLLECTION_METADATA_TYPE_BUNDLE == mCollectionResponse.collectionMetadata.type?.get(0)?.name
                if (isBundleCollection) {
                    val associatedMetadata = AssociatedMetadata()
                    associatedMetadata.associatedMetadataLayout = COMPONENT_FIVE_STORY_1AD
                    associatedMetadata.associatedMetadataNumberOfStoriesToShow = STORY_LIMIT

                    val collectionInnerItemList = ArrayList<InnerCollectionItemModel>()
                    for (index in 0 until mCollectionResponse.items?.size as Int) {
                        val mCollectionItem = mCollectionResponse.items?.get(index)
                        collectionInnerItemList.add(
                            InnerCollectionItemModel(
                                mCollectionItem as CollectionItem,
                                mCollectionResponse
                            )
                        )
                    }

                    val bulkTableModel = BulkTableModel(
                        mCollectionResponse.slug,
                        null,
                        mCollectionResponse.name,
                        mCollectionResponse.summary,
                        associatedMetadata,
                        mCollectionResponse.template,
                        null,
                        collectionInnerItemList,
                        null,
                        mCollectionResponse.collectionMetadata,
                        mCollectionResponse.totalCount
                    )
//                    //notifying the main thread, as its a child thread
                    Handler(Looper.getMainLooper()).post {
                        listener?.notifyChange(arrayListOf(bulkTableModel))
                    }

                    mCollectionModelList.add(bulkTableModel)
                } else {
                    for (index in 0 until mCollectionResponse.items?.size as Int) {
                        val mCollectionItem = mCollectionResponse.items?.get(index)

                        if (mCollectionItem?.type == OxygenConstants.TYPE_COLLECTION) {
                            logdExt("The " + index + "th collection Item is COLLECTION type, the slug is " + mCollectionItem.slug)
                            val bulkTableModel = BulkTableModel(
                                mCollectionItem.slug,
                                null,
                                mCollectionItem.name,
                                mCollectionResponse.summary,
                                mCollectionItem.associatedMetadata,
                                mCollectionItem.template,
                                null,
                                null,
                                null,
                                mCollectionResponse.collectionMetadata,
                                mCollectionResponse.totalCount
                            )
                            mCollectionModelList.add(bulkTableModel)
                        } else if (mCollectionItem?.type == OxygenConstants.TYPE_STORY) {
                            logdExt("The " + index + "th collection Item is STORY type, the headline is " + mCollectionItem.story?.headline)
                            val bulkTableModel = BulkTableModel(
                                mCollectionItem.story?.slug,
                                mCollectionItem.story,
                                mCollectionResponse.name,
                                mCollectionResponse.summary,
                                null,
                                null,
                                null,
                                null,
                                null,
                                mCollectionResponse.collectionMetadata,
                                mCollectionResponse.totalCount
                            )
//                            //notifying the main thread, as its a child thread
                            Handler(Looper.getMainLooper()).post {
                                listener?.notifyChange(arrayListOf(bulkTableModel))
                            }
                            mCollectionModelList.add(bulkTableModel)
                        }

                    }
                }

                return@concatMap Flowable.fromIterable(mCollectionResponse.items)
            }
            .filter { mCollectionItem -> mCollectionItem.type.equals(OxygenConstants.TYPE_COLLECTION) }
            .concatMap { mCollectionItem ->
                logdExt("Start 2nd level collection api of :${mCollectionItem.slug}")
                var PAGE_LIMIT_CHILD = iPageLimit
                val noOfStoriesToShow = mCollectionItem.associatedMetadata?.associatedMetadataNumberOfStoriesToShow
                val noOfSliderStoriesToShow =
                    mCollectionItem.associatedMetadata?.associatedMetadataNumberOfSliderStoriesToShow
                val noOfChildStoriesToShow =
                    mCollectionItem.associatedMetadata?.associatedMetadataNumberOfChildStoriesToShow
                val noOfStoriesInsideCollectionToShow =
                    mCollectionItem.associatedMetadata?.associatedMetadataNumberOfStoriesInsideCollectionToShow
                val noOfCollectionToShow =
                    mCollectionItem.associatedMetadata?.associatedMetadataNumberOfCollectionsToShow

                /**
                 *  Check no_of_collection_to_show if size is greater than 0 then check for noOfStoriesInsideCollectionToShow,noOfStoriesToShow and noOfChildStoriesToShow
                 *  and set item-type and pageLimit
                 */
                if (noOfCollectionToShow != null && noOfCollectionToShow > 0) {
                    if ((noOfStoriesInsideCollectionToShow != null && noOfStoriesInsideCollectionToShow > 0) && (noOfStoriesToShow == null || noOfStoriesToShow == 0) && (noOfChildStoriesToShow == null || noOfChildStoriesToShow == 0)) {
                        itemType = OxygenConstants.TYPE_COLLECTION
                    } else {
                        itemType = null
                    }

                    PAGE_LIMIT_CHILD =
                        if ((noOfSliderStoriesToShow != null && noOfSliderStoriesToShow > 0) && (noOfChildStoriesToShow != null && noOfChildStoriesToShow > 0))
                            noOfSliderStoriesToShow.plus(noOfChildStoriesToShow).plus(noOfCollectionToShow)

//                    else if (noOfStoriesInsideCollectionToShow != null && noOfStoriesInsideCollectionToShow > 0) {
//                        PAGE_LIMIT_CHILD = noOfStoriesInsideCollectionToShow.plus(noOfCollectionToShow)
//                    }
                        else if (noOfStoriesToShow != null && noOfStoriesToShow > 0)
                            noOfCollectionToShow.plus(noOfStoriesToShow)
                        else
                            noOfCollectionToShow
                } else if (noOfStoriesToShow != null && noOfStoriesToShow > 0) {
                    itemType = OxygenConstants.TYPE_STORY
                    PAGE_LIMIT_CHILD = noOfStoriesToShow
                } else {
                    itemType = OxygenConstants.TYPE_STORY
                    PAGE_LIMIT_CHILD = iPageLimit
                }

                /**
                 * Using getCollectionOnlyStoriesApiService for getting only stories
                 */
                if (itemType != null && itemType.equals(OxygenConstants.TYPE_STORY)) {
                    return@concatMap mCollectionApiService.getCollectionOnlyStoriesApiService(
                        mCollectionItem.slug as String,
                        PAGE_LIMIT_CHILD,
                        0,
                        OxygenConstants.TYPE_STORY,
                        OxygenConstants.STORY_FIELDS
                    )
                } else if (itemType != null && itemType.equals(OxygenConstants.TYPE_COLLECTION)) {
                    return@concatMap mCollectionApiService.getCollectionOnlyStoriesApiService(
                        mCollectionItem.slug as String,
                        PAGE_LIMIT_CHILD,
                        0,
                        OxygenConstants.TYPE_COLLECTION,
                        OxygenConstants.STORY_FIELDS
                    )
                } else {
                    return@concatMap mCollectionApiService.getCollectionApiService(
                        mCollectionItem.slug as String,
                        PAGE_LIMIT_CHILD,
                        0,
                        OxygenConstants.STORY_FIELDS
                    )
                }
            }
            .map { collectionResponseModel ->
                logdExt("Collection Response 2nd level :${collectionResponseModel.slug}, Items Size :${collectionResponseModel.items?.size}")
                mCollectionModelList.forEach { bulkTableModel ->
                    if (bulkTableModel.slug.equals(collectionResponseModel.slug) && collectionResponseModel.items != null && (collectionResponseModel.items?.size as Int) > 0) {
                        val innerCollectionFirstItem = collectionResponseModel.items?.get(0)
                        bulkTableModel.outerCollectionInnerSlug = innerCollectionFirstItem?.slug

                        val innerCollectionItemModelList = ArrayList<InnerCollectionItemModel>()
                        collectionResponseModel.items?.forEach { collectionItem ->
                            innerCollectionItemModelList.add(InnerCollectionItemModel(collectionItem, null))
                        }
                        bulkTableModel.outerCollectionInnerItem = innerCollectionItemModelList

                        collectionResponseModel.items?.forEach { collectionItem ->
                            if (OxygenConstants.TYPE_COLLECTION == collectionItem.type && OxygenConstants.WIDGET_TEMPLATE != collectionItem.template) {
//                                val getInnerCollection = bulkTableModel.mOuterCollectionAssociatedMetadata?.associatedMetadataGetInnerCollection
                                val getInnerCollection =
                                    isInnerCollectionRequired(bulkTableModel.mOuterCollectionAssociatedMetadata?.associatedMetadataLayout)
                                val collectionMetadataType = collectionItem.metadata?.type
                                /**
                                 * check wither getInnerCollection is true or not.
                                 * if false then check collection metadata type
                                 *      if type is OxygenConstants.KEY_TRENDING then make one more API call to get collection
                                 *      else return the response
                                 */
                                if (getInnerCollection || (collectionMetadataType != null && collectionMetadataType.isNotEmpty() && collectionItem.metadata?.type?.get(
                                        0
                                    )?.key.equals(
                                        OxygenConstants.KEY_TRENDING
                                    ))
                                ) {
                                    if (collectionItem.slug != null) {
                                        logdExt("Start of child the ${collectionItem.slug}")
                                        var noOfStoriesInsideCollectionToShow =
                                            bulkTableModel.mOuterCollectionAssociatedMetadata?.associatedMetadataNumberOfStoriesInsideCollectionToShow as Int
                                        if (noOfStoriesInsideCollectionToShow == 0)
                                            noOfStoriesInsideCollectionToShow = TRENDING_STORY_LIMIT

                                        getChildRxResponse(
                                            collectionItem.slug as String,
                                            noOfStoriesInsideCollectionToShow,
                                            bulkTableModel
                                        )
                                    }
                                }
                            }
                        }

//                        //notifying the main thread, as its a child thread
                        Handler(Looper.getMainLooper()).post {
                            listener?.notifyChange(arrayListOf(bulkTableModel))
                        }

                    }
                }

                return@map collectionResponseModel
            }
            .subscribeOn(Schedulers.io())
            .retry(3)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : ResourceSubscriber<CollectionResponse>() {
                override fun onComplete() {
                    logdExt("CollectionModelList Size onComplete - " + mCollectionModelList.size)
                    listener?.notifyComplete()
                }

                override fun onNext(mCollectionsModel: CollectionResponse) {
                    logdExt("CollectionModelList collectionResponse - $mCollectionsModel")
                }

                override fun onError(e: Throwable) {
                   listener?.notifyError(e)
                    logeExt("First iteration API failed for collection SLUG " + collectionSlug + ", getting " + e.message)
                }
            })
    }

    /**
     * blocking call as its required to call the inner collection and notify
     */
    private fun getChildRxResponse(collectionSlug: String, iPageLimit: Int, bulkTableModel: BulkTableModel) {
        val collectionApiService: CollectionApiService =
            RetrofitApiClient.getRetrofitApiClient().create(CollectionApiService::class.java)
        logdExt("Second Iteration Collection Slug - $collectionSlug")
        val subscribeWith = collectionApiService.getCollectionOnlyStoriesApiService(
            collectionSlug,
            iPageLimit,
            0,
            OxygenConstants.TYPE_STORY,
            OxygenConstants.STORY_FIELDS
        ).map { collectionModel ->
            bulkTableModel.outerCollectionInnerItem?.forEach { outerCollectionInnerItem ->
                if (outerCollectionInnerItem.mCollectionItem.slug.equals(collectionModel.slug)) {
                    outerCollectionInnerItem.mCollectionInnerResponse = collectionModel
                }
            }
            return@map collectionModel
        }
            .subscribeWith(object : ResourceSubscriber<CollectionResponse>() {
                override fun onComplete() {
                    logdExt("Completed the child collection response")
                }

                override fun onNext(mCollectionsModel: CollectionResponse) {
                    logdExt("Collection Response 3rd/child level :${mCollectionsModel.slug}, Items Size :${mCollectionsModel.items?.size}")
                }

                override fun onError(e: Throwable) {
                    logdExt("Second iteration API failed for collection SLUG " + collectionSlug + ", getting " + e.message)
                }
            })
    }

    /**
     * listener for the data changes
     */
    interface IListener {
        fun notifyChange(data: ArrayList<BulkTableModel>)

        fun notifyError(error: Throwable)

        fun notifyComplete()
    }
}