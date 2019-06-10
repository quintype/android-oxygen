package com.quintype.oxygen.services

import com.vikatanapp.vikatan.services.LoginService
import com.vikatanapp.vikatan.ui.main.models.StoryPayWallPingBackRequest
import io.reactivex.disposables.CompositeDisposable

class UserAccessService {
    companion object {
        private var mUserAccessService: UserAccessService? = null
        var mCompositeDisposable = CompositeDisposable()
        var mUserAccessServiceApi = RetrofitApiClient.getRetrofitApiClient().create(UserAccessServiceApi::class.java)!!

        @Synchronized
        fun getInstance(): UserAccessService {
            if (null == mUserAccessService)
                mUserAccessService = UserAccessService()

            return mUserAccessService as UserAccessService
        }
    }

    /**
     * function to get user story access based on the Vuid
     */
    fun getUserStoryAccess(mUserVuid: String?, mStoryId: String) = mUserAccessServiceApi.getUserStoryAccessInfo(mUserVuid, mStoryId)

    /**
     * function to post the read premium story ID's as Array, based ov user Vuid
     */
    fun postReadStoryIdArray(mUserVuid: String?, storyIds: StoryPayWallPingBackRequest) = mUserAccessServiceApi.postReadStoryIdArray(mUserVuid, storyIds)

    /**
     * function to post the read premium story ID, based ov user Vuid
     */
    fun postReadStoryId(mUserVuid: String?, storyId: String) = mUserAccessServiceApi.postReadStoryId(mUserVuid, storyId)

    /**
     * function to get user collection access based on the Vuid
     */
    fun getUserCollectionAccess(mUserVuid: String?, mCollectionId: String) = mUserAccessServiceApi.getUserCollectionAccessInfo(mUserVuid, mCollectionId)

    /**
     * API for getting JWT key for meType
     */
    fun getJWTKeyForUser(mVuid: String?) = mUserAccessServiceApi.getJWTKeyForUser(mVuid)
}