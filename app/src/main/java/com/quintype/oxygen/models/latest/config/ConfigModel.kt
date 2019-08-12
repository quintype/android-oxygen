package com.quintype.oxygen.models.latest.config

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ConfigModel {
    @SerializedName("cdn-name")
    @Expose
    var cdnName: String? = null
    @SerializedName("publisher-id")
    @Expose
    var publisherId: Long? = null
    @SerializedName("publisher-name")
    @Expose
    var publisherName: String? = null
    @SerializedName("cdn-image")
    @Expose
    var cdnImage: String? = null
    @SerializedName("copyright")
    @Expose
    var copyright: String? = null
    @SerializedName("static-page-urls")
    @Expose
    var staticPageUrls: List<String>? = null
    @SerializedName("shrubbery-host")
    @Expose
    var shrubberyHost: String? = null
    @SerializedName("polltype-host")
    @Expose
    var polltypeHost: String? = null
    @SerializedName("sections")
    @Expose
    var sections: String? = null
}