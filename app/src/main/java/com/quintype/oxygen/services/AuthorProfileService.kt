package com.quintype.oxygen.services


import com.quintype.oxygen.AUTHOR_STORY_FIELDS
import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.TYPE_STORY

class AuthorProfileService private constructor() {
    companion object {
        private var mAuthorProfileApiService: AuthorProfileApiService? = null

        private var mAuthorProfileService: AuthorProfileService? = null

        private var AUTHOR_STORY_FIELD: String = AUTHOR_STORY_FIELDS

        @Synchronized
        fun getInstance(mAuthorStoryFields: String = ""): AuthorProfileService {
            if (null == mAuthorProfileService) {
                mAuthorProfileService = AuthorProfileService()
                mAuthorProfileApiService = RetrofitApiClient.getRetrofitApiClient(OxygenConstants.BASE_URL).create(AuthorProfileApiService::class.java)
                if (mAuthorStoryFields.isNotEmpty()) {
                    AUTHOR_STORY_FIELD = mAuthorStoryFields
                }
            }

            return mAuthorProfileService!!
        }
    }

    fun getAuthorProfileStories(mAuthorId: String, pageNumber: Int, iPageLimit: Int) =
        mAuthorProfileApiService?.getStoriesByAuthorApiService(mAuthorId, iPageLimit, pageNumber, TYPE_STORY, AUTHOR_STORY_FIELD)

}