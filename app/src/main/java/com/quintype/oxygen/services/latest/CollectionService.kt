package com.quintype.oxygen.services.latest

import com.quintype.oxygen.OxygenApplication
import com.quintype.oxygen.services.RetrofitCacheApiClient
import com.quintype.prothomalo.latest.ICollectionApiService

class CollectionService private constructor() {
    companion object {
        private val mCollectionService: ICollectionApiService =
            RetrofitCacheApiClient(OxygenApplication.mInstance!!).getRetrofitApiClient().create(ICollectionApiService::class.java)

        private var mCollectionServiceInstance: CollectionService? = null

        @Synchronized
        fun getInstance(): CollectionService {
            if (mCollectionServiceInstance == null) {
                mCollectionServiceInstance = CollectionService()
            }

            return mCollectionServiceInstance as CollectionService
        }
    }

    fun getCollectionResponse(mCollectionSlug: String, iLimit: Int, iOffset: Int, storyField: String) =
        mCollectionService.getCollectionApiService(mCollectionSlug, iLimit, iOffset, storyField)
}