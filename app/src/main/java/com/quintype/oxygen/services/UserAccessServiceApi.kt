package com.quintype.oxygen.services

import com.quintype.oxygen.COLLECTION_ID
import com.quintype.oxygen.STORY_ID
import com.quintype.oxygen.X_VIKATAN_AUTH
import com.quintype.oxygen.models.UserAccessModel
import com.quintype.oxygen.models.UserJWTModel
import com.vikatanapp.vikatan.ui.main.models.StoryPayWallPingBackRequest
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface UserAccessServiceApi {
    /**
     * API to get the story access info based ov user Vuid
     */
    @GET("/api/vikatan/v1/stories/{" + STORY_ID + "}/access")
    fun getUserStoryAccessInfo(
        @Header(X_VIKATAN_AUTH) headerToken: String?,
        @Path(STORY_ID) mStoryId: String
    ): Single<UserAccessModel>

    /**
     * API to post the read premium story ID's as Array, based ov user Vuid
     */
    @POST("api/vikatan/v1/stories/pingback")
    fun postReadStoryIdArray(
        @Header(X_VIKATAN_AUTH) headerToken: String?,
        @Body storyIds: StoryPayWallPingBackRequest
    ): Completable

    /**
     * API to post the read premium story ID, based ov user Vuid
     */
    @POST("/api/vikatan/v1/stories/{" + STORY_ID + "}/pingback")
    fun postReadStoryId(
        @Header(X_VIKATAN_AUTH) headerToken: String?,
        @Path(STORY_ID) mStoryId: String
    ): Completable

    /**
     * API to get the collection access info based Vuid
     */
    @GET("/api/vikatan/v1/collections/{" + COLLECTION_ID + "}/access")
    fun getUserCollectionAccessInfo(
        @Header(X_VIKATAN_AUTH) headerToken: String?,
        @Path(COLLECTION_ID) mCollectionId: String
    ): Single<UserAccessModel>

    /**
     * API to get the JWT key for meType
     */
    @GET("/api/vikatan/v1/users/me")
    fun getJWTKeyForUser(
        @Header(X_VIKATAN_AUTH) headerToken: String?
    ): Single<UserJWTModel>
}