package com.quintype.androidoxygen.services

import com.quintype.androidoxygen.OxygenConstants
import com.quintype.androidoxygen.models.collection.BreakingNewsModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface BreakingNewsApi {
    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("/api/v1/breaking-news")
    fun getBreakingNews(): Single<BreakingNewsModel>
}