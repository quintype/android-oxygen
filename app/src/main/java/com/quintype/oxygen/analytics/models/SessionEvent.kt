package com.quintype.oxygen.analytics.models

import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.analytics.models.Event
import com.quintype.oxygen.analytics.models.EventType

class SessionEvent : Event() {
    @SerializedName("id")
    var mId: String? = null
    @SerializedName("member-id")
    var mMemberId: Long? = null
    @SerializedName("publisher-id")
    var mPublisherId: Long? = null
    @SerializedName("user-agent")
    var mUserAgent: String? = null
    @SerializedName("device-type")
    var mDeviceType = "Android"
    @SerializedName("device-is-mobile")
    var mDeviceIsMobile = true
    @SerializedName("os")
    var mOS: String? = null
    @SerializedName("device-maker")
    var mDeviceMaker: String? = null
    @SerializedName("device-model")
    var mDeviceModel: String? = null

    /**
     * Default constructor
     */
    init {
        mEventType = EventType.SESSION
    }
}
