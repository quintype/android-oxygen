package com.quintype.oxygen.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.story.Story


class TagListResponse {
    @SerializedName("stories")
    @Expose
    var stories: List<Story>? = null
}
