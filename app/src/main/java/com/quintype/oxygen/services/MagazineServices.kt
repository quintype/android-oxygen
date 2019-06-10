package com.quintype.oxygen.services

import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.OxygenConstants.Companion.MAGAZINE_ID
import com.quintype.oxygen.models.collection.CollectionResponse
import com.quintype.oxygen.models.entities.EntityInfoFromConfig
import com.quintype.oxygen.models.entities.MagazineResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MagazineServices {

    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("/mobile-config/magazines.json")
    fun getMagazineEntitiesResponseFromConfig(): Flowable<EntityInfoFromConfig>

    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("/api/v1/entities")
    fun getMagazineEntitiesResponse(
        @Query(OxygenConstants.QUERY_PARAM_KEY_TYPE) entityType: String,
        @Query(OxygenConstants.QUERY_PARAM_KEY_LIMIT) limit: Int,
        @Query(OxygenConstants.QUERY_PARAM_KEY_OFFSET) offset: Int
    ): Flowable<MagazineResponse>

    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("/api/v1/entity/{$MAGAZINE_ID}/collections?sort-by=collection-date&order=desc")
    fun getMagazineEntity(
        @Path(MAGAZINE_ID) magazineId: String,
        @Query(OxygenConstants.QUERY_PARAM_KEY_LIMIT) limit: Int,
        @Query(OxygenConstants.QUERY_PARAM_KEY_OFFSET) offset: Int
    ): Flowable<CollectionResponse>

    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("/api/v1/entity/{$MAGAZINE_ID}/collections?")
    fun getMagazineEntity(
        @Path(MAGAZINE_ID) entityId: String,
        @Query(OxygenConstants.QUERY_PARAM_COLLECTION_DATE_AFTER) dateAfter: Long,
        @Query(OxygenConstants.QUERY_PARAM_COLLECTION_DATE_BEFORE) dateBefore: Long
    ): Flowable<CollectionResponse>

    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("/mobile-data.json")
    fun getMobileCollectionForMagazine(
        @Query(OxygenConstants.QUERY_PARAM_KEY_STORY_SLUG) slug: String,
        @Query(OxygenConstants.QUERY_PARAM_KEY_TYPE) type: String,
        @Query(OxygenConstants.QUERY_PARAM_KEY_ITEM_TYPE) itemType: String,
        @Query(OxygenConstants.QUERY_PARAM_KEY_FIELDS) storyFields: String
    ): Flowable<com.quintype.oxygen.models.collection.MagazineResponse>

}