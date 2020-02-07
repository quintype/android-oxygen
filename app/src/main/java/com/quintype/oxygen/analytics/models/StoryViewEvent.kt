package com.quintype.oxygen.analytics.models

import com.google.gson.annotations.SerializedName

class StoryViewEvent : AnalyticsEvent() {
    @SerializedName("page-view-event-id")
    var mPageViewEventId: String? = null
    @SerializedName("story-content-id")
    var mStoryContentId: String? = null

    /**
     * Default constructor
     */
    init {
        this.mEventType = EventType.STORY_VIEW
    }

}
