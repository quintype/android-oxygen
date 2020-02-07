package com.quintype.oxygen.analytics.models

import com.google.gson.annotations.SerializedName

class StoryElementActionEvent : StoryElementViewEvent() {

    @SerializedName("story-element-action")
    var mStoryElementAction: String? = null
    @SerializedName("action-timestamp")
    var mActionTimeInMillis: Long = 0

    enum class ACTION(private val mAction: String) {
        PLAY("play"), PAUSE("pause"), COMPLETE("complete"), OPINION_SELECT("opinion-select"),
        VOTE_INTENT("vote-intent"), VOTE_CHANGE_INTENT("vote-change-intent"),
        VOTE("vote"), AUTHORIZED_VOTE("authorised-vote");

        override fun toString(): String {
            return mAction
        }
    }

    /**
     * Default constructor
     */
    init {
        this.mEventType = EventType.STORY_ELEMENT_ACTION
    }

    companion object {
        const val ACTION_PLAY = "play"
        const val ACTION_PAUSE = "pause"
        const val ACTION_COMPLETE = "complete"
    }
}
