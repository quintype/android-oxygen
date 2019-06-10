package com.quintype.oxygen.analytics.models

import com.google.gson.annotations.SerializedName

open class Event {
    @SerializedName("device-tracker-id")
    var mDeviceTrackerId: String? = null

    @Transient
    var mEventType: EventType? = null

    /**
     * @return whether this event type is session
     */
    private val isSessionType: Boolean
        get() = mEventType == EventType.SESSION

    /**
     * @return whether this event type is page view
     */
    private val isPageViewType: Boolean
        get() = mEventType == EventType.PAGE_VIEW

    /**
     * @return whether this event type is story element view
     */
    private val isStoryElementViewEventType: Boolean
        get() = mEventType == EventType.STORY_ELEMENT_VIEW

    /**
     * @return whether this event is content share type
     */
    private val isContentShareType: Boolean
        get() = mEventType == EventType.CONTENT_SHARE

    /**
     * @return whether this event is story view type
     */
    private val isStoryViewType: Boolean
        get() = mEventType == EventType.STORY_VIEW

    /**
     * @return whether this event is story element action type
     */
    private val isStoryElementActionType: Boolean
        get() = mEventType == EventType.STORY_ELEMENT_ACTION

    /**
     * @return whether the event is of known type
     */
    val isKnownType: Boolean
        get() = (isSessionType || isContentShareType || isPageViewType || isStoryElementActionType
                || isStoryElementViewEventType || isStoryViewType)
}
