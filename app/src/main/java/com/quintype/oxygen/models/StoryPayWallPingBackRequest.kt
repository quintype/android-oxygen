package com.quintype.oxygen.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class StoryPayWallPingBackRequest {
    @SerializedName("storyIds")
    @Expose
    var storyIds: List<String>? = null
}