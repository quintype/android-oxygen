package com.quintype.oxygen.models.collection

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.latest.home.ChildItem

class AuthorProfileResponse {
    @SerializedName("total-count")
    @Expose
    var totalCount: Int? = null
    @SerializedName("template")
    @Expose
    var template: String? = null
    @SerializedName("items")
    @Expose
    var items: List<ChildItem>? = null
}