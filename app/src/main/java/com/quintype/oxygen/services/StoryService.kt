package com.quintype.oxygen.services

import android.arch.lifecycle.MutableLiveData
import com.quintype.oxygen.models.story.SlugStory
import io.reactivex.disposables.CompositeDisposable

class StoryService {
    companion object {
        var storyServiceApi: StoryServiceApi =
            RetrofitApiClient.getRetrofitApiClient().create(StoryServiceApi::class.java)

        private var storyServiceInstance: StoryService? = null
        var mCompositeDisposable: CompositeDisposable? = null
        var storyData: MutableLiveData<SlugStory> = MutableLiveData()

        @Synchronized
        fun getInstance(compositeDisposable: CompositeDisposable): StoryService {
            if (storyServiceInstance == null)
                storyServiceInstance = StoryService()

            mCompositeDisposable = CompositeDisposable()

            return storyServiceInstance as StoryService
        }
    }

    fun getStoryDetailResponse(mStorySlug: String) = storyServiceApi.getStoryDetailBySlug(mStorySlug)
}
