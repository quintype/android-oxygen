package com.quintype.oxygen.services.latest

import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.models.latest.config.ConfigModel
import com.quintype.oxygen.models.config.menugroups.MenuGroup
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface IMobileConfigService {
    @GET("/v1/mobile/config")
    fun getMobileConfig(): Flowable<ConfigModel>


    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("/v1/mobile/menu-groups")
    fun getMenuGroupsResponse(
        @Query("title") mMenuGroupSlug: String = "mobile-menu"
    ): Flowable<MenuGroup>
}