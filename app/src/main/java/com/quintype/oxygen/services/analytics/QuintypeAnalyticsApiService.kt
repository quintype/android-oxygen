package com.quintype.oxygen.services.analytics

import com.quintype.oxygen.analytics.models.PostableEvent
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface QuintypeAnalyticsApiService {
    /**
     * Notify an event
     *
     * @param event event data
     * @return observable for response
     */
    @Headers(
        ACCEPT_APPLICATION_JSON_CHARSET_UTF_8,
        CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8
    )
    @POST("/api/event")
    fun notifyEvent(@Body event: PostableEvent): Flowable<Response<Void>>

    companion object {
        const val ACCEPT_APPLICATION_JSON_CHARSET_UTF_8 = "Accept: application/json; charset=utf-8"
        const val CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8 = "Content-Type: application/json; charset=utf-8"
    }
}
