package com.quintype.oxygen.services.bookmark

import com.quintype.oxygen.*
import com.quintype.oxygen.models.bookmark.AllBookmarksResponse
import com.quintype.oxygen.models.bookmark.BookmarkResponse
import com.quintype.oxygen.models.bookmark.BulkBookmarksRequest
import com.quintype.oxygen.models.bookmark.CreateBookmarkRequest
import io.reactivex.Single
import retrofit2.http.*

interface IBookmarkService {

    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @POST("/api/v1/accounts/{$ACCOUNT_ID}/pages/{$PAGE_ID}/bookmark")
    fun createBookmark(
        @Header(ORIGIN) headerToken: String?,
        @Path(ACCOUNT_ID) accId: String, @Path(PAGE_ID, encoded = true) pageId: String,
        @Body bookmarkRequest: CreateBookmarkRequest, @Query(JWT, encoded = true) jwt: String
    ): Single<BookmarkResponse>


    @DELETE("/api/v1/accounts/{$ACCOUNT_ID}/pages/{$PAGE_ID}/bookmark")
    fun deleteBookmark(
        @Header(ORIGIN) headerToken: String?,
        @Path(ACCOUNT_ID) accId: String, @Path(PAGE_ID, encoded = true) pageId: String, @Query(
            JWT,
            encoded = true
        ) jwt: String
    ): Single<BookmarkResponse>


    @GET("/api/v1/accounts/{$ACCOUNT_ID}/bookmarks")
    fun fetchAllBookmarks(
        @Header(ORIGIN) headerToken: String?,
        @Path(ACCOUNT_ID) accId: String, @Query(JWT, encoded = true) jwt: String, @Query(
            PAGE
        ) page: Int
    ): Single<AllBookmarksResponse>


    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @POST("/api/v1/accounts/{$ACCOUNT_ID}/bookmarks/bulk_upload")
    fun uploadBookmarkedStories(
        @Header(ORIGIN) headerToken: String?,
        @Path(ACCOUNT_ID) accId: String,
        @Body bulkBookmarksRequest: BulkBookmarksRequest, @Query(JWT, encoded = true) jwt: String
    ): Single<Any>
}