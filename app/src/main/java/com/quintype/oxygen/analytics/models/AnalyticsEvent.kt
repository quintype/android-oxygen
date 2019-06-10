package com.quintype.oxygen.analytics.models

import com.google.gson.annotations.SerializedName

open class AnalyticsEvent : Event() {
    @SerializedName("session-event-id")
    var mSessionEventId: String? = null
    @SerializedName("publisher-id")
    var mPublisherId: Long? = null
    @SerializedName("member-id")
    var mMemberId: Long? = null
    @SerializedName("id")
    var mId: String? = null
}
