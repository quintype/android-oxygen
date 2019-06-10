package com.quintype.oxygen.services

import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.models.TagListResponse
import com.quintype.oxygen.models.search.SearchStoryList
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created TemplateCollectionWithRx by rakshith on 10/1/18.
 */

interface StoriesListApiService {

    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("api/v1/stories")
    fun getTagStoriesList(
        @Query(OxygenConstants.QUERY_PARAM_KEY_TAG_NAME) tagName: String,
        @Query(OxygenConstants.QUERY_PARAM_KEY_LIMIT) limit: Int,
        @Query(OxygenConstants.QUERY_PARAM_KEY_SEARCH_FIELDS) storyFields: String,
        @Query(OxygenConstants.QUERY_PARAM_KEY_OFFSET) offset: Int
    ): Flowable<TagListResponse>

    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("api/v1/search")
    fun getSearchStoriesList(
        @Query(OxygenConstants.QUERY_PARAM_KEY_SEARCH_TERM) tagName: String,
        @Query(OxygenConstants.QUERY_PARAM_KEY_LIMIT) limit: Int,
        @Query(OxygenConstants.QUERY_PARAM_KEY_SEARCH_FIELDS) storyFields: String,
        @Query(OxygenConstants.QUERY_PARAM_KEY_OFFSET) offset: Int
    ): Flowable<SearchStoryList>

}