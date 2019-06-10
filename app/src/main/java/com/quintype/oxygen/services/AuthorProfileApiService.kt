package com.quintype.oxygen.services

import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.models.collection.CollectionResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthorProfileApiService {
    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("/api/v1/authors/{" + OxygenConstants.AUTHOR_ID + "}/collection")
    fun getStoriesByAuthorApiService(
        @Path(OxygenConstants.AUTHOR_ID) mAuthorId: String,
        @Query(OxygenConstants.QUERY_PARAM_KEY_LIMIT) limit: Int,
        @Query(OxygenConstants.QUERY_PARAM_KEY_OFFSET) offset: Int,
        @Query(OxygenConstants.QUERY_PARAM_KEY_ITEM_TYPE) itemType: String,
        @Query(OxygenConstants.QUERY_PARAM_KEY_FIELDS) storyFields: String
    ): Flowable<CollectionResponse>
}