package com.quintype.oxygen.services

import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.models.BulkCollectionRequest
import com.quintype.oxygen.models.collection.CollectionResponse
import io.reactivex.Flowable
import retrofit2.http.*

/**
 * Created TemplateCollectionWithRx by rakshith on 7/23/18.
 */

interface CollectionApiService {
    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("/api/v1/collections/{" + OxygenConstants.COLLECTION_SLUG + "}")
    fun getCollectionApiService(
        @Path(OxygenConstants.COLLECTION_SLUG) mCollectionName: String,
        @Query(OxygenConstants.QUERY_PARAM_KEY_LIMIT) limit: Int,
        @Query(OxygenConstants.QUERY_PARAM_KEY_OFFSET) offset: Int,
        @Query(OxygenConstants.QUERY_PARAM_KEY_FIELDS) storyFields: String
    ): Flowable<CollectionResponse>

    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("/api/v1/collections/{" + OxygenConstants.COLLECTION_SLUG + "}")
    fun getCollectionOnlyStoriesApiService(
        @Path(OxygenConstants.COLLECTION_SLUG) mCollectionName: String,
        @Query(OxygenConstants.QUERY_PARAM_KEY_LIMIT) limit: Int,
        @Query(OxygenConstants.QUERY_PARAM_KEY_OFFSET) offset: Int,
        @Query(OxygenConstants.QUERY_PARAM_KEY_ITEM_TYPE) itemType: String,
        @Query(OxygenConstants.QUERY_PARAM_KEY_FIELDS) storyFields: String
    ): Flowable<CollectionResponse>

    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @POST("/api/v1/bulk")
    fun getCollectionBulk(@QueryMap bulkCollectionRequest: BulkCollectionRequest): Flowable<CollectionResponse>
}