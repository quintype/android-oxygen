package com.quintype.androidoxygen.models.collection

import com.quintype.androidoxygen.models.story.Story
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


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
