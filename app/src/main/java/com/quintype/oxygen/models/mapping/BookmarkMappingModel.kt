package com.quintype.oxygen.models.mapping

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.story.Story
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookmarkMappingModel(
    @SerializedName("bookmark-article-mapping")
    @Expose
    var mBookmarkMap: HashMap<String, Story> = HashMap()
) : Parcelable