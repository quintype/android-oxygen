package com.quintype.androidoxygen.services

import io.reactivex.Flowable
import com.quintype.androidoxygen.models.config.PublisherConfig
import com.quintype.androidoxygen.OxygenConstants
import retrofit2.http.GET
import retrofit2.http.Headers

interface PublisherConfigServiceApi {

    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("/api/v1/config")
    fun getPublisherConfig(): Flowable<PublisherConfig>
}
