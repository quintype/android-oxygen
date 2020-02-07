package com.quintype.oxygen.analytics

import android.app.Application
import android.content.Context
import android.text.TextUtils
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.quintype.oxygen.TLSSocketFactory
import com.quintype.oxygen.analytics.models.*
import com.quintype.oxygen.models.config.PublisherConfig
import com.quintype.oxygen.models.sections.Section
import com.quintype.oxygen.models.story.Card
import com.quintype.oxygen.models.story.Story
import com.quintype.oxygen.models.story.StoryElement
import com.quintype.oxygen.utils.widgets.logdExt
import com.quintype.oxygen.utils.widgets.logeExt
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class QuintypeAnalyticsService(mContext: Context, publisherConfig: PublisherConfig) {

    private var mSessionEventId: String? = null
    internal lateinit var app: Application
    private var mStoryVisitPageViewEventId: String? = null
    private var mIsSessionEventNotified: Boolean = false
    private var mSessionIdLastUsed: Long = 0
    private var mMaxSessionInactiveDuration: Long = 90 * 60 * 1000 //90 minutes
    private var appName: String
    private var installationDetails: InstallationDetails? = null
    private var mPublisherConfig = publisherConfig
    private var mPublisherID: Long? = null
    internal var gson: Gson
    private var mAnalyticsApiService: QuintypeAnalyticsApiService


    companion object {
        var instance: QuintypeAnalyticsService? = null

        fun initialize(context: Context, publisherConfig: PublisherConfig): QuintypeAnalyticsService {
            if (null == instance)
                instance =
                    QuintypeAnalyticsService(context, publisherConfig)
            return instance as QuintypeAnalyticsService
        }
    }

    init {
        generateSessionId()
        mPublisherID = mPublisherConfig.publisherId
        appName = mPublisherConfig.publisherName + " Android"
        installationDetails = InstallationDetails.newInstance(mContext)
        gson = Gson()
        mAnalyticsApiService = getShrubberyRetrofitApiClient()
    }

    private fun getShrubberyRetrofitApiClient(): QuintypeAnalyticsApiService {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val builder: OkHttpClient.Builder = OkHttpClient().newBuilder()
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .addInterceptor(interceptor)
            .cache(null)
            .sslSocketFactory(TLSSocketFactory())
            .hostnameVerifier { hostname, session -> true }
            .connectTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)

        val shrubberyEndPoint: String = mPublisherConfig.shrubberyHost!!

        val retrofit: Retrofit = Retrofit
            .Builder()
            .baseUrl(shrubberyEndPoint)
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(QuintypeAnalyticsApiService::class.java)
    }

    private fun generateSessionId() {
        mSessionEventId = UUID.randomUUID().toString()
        mSessionIdLastUsed = System.currentTimeMillis()
        mIsSessionEventNotified = false
        logdExt("Generating a new session event id $mSessionEventId")
    }

    private fun notifySessionEvent() {
        val sessionEvent = SessionEvent()
        sessionEvent.mDeviceTrackerId = installationDetails?.deviceId
        sessionEvent.mId = mSessionEventId
        sessionEvent.mMemberId = null
        try {
            sessionEvent.mPublisherId = mPublisherID
        } catch (nfe: NumberFormatException) {
            logeExt("$nfe Publisher id is not a long value")
        }

        sessionEvent.mUserAgent = getUserAgent()
        sessionEvent.mOS = installationDetails?.osVersionCode.toString()
        sessionEvent.mDeviceModel = installationDetails?.deviceName
        sessionEvent.mDeviceMaker = installationDetails?.deviceManufacturer
        postEvent(sessionEvent)
    }

    private fun postEvent(event: Event) {
        validateInactivityOfSession()
        if (!mIsSessionEventNotified) {
            logdExt("Session event not created, will be creating session event first")

            mIsSessionEventNotified = true
            notifySessionEvent()
        }
        logdExt("Need to post event  $gson.toJson(event)")

        if (event.isKnownType) {
            val postableEvent = PostableEvent.fromEvent(event)
            logdExt("Posting analytics event  ${postableEvent.mEventType}")
            val subscribe = mAnalyticsApiService.notifyEvent(postableEvent)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    logdExt("Response Status- ${response.code()}")
                }, { error ->
                    logeExt("$error Error posting Event")
                })
        }
    }

    private fun validateInactivityOfSession() {
        val currentTime = System.currentTimeMillis()
        val diff = currentTime - mSessionIdLastUsed
        if (diff > mMaxSessionInactiveDuration) {
            logdExt("Session has been inactive for %d milli second duration, max diff is $diff $mMaxSessionInactiveDuration")
            generateSessionId()
        } else {
            mSessionIdLastUsed = System.currentTimeMillis()
        }
    }

    /**
     * Notify a section visit
     *
     * @param section section that is visited
     */
    fun notifySectionVisitPageView(section: Section) {
        val pageViewEvent = PageViewEvent()
        /*if (Section.isHome(section)) {
            pageViewEvent.mPageType = PageViewEvent.PAGE_TYPE_HOME
        } else {*/
        pageViewEvent.mPageType = PageViewEvent.PAGE_TYPE_SECTION
        /*}*/
        pageViewEvent.mUrl = section.name
        postAnalyticsEvent(pageViewEvent)
    }

    /**
     * Notify a Collection visit
     *
     * @param collectionName that is visited
     */
    fun notifyCollectionVisitPageView(collectionName: String) {
        val pageViewEvent = PageViewEvent()
        pageViewEvent.mPageType = PageViewEvent.PAGE_TYPE_COLLECTION
        pageViewEvent.mUrl = collectionName
        postAnalyticsEvent(pageViewEvent)
    }

    /**
     * Notify a page visit of type search results
     */
    fun notifySearchResultsVisitPageView() {
        val pageViewEvent = PageViewEvent()
        pageViewEvent.mPageType = PageViewEvent.PAGE_TYPE_SEARCH_RESULTS
        postAnalyticsEvent(pageViewEvent)
    }

    /**
     * Notify a page visit of type story
     *
     * @param story story that is visited
     */
    fun notifyStoryVisitPageView(story: Story) {
        val pageViewEvent = PageViewEvent()
        pageViewEvent.mPageType = PageViewEvent.PAGE_TYPE_STORY
        pageViewEvent.mUrl = story.slug
        pageViewEvent.mId = UUID.randomUUID().toString()
        mStoryVisitPageViewEventId = pageViewEvent.mId
        postAnalyticsEvent(pageViewEvent)
    }

    /**
     * Notify a story visit
     *
     * @param story story that is visited
     */
    fun notifyStoryVisit(story: Story) {
        logdExt("Notify story visit  $story.headline()")
        val storyViewEvent = StoryViewEvent()
        storyViewEvent.mPageViewEventId = mStoryVisitPageViewEventId
        storyViewEvent.mStoryContentId = story.storyContentId
        postAnalyticsEvent(storyViewEvent)
    }

    /**
     * Notify story element view
     *
     * @param story        story of the story element
     * @param card         card of the story element
     * @param storyElement story element
     */
    fun notifyStoryElementVisible(story: Story, card: Card, storyElement: StoryElement) {
        val event = StoryElementViewEvent()
        event.mStoryContentId = story.storyContentId
        event.mStoryVersionId = story.storyVersionId
        event.mCardContentId = card.contentId
        event.mCardVersionId = card.contentVersionId
        event.mStoryElementId = storyElement.id()
        event.mType = storyElement.type()!!
        event.mPageViewEventId = mStoryVisitPageViewEventId
        postAnalyticsEvent(event)
    }

    /**
     * Notify story element action
     *
     * @param story             story of the story element
     * @param card              card of the story element
     * @param storyElement      story element
     * @param timeStampInMillis action timestamp in milli seconds
     * @param action            kind of action
     */
    fun notifyStoryElementAction(
        story: Story, card: Card, storyElement: StoryElement,
        timeStampInMillis: Long, action: StoryElementActionEvent.ACTION
    ) {
        val event = StoryElementActionEvent()
        event.mStoryContentId = story.storyContentId
        event.mStoryVersionId = story.storyVersionId
        event.mCardContentId = card.contentId
        event.mCardVersionId = card.contentVersionId
        event.mStoryElementId = storyElement.id()
        event.mType = storyElement.type()!!
        event.mPageViewEventId = mStoryVisitPageViewEventId
        event.mActionTimeInMillis = timeStampInMillis
        event.mStoryElementAction = action.toString()
        postAnalyticsEvent(event)
    }

    /**
     * Notify content share analytics event
     *
     * @param story    story that is shared
     * @param provider provider to which story is shared
     */
    fun notifyContentShare(story: Story, provider: String) {
        val event = ContentShareEvent()
        event.mSocialMediaType = provider
        event.mStoryContentId = story.storyContentId
        event.mContentType = "story"
        event.mPageViewEventId = mStoryVisitPageViewEventId
        event.mUrl = story.slug
        postAnalyticsEvent(event)
    }

    /**
     * Notify content share to facebook analytics event
     *
     * @param story story that is shared
     */
    fun notifyFacebookShare(story: Story) {
        notifyContentShare(story, "facebook")
    }

    /**
     * Notify content share to twitter analytics event
     *
     * @param story story that is shared
     */
    fun notifyTwitterShare(story: Story) {
        notifyContentShare(story, "twitter")
    }

    /**
     * Notify content share to google analytics event
     *
     * @param story story that is shared
     */
    fun notifyGooglePlusShare(story: Story) {
        notifyContentShare(story, "google-plus")
    }

    private fun postAnalyticsEvent(event: AnalyticsEvent) {
        if (TextUtils.isEmpty(event.mId)) {
            event.mId = UUID.randomUUID().toString()
        }
        try {

            //            if (mUser.isLoggedIn() && !TextUtils.isEmpty(mUser.getUserId())) {
            //                event.mMemberId = Long.valueOf(mUser.getUserId());
            //            } else {
            event.mMemberId = null
            //            }
            if (installationDetails != null) {
                event.mDeviceTrackerId = installationDetails?.deviceId
            }
            event.mPublisherId = mPublisherID
        } catch (e: NumberFormatException) {
            logeExt("$e Publisher id or member id is not a long value")
        }

        event.mSessionEventId = mSessionEventId
        postEvent(event)
    }


    /**
     * @return user agent string
     */
    private fun getUserAgent(): String {
        return if (installationDetails?.appVersionCode != 0 && !TextUtils
                .isEmpty(installationDetails?.appVersionName)
        ) {
            (appName + " " + installationDetails?.appVersionName
                    + " (" + installationDetails?.appVersionCode + ")")
        } else {
            appName
        }
    }
}