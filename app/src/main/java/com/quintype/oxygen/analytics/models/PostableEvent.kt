package com.quintype.oxygen.analytics.models

import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.analytics.models.Event

class PostableEvent {
    @SerializedName("event-type")
    var mEventType: String? = null
    @SerializedName("event")
    var mEvent: Event? = null

    companion object {

        /**
         * @param event event which needs to be converted to postable event
         * @return a new instance of postable event
         */
        fun fromEvent(event: Event): PostableEvent {
            val postableEvent = PostableEvent()
            postableEvent.mEventType = event.mEventType!!.getName()
            postableEvent.mEvent = event
            return postableEvent
        }
    }
}
