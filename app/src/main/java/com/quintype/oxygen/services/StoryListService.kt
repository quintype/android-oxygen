package com.quintype.oxygen.services

import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.SEARCH_STORY_FIELDS
import com.quintype.oxygen.STORY_FIELDS
import com.quintype.oxygen.TYPE_STORY

class StoryListService private constructor() {

    companion object {
        private var storyListService: StoryListService? = null

        private var storiesListApiService: StoriesListApiService? = null

        @Synchronized
        fun getInstance(): StoryListService? {
            if (storyListService == null) {
                storyListService = StoryListService()
                storiesListApiService = RetrofitApiClient.getRetrofitApiClient(OxygenConstants.BASE_URL)
                    .create(
                        StoriesListApiService::class.java
                    )
            }

            return storyListService
        }
    }

    @Synchronized
    fun getTagListResponse(searchTerm: String, pageNumber: Int, iPageLimit: Int) =
        storiesListApiService?.getTagStoriesList(
            searchTerm,
            iPageLimit,
            STORY_FIELDS,
            pageNumber * iPageLimit
        )

    @Synchronized
    fun getSearchStoryListResponse(searchTerm: String, pageNumber: Int, iPageLimit: Int) =
        storiesListApiService?.getSearchStoriesList(
            searchTerm,
            iPageLimit,
            pageNumber * iPageLimit,
            TYPE_STORY,
            SEARCH_STORY_FIELDS
        )
}
