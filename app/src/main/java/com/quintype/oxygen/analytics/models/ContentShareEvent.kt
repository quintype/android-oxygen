package com.quintype.oxygen.analytics.models

import com.google.gson.annotations.SerializedName

class ContentShareEvent : AnalyticsEvent() {
    @SerializedName("page-view-event-id")
    var mPageViewEventId: String? = null
    @SerializedName("story-content-id")
    var mStoryContentId: String? = null
    @SerializedName("content-type")
    var mContentType: String? = null
    @SerializedName("social-media-type")
    var mSocialMediaType: String? = null
    @SerializedName("url")
    var mUrl: String? = null

    /**
     * Default constructor
     */
    init {
        this.mEventType = EventType.CONTENT_SHARE
    }

}
