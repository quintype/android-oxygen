package com.quintype.oxygen.services

import com.quintype.oxygen.OxygenConstants
import io.reactivex.disposables.CompositeDisposable

class StoriesListService {
    companion object {
        private var storiesListService = StoriesListService()
        var storiesListApiService: StoriesListApiService = RetrofitApiClient.getRetrofitApiClient()
            .create(
            StoriesListApiService::class.java
        )
        var mCompositeDisposable: CompositeDisposable? = null

        @Synchronized
        fun getInstance(compositeDisposable: CompositeDisposable): StoriesListService {
            mCompositeDisposable = compositeDisposable

            return storiesListService
        }
    }

    fun getStoriesListResponse(searchTerm: String, pageNumber: Int, iPageLimit: Int) =
        storiesListApiService.getTagStoriesList(
            searchTerm,
            iPageLimit,
            OxygenConstants.STORY_FIELDS,
            pageNumber * iPageLimit
        )

    fun getSearchStoryListResponse(searchTerm: String, pageNumber: Int, iPageLimit: Int) =
        storiesListApiService.getSearchStoriesList(
            searchTerm,
            iPageLimit,
            OxygenConstants.SEARCH_STORY_FIELDS,
            pageNumber * iPageLimit
        )
}
