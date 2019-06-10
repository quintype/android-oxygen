package com.quintype.oxygen.services

import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.models.config.PublisherConfig
import com.quintype.oxygen.models.config.menugroups.MenuGroups
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Headers

interface PublisherConfigServiceApi {

    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("/api/v1/config")
    fun getPublisherConfig(): Flowable<PublisherConfig>

    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("/api/v1/menu-groups")
    fun getMenuGroupsResponse(): Flowable<MenuGroups>
}
