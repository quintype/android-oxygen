package com.quintype.oxygen.services

import com.quintype.oxygen.models.StoryPayWallPingBackRequest
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
    fun getUserStoryAccess(mUserVuid: String?, mStoryId: String, platform: String, deviceID: String) =
        mUserAccessServiceApi.getUserStoryAccessInfo(mUserVuid, mStoryId, platform, deviceID)

    /**
     * function to post the read premium story ID's as Array, based ov user Vuid
     */
    fun postReadStoryIdArray(
        mUserVuid: String?,
        storyIds: StoryPayWallPingBackRequest,
        platform: String,
        deviceID: String
    ) =
        mUserAccessServiceApi.postReadStoryIdArray(mUserVuid, storyIds, platform, deviceID)

    /**
     * function to post the read premium story ID, based ov user Vuid
     */
    fun postReadStoryId(mUserVuid: String?, storyId: String, platform: String, deviceID: String) =
        mUserAccessServiceApi.postReadStoryId(mUserVuid, storyId, platform, deviceID)

    /**
     * function to get user collection access based on the Vuid
     */
    fun getUserCollectionAccess(mUserVuid: String?, mCollectionId: String, platform: String, deviceID: String) =
        mUserAccessServiceApi.getUserCollectionAccessInfo(mUserVuid, mCollectionId, platform, deviceID)

    /**
     * API for getting JWT key for meType
     */
    fun getJWTKeyForUser(mVuid: String?) = mUserAccessServiceApi.getJWTKeyForUser(mVuid)
}