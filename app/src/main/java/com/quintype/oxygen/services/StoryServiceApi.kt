package com.quintype.oxygen.services

import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.models.story.SlugStory
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface StoryServiceApi {
    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("/api/v1/stories-by-slug")
    fun getStoryDetailBySlug(@Query(OxygenConstants.QUERY_PARAM_KEY_STORY_SLUG) storySlug: String): Single<SlugStory>
}
