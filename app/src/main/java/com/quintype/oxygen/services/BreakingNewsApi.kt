package com.quintype.oxygen.services

import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.models.latest.home.CollectionResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BreakingNewsApi {
    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("/v1/mobile/collection")
    fun getBreakingNews(
        @Query(OxygenConstants.QUERY_PARAM_KEY_STORY_SLUG) mCollectionSlug: String,
        @Query(OxygenConstants.QUERY_PARAM_KEY_OFFSET) offset: Int,
        @Query(OxygenConstants.QUERY_PARAM_KEY_LIMIT) limit: Int,
        @Query(OxygenConstants.QUERY_PARAM_KEY_FIELDS) storyFields: String
    ): Single<CollectionResponse>

}