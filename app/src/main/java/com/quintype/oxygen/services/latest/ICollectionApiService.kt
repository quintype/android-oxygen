package com.quintype.prothomalo.latest

import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.models.latest.home.CollectionResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ICollectionApiService {
    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("/v1/mobile/collection")
    fun getCollectionApiService(
        @Query(OxygenConstants.QUERY_PARAM_KEY_STORY_SLUG) mCollectionSlug: String,
        @Query(OxygenConstants.QUERY_PARAM_KEY_LIMIT) limit: Int,
        @Query(OxygenConstants.QUERY_PARAM_KEY_OFFSET) offset: Int,
        @Query(OxygenConstants.QUERY_PARAM_KEY_FIELDS) storyFields: String
    ): Flowable<CollectionResponse>


}