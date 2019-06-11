package com.quintype.oxygen.services

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.quintype.oxygen.ErrorHandler
import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.models.collection.CollectionResponse
import com.quintype.oxygen.models.story.Story
import com.quintype.oxygen.utils.widgets.logdExt
import com.quintype.oxygen.utils.widgets.logeExt
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class AuthorProfileService {
    private var mAuthorProfileApiService: AuthorProfileApiService =
        RetrofitApiClient.getRetrofitApiClient().create(AuthorProfileApiService::class.java)
    private var mObserver: Disposable? = null
    var mCollectionData: MutableLiveData<List<Story>> = MutableLiveData()

    /**
     * Method to get only stories written by the author
     */
    fun getAuthorProfileStoriesResponse(
        mAuthorId: String,
        pageNumber: Int,
        iPageLimit: Int,
        errorHandler: ErrorHandler?
    ): LiveData<List<Story>> {
        mObserver?.dispose()
        logdExt("First Iteration Collection Slug - " + mAuthorId + "  Limit - " + iPageLimit + " Offset - " + pageNumber * iPageLimit)

        //story list
        val storyList = ArrayList<Story>()

        mObserver = mAuthorProfileApiService.getStoriesByAuthorApiService(
            mAuthorId,
            iPageLimit,
            pageNumber,
            OxygenConstants.TYPE_STORY,
            OxygenConstants.STORY_FIELDS
        )
            .map { mAuthorProfileResponse ->
                mAuthorProfileResponse.items?.forEach { mCollectionItem ->
                    if (mCollectionItem.story != null)
                        storyList.add(mCollectionItem.story as Story)
                }

                return@map mAuthorProfileResponse
            }
            .subscribeOn(Schedulers.io())
            .retry(3)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : ResourceSubscriber<CollectionResponse>() {
                override fun onComplete() {
                    logdExt("CollectionModelList Size onComplete - " + storyList.size)
                    mCollectionData.value = storyList
                    errorHandler?.onAPISuccess()
                }

                override fun onNext(mCollectionsModel: CollectionResponse) {
                    logdExt("CollectionModelList collectionResponse - $mCollectionsModel")
                }

                override fun onError(e: Throwable) {
                    errorHandler?.onAPIFailure()
                    logeExt("First iteration API failed for collection SLUG " + mAuthorId + ", getting " + e.message)
                }
            })
        return mCollectionData
    }
}