package com.quintype.oxygen.services

import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.models.collection.CollectionResponse
import com.vikatanapp.vikatan.utils.BREAKING_NEWS_SLUG
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface BreakingNewsApi {
    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("/api/v1/collections/$BREAKING_NEWS_SLUG")
    fun getBreakingNews(): Single<CollectionResponse>
}