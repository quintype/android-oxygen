package com.quintype.oxygen.models.storypresenter

import android.view.View
import com.quintype.oxygen.models.story.StoryElement

interface StoryElementBinder {
    val view: View
    fun bind(var1: StoryElement)

    fun recreate(): Boolean
}
