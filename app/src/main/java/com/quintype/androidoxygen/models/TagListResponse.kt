package com.quintype.androidoxygen.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.quintype.androidoxygen.models.story.Story


class TagListResponse {
    @SerializedName("stories")
    @Expose
    var stories: List<Story>? = null
}
