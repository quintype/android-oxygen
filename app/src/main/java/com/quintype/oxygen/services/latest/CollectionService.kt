package com.quintype.oxygen.services.latest

import android.app.Application
import com.quintype.oxygen.services.RetrofitCacheApiClient
import com.quintype.prothomalo.latest.ICollectionApiService

class CollectionService private constructor(application: Application) {

    private val mCollectionService: ICollectionApiService =
        RetrofitCacheApiClient(application).getRetrofitApiClient().create(ICollectionApiService::class.java)

    companion object {

        private var mCollectionServiceInstance: CollectionService? = null

        @Synchronized
        fun getInstance(application: Application): CollectionService {
            if (mCollectionServiceInstance == null) {
                mCollectionServiceInstance = CollectionService(application)
            }

            return mCollectionServiceInstance as CollectionService
        }
    }

    fun getCollectionResponse(mCollectionSlug: String, iLimit: Int, iOffset: Int, storyField: String) =
        mCollectionService.getCollectionApiService(mCollectionSlug, iLimit, iOffset, storyField)
}