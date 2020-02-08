package com.quintype.oxygen.models

import android.os.Parcelable
import com.quintype.oxygen.models.story.StoryElement
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class StoryElementList(
    var mItems: List<StoryElement> = ArrayList(),
    var mSelectedItem: Int = 0
) : Parcelable