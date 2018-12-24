package com.quintype.androidoxygen.services

import io.reactivex.disposables.CompositeDisposable

class BreakingNewsService {
    companion object {
        var breakingNewsApi: BreakingNewsApi =
            RetrofitApiClient.getRetrofitApiClient().create(BreakingNewsApi::class.java)

        var breakingNewsServiceInstance: BreakingNewsService? = null
        var mCompositeDisposable: CompositeDisposable? = null

        @Synchronized
        fun getInstance(compositeDisposable: CompositeDisposable): BreakingNewsService {
            if (breakingNewsServiceInstance == null)
                breakingNewsServiceInstance = BreakingNewsService()

            if (true)
                mCompositeDisposable = CompositeDisposable()

            return breakingNewsServiceInstance as BreakingNewsService
        }
    }

    fun getBreakingNews() = breakingNewsApi.getBreakingNews()
}