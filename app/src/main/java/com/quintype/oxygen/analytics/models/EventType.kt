package com.quintype.oxygen.analytics.models

enum class EventType(name: String) {
    SESSION("session"),
    PAGE_VIEW("page-view"),
    STORY_ELEMENT_VIEW("story-element-view"),
    CONTENT_SHARE("content-share"),
    STORY_VIEW("story-view"),
    STORY_ELEMENT_ACTION("story-element-action");

    private var mName: String = name

    /**
     * @return name of this event
     */
    fun getName(): String {
        return mName
    }

}
