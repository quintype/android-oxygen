package com.vikatanapp.vikatan.services

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.quintype.oxygen.ErrorHandler
import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.TYPE_MAGAZINE
import com.quintype.oxygen.models.collection.CollectionResponse
import com.quintype.oxygen.models.collection.MagazineResponse
import com.quintype.oxygen.services.MagazineServices
import com.quintype.oxygen.services.RetrofitApiClient
import com.quintype.oxygen.utils.widgets.logdExt
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MagazineService {
    private var mCollectionResponse = MutableLiveData<MagazineResponse>()
    private var mEntityReponse = MutableLiveData<CollectionResponse>()
    @SuppressLint("CheckResult")
    fun getMagazinePreviewResponse(
        errorHandler: ErrorHandler,
        entityId: String,
        afterDateMillis: String,
        beforeDateMillis: String
    ): LiveData<MagazineResponse> {
        val magazineService: MagazineServices =
            RetrofitApiClient.getRetrofitApiClient().create(MagazineServices::class.java)
        logdExt("first Iteration for magazine Collection Slug - $entityId")
        magazineService.getMagazineEntity(entityId, afterDateMillis.toLong(), beforeDateMillis.toLong())
            .flatMap<MagazineResponse> {
                if (it.slug == null) {
                    throw Exception("Slug can't be null.")
                }
                val slug = it.collections?.get(0)?.slug
                return@flatMap magazineService.getMobileCollectionForMagazine(
                    slug!!,
                    TYPE_MAGAZINE,
                    OxygenConstants.TYPE_STORY,
                    OxygenConstants.MAGAZINE_STORY_FIELDS
                )
            }
            .subscribeOn(Schedulers.io())
            .retry(3)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ mMagazineResponse ->
                logdExt("Collection Response from magazine API :${mMagazineResponse.mCollectionResponse?.slug}, Items Size :${mMagazineResponse.mCollectionResponse?.items?.size}")
                mCollectionResponse.value = mMagazineResponse
                errorHandler.onAPISuccess()
            }, {
                logdExt(it.localizedMessage)
                errorHandler.onAPIFailure()
            })

        return mCollectionResponse
    }

    @SuppressLint("CheckResult")
    fun getMagazinePreviewResponse(errorHandler: ErrorHandler, slug: String): LiveData<MagazineResponse> {

        val magazineService: MagazineServices =
            RetrofitApiClient.getRetrofitApiClient().create(MagazineServices::class.java)

        magazineService.getMobileCollectionForMagazine(
            slug,
            TYPE_MAGAZINE,
            OxygenConstants.TYPE_STORY,
            OxygenConstants.MAGAZINE_STORY_FIELDS
        )
            .subscribeOn(Schedulers.io())
            .retry(3)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ mMagazineResponse ->
                logdExt("Collection Response from magazine API :${mMagazineResponse.mCollectionResponse?.slug}, Items Size :${mMagazineResponse.mCollectionResponse?.items?.size}")
                mCollectionResponse.value = mMagazineResponse
                errorHandler.onAPISuccess()
            }, {
                logdExt(it.localizedMessage)
                errorHandler.onAPIFailure()
            })

        return mCollectionResponse
    }


    @SuppressLint("CheckResult")
    fun getMagazineArchiveList(
        errorHandler: ErrorHandler,
        entityId: String,
        afterDateMillis: String,
        beforeDateMillis: String
    ): LiveData<CollectionResponse> {

        val magazineService: MagazineServices =
            RetrofitApiClient.getRetrofitApiClient().create(MagazineServices::class.java)

        logdExt("first Iteration for magazine Collection Slug - $entityId")
        magazineService.getMagazineEntity(entityId, afterDateMillis.toLong(), beforeDateMillis.toLong())
            .subscribeOn(Schedulers.io())
            .retry(3)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ mMagazineResponse ->
                mEntityReponse.value = mMagazineResponse
                errorHandler.onAPISuccess()
            }, {
                logdExt(it.localizedMessage)
                errorHandler.onAPIFailure()
            })
        return mEntityReponse
    }

    /**
     * API for getting external id to bookmark mapping
     */
    fun getStoryByExternalId(externalIds: String, mExternalStoryFields: String) {
        val magazineService: MagazineServices =
            RetrofitApiClient.getRetrofitApiClient().create(MagazineServices::class.java)
        magazineService.getStoryByExternalId(externalIds, mExternalStoryFields)
    }
}