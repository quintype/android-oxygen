package com.quintype.oxygen.models.collection

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.story.Story


class BreakingNewsModel {
    @SerializedName("stories")
    @Expose
    private var stories: List<Story>? = null

    fun getStories(): List<Story>? {
        return stories
    }

    fun setStories(stories: List<Story>) {
        this.stories = stories
    }
}
