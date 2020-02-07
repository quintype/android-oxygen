package com.quintype.oxygen.services.mycomments

import com.quintype.oxygen.OxygenConstants.Companion.QUERY_PARAM_HEADER_ORIGIN
import com.quintype.oxygen.OxygenConstants.Companion.QUERY_PARAM_KEY_ACCOUNT_ID
import com.quintype.oxygen.OxygenConstants.Companion.QUERY_PARAM_KEY_JWT
import com.quintype.oxygen.OxygenConstants.Companion.QUERY_PARAM_KEY_PAGE
import com.quintype.oxygen.OxygenConstants.Companion.QUERY_PARAM_KEY_PER_PAGE
import com.quintype.oxygen.models.mycomments.MyComments
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MyCommentsServiceAPI {

        @GET("/api/v1/activity/comments")
//    @GET("/mycomments")
    fun getMyComments(
        @Header(QUERY_PARAM_HEADER_ORIGIN) origin: String?,
        @Query(QUERY_PARAM_KEY_ACCOUNT_ID) accountId: String?,
        @Query(QUERY_PARAM_KEY_JWT) jwt: String?,
        @Query(QUERY_PARAM_KEY_PAGE) mPageNumber: Int,
        @Query(QUERY_PARAM_KEY_PER_PAGE) mPerPage: Int
    ): Flowable<MyComments>
}