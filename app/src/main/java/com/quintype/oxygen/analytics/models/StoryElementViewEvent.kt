package com.quintype.oxygen.analytics.models

import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.analytics.models.AnalyticsEvent
import com.quintype.oxygen.analytics.models.EventType

open class StoryElementViewEvent : AnalyticsEvent() {
    @SerializedName("story-content-id")
    var mStoryContentId: String? = null
    @SerializedName("story-version-id")
    var mStoryVersionId: String? = null
    @SerializedName("card-content-id")
    var mCardContentId: String? = null
    @SerializedName("card-version-id")
    var mCardVersionId: String? = null
    @SerializedName("story-element-id")
    var mStoryElementId: String? = null
    @SerializedName("story-element-type")
    var mType = "story-element-view"
    @SerializedName("page-view-event-id")
    var mPageViewEventId: String? = null

    /**
     * Default constructor
     */
    init {
        this.mEventType = EventType.STORY_ELEMENT_VIEW
    }

}
