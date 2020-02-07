package com.quintype.oxygen.services.latest

import com.quintype.oxygen.KEY_PARAM_ID
import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.QUERY_PARAM_EXTERNAL_IDS
import com.quintype.oxygen.models.latest.home.magazines.NewMagazineResponseModel
import com.quintype.oxygen.models.mapping.BookmarkMappingModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IMagazineService {

    @GET("/v1/mobile/magazines.json")
    fun getMagazines(
        @Query(OxygenConstants.QUERY_PARAM_KEY_LIMIT) limit: Int,
        @Query(OxygenConstants.QUERY_PARAM_KEY_OFFSET) offset: Int
    ): Flowable<NewMagazineResponseModel>

    @GET("/v1/mobile/magazines-by-id")
    fun getMagazinesById(
        @Query(KEY_PARAM_ID) entityId: Int,
        @Query(OxygenConstants.QUERY_PARAM_KEY_LIMIT) limit: Int,
        @Query(OxygenConstants.QUERY_PARAM_KEY_OFFSET) offset: Int
    ): Flowable<NewMagazineResponseModel>


    @GET("/v1/mobile/magazines-by-id")
    fun getMagazinesById(
        @Query(KEY_PARAM_ID) entityId: Int,
        @Query(OxygenConstants.QUERY_PARAM_COLLECTION_DATE_AFTER) dateAfter: Long,
        @Query(OxygenConstants.QUERY_PARAM_COLLECTION_DATE_BEFORE) dateBefore: Long
    ): Flowable<NewMagazineResponseModel>

    /**
     * API for getting external id to bookmark mapping
     */
    @GET("/v1/mobile/external-id")
    fun getStoryByExternalId(
        @Query(QUERY_PARAM_EXTERNAL_IDS) externalIds: String,
        @Query(OxygenConstants.QUERY_PARAM_KEY_SEARCH_FIELDS) mExternalStoryFields: String
    ): Single<BookmarkMappingModel>


}
