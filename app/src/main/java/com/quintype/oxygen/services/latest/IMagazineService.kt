package com.quintype.oxygen.services.latest

import com.quintype.oxygen.KEY_PARAM_ID
import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.models.latest.home.magazines.NewMagazineResponseModel
import io.reactivex.Flowable
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


}
