package com.quintype.oxygen.services

import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.STORY_FIELDS
import com.quintype.prothomalo.utils.BREAKING_NEWS_SLUG

class BreakingNewsService private constructor() {
    companion object {
        private var breakingNewsApi: BreakingNewsApi = RetrofitApiClient.getRetrofitApiClient(OxygenConstants.BASE_URL).create(BreakingNewsApi::class.java)

        private var breakingNewsServiceInstance: BreakingNewsService? = null
        private var BREAKING_NEWS_STORY_FIELD = STORY_FIELDS

        @Synchronized
        fun getInstance(breakingNewsStoryFields: String): BreakingNewsService {
            if (breakingNewsServiceInstance == null) {
                breakingNewsServiceInstance = BreakingNewsService()

                if (breakingNewsStoryFields.isNotEmpty()) BREAKING_NEWS_STORY_FIELD = breakingNewsStoryFields
            }
            return breakingNewsServiceInstance as BreakingNewsService
        }
    }

    fun getBreakingNews(defaultOffset: Int, breakingNewsLimit: Int) =
        breakingNewsApi.getBreakingNews(BREAKING_NEWS_SLUG, defaultOffset, breakingNewsLimit, BREAKING_NEWS_STORY_FIELD)
}