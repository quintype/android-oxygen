package com.quintype.oxygen.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.STORY_FIELDS

/**
 * Created TemplateCollectionWithRx by rakshith on 7/31/18.
 */

class BulkCollectionRequest(slug: String) {
    @SerializedName("_type")
    @Expose
    private var type: String? = "collection"
    @SerializedName("slug")
    @Expose
    private var slug: String? = slug
    @SerializedName("limit")
    @Expose
    private var limit: Int? = OxygenConstants.PAGE_LIMIT_CHILD
    @SerializedName("story-fields")
    @Expose
    private var storyField: String? = STORY_FIELDS
}