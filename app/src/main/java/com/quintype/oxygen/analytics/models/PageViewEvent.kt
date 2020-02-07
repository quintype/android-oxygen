package com.quintype.oxygen.analytics.models

import com.google.gson.annotations.SerializedName

class PageViewEvent : AnalyticsEvent() {
    @SerializedName("page-type")
    var mPageType: String? = null
    @SerializedName("referrer")
    var mReferrer = ""
    @SerializedName("url")
    var mUrl: String? = null

    /**
     * Default constructor
     */
    init {
        this.mEventType = EventType.PAGE_VIEW
    }

    companion object {
        const val PAGE_TYPE_HOME = "home"
        const val PAGE_TYPE_STORY = "story"
        const val PAGE_TYPE_SECTION = "section"
        const val PAGE_TYPE_COLLECTION = "collection"
        const val PAGE_TYPE_SEARCH_RESULTS = "story-search-results"
    }
}
