package com.quintype.oxygen.services

import com.quintype.oxygen.OxygenConstants

class StoryDetailService private constructor() {

    companion object {
        private var storyServiceApi: StoryServiceApi = RetrofitApiClient.getRetrofitApiClient(OxygenConstants.BASE_URL).create(StoryServiceApi::class.java)
        private var storyDetailServiceInstance: StoryDetailService? = null

        @Synchronized
        fun getInstance(): StoryDetailService {
            if (storyDetailServiceInstance == null) {
                storyDetailServiceInstance = StoryDetailService()
            }

            return storyDetailServiceInstance!!
        }
    }

    @Synchronized
    fun getStoryDetailResponse(mStorySlug: String) = storyServiceApi.getStoryDetailBySlug(mStorySlug)
}
