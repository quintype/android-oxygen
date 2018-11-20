package com.quintype.androidoxygen

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Point
import android.os.Build
import android.preference.PreferenceManager
import android.support.annotation.RequiresApi
import android.text.Html
import android.view.WindowManager
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created TemplateCollectionWithRx by rakshith on 7/23/18.
 */

class Constants {
    companion object {
        var BASE_URL: String? = null

        fun initBaseUrl(mBaseUrl: String) {
            BASE_URL = mBaseUrl
        }

        //const val BASE_URL = "https://thequint-web.staging.quintype.io"
        //const val BASE_URL = "https://www.thequint.com"
        //const val BASE_URL = "https://madrid.quintype.io"
        //        const val BASE_URL = "https://www.knocksense.com/"
        //const val BASE_URL = "https://www.samachara.com"
        const val COLLECTION_HOME: String = "home"
        const val PAGE_LIMIT: Int = 20
        const val TYPE_COLLECTION: String = "collection"
        const val PAGE_LIMIT_CHILD: Int = 5
        const val COLLECTION_LIMIT: Int = 5
        const val TYPE_STORY: String = "story"
        const val WIDGET_TEMPLATE: String = "widget"
        const val STORY_FIELDS: String =
            "id,hero-image-s3-key,sections,headline,authors,created-at,hero-image-caption,story-content-id,alternative,hero-image-metadata,slug,last-published-at,published-at,first-published-at"
        const val PAGE_TITLE: String = "PAGE_TITLE"
        /**
         * Constants for query params
         */
        const val CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8 = "Content-Type: application/json; " + "charset=utf-8"
        const val COLLECTION_SLUG = "collections-slug"
        const val QUERY_PARAM_KEY_LIMIT = "limit"
        const val QUERY_PARAM_KEY_OFFSET = "offset"
        const val QUERY_PARAM_KEY_FIELDS = "story-fields"
        const val QUERY_PARAM_KEY_ITEM_TYPE = "item-type"
        const val QUERY_PARAM_KEY_STORY_SLUG = "slug"
        const val QUERY_PARAM_KEY_TAG_NAME = "tag"
        const val QUERY_PARAM_KEY_SEARCH_TERM = "q"

        /**
         * for storyElementSubTypeMetadata
         */
        const val TYPE_SLIDESHOW = "slideshow"
        const val TYPE_GALLERY = "gallery"
        const val TYPE_INVALID = "invalid"
    }
}