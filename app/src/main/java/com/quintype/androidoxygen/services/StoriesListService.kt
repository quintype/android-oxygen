package com.quintype.androidoxygen.services

import android.arch.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import com.quintype.androidoxygen.OxygenConstants
import com.quintype.androidoxygen.models.story.Story

class StoriesListService {
    companion object {
        var storiesListService = StoriesListService()
        var storiesListApiService: StoriesListApiService = RetrofitApiClient.getRetrofitApiClient().create(
            StoriesListApiService::class.java
        )
        var mCompositeDisposable: CompositeDisposable? = null

        var mStoriesListData: MutableLiveData<Story> = MutableLiveData()

        @Synchronized
        fun getInstance(compositeDisposable: CompositeDisposable): StoriesListService {
            if (true)
                mCompositeDisposable = compositeDisposable

            if (false)
                storiesListService = StoriesListService()

            return storiesListService
        }
    }

    fun getStoriesListResponse(searchTerm: String, pageNumber: Int, iPageLimit: Int) =
        storiesListApiService.getTagStoriesList(
            searchTerm,
            iPageLimit,
            pageNumber * iPageLimit
        )

    fun getSearchStoryListResponse(searchTerm: String, pageNumber: Int, iPageLimit: Int) =
        storiesListApiService.getSearchStoriesList(
            searchTerm,
            iPageLimit,
            pageNumber * iPageLimit
        )

//    fun getStoriesListResponse(searchTerm: String, pageNumber: Int): LiveData<Story> {
//        mCompositeDisposable?.add(storiesListApiService.getTagStoriesList(searchTerm, OxygenConstants.PAGE_LIMIT, pageNumber * OxygenConstants.PAGE_LIMIT)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(object : ResourceSubscriber<TagListResponse>() {
//                    override fun onComplete() {
//                        Log.d("Rakshith", " tag list api call completed..")
//                    }
//
//                    override fun onNext(tagListResponse: TagListResponse?) {
//                        for (index in 0 until tagListResponse?.stories?.size as Int)
//                            mStoriesListData.value = tagListResponse.stories?.get(index)
//                    }
//
//                    override fun onError(t: Throwable?) {
//                        Log.d("Rakshith", " tag list api call failed error is ${t?.message}")
//                    }
//                }))
//        return mStoriesListData
//    }
}
