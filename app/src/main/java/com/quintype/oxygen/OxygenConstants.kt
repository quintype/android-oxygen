package com.quintype.oxygen

/**
 * Created TemplateCollectionWithRx by rakshith on 7/23/18.
 */
class OxygenConstants {
    companion object {
        var BASE_URL: String? = null

        fun initBaseUrl(mBaseUrl: String) {
            BASE_URL = mBaseUrl
        }

        const val COLLECTION_HOME: String = "home"
        const val TYPE_COLLECTION: String = "collection"
        const val PAGE_LIMIT_CHILD: Int = 5
        const val TYPE_STORY: String = "story"
        const val TYPE_BREAKING_NEWS: String = "breaking-news"
        const val WIDGET_TEMPLATE: String = "widget"
        const val KEY_BUNDLE: String = "bundle"
        const val KEY_TRENDING: String = "trending"
        const val KEY_WIDGET: String = "widget"
        const val STORY_FIELDS: String =
            "id,hero-image-s3-key,sections,headline,authors,created-at,hero-image-caption,story-content-id,alternative,hero-image-metadata,slug,last-published-at,published-at,first-published-at,story-template,subheadline,author-name,access-level-values,access"
        const val MAGAZINE_STORY_FIELDS: String =
            "id,hero-image-s3-key,sections,headline,authors,created-at,hero-image-caption,story-content-id,alternative,hero-image-metadata,slug,last-published-at,published-at,first-published-at,story-template,subheadline,author-name,cards,tags,subheadline,access-level-values,access"
        const val SEARCH_STORY_FIELDS =
            "author-name,tags,headline,authors,story-content-id,slug,last-published-at,subheadline,sections,hero-image-metadata,published-at,id,hero-image-s3-key,author-id,first-published-at,hero-image-caption,story-template,created-at,alternative,access-level-values,access"
        const val LIMITED_STORY_FIELDS = "id,headline,slug"

        /**
         * OxygenConstants for query params
         */
        const val CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8 = "Content-Type: application/json; " + "charset=utf-8"
        const val CONTENT_TYPE_HTTP_ACCESS_TOKEN = "Access-token"
        const val COLLECTION_SLUG = "collections-slug"
        const val QUERY_PARAM_KEY_LIMIT = "limit"
        const val QUERY_PARAM_KEY_OFFSET = "offset"
        const val QUERY_PARAM_KEY_FIELDS = "story-fields"
        const val QUERY_PARAM_KEY_SEARCH_FIELDS = "fields"
        const val QUERY_PARAM_KEY_ITEM_TYPE = "item-type"
        const val QUERY_PARAM_KEY_STORY_SLUG = "slug"
        const val QUERY_PARAM_KEY_TAG_NAME = "tag"
        const val QUERY_PARAM_KEY_SEARCH_TERM = "q"
        const val QUERY_PARAM_KEY_TYPE = "type"
        const val AUTHOR_ID = "AUTHOR_ID"
        const val MAGAZINE_ID = "MAGAZINE_ID"
        const val QUERY_PARAM_COLLECTION_DATE_AFTER = "collection-date-after"
        const val QUERY_PARAM_COLLECTION_DATE_BEFORE = "collection-date-before"

        /**
         * for storyElementSubTypeMetadata
         */
        const val TYPE_SLIDESHOW = "slideshow"
        const val TYPE_GALLERY = "gallery"
        const val TYPE_INVALID = "invalid"
    }
}

/**
 * for magazine type
 */
const val TYPE_MAGAZINE = "magazine"
const val TYPE_EBOOK = "eBook"

/**
 * Hardcoding the menu key for mobile(need seperate menu groups for mobile Apps)
 */
const val MOBILE_MENU = "mobile-menu"
const val STORY_ID = "STORY_ID"
const val X_VIKATAN_AUTH = "x-vikatan-auth"
const val COLLECTION_ID = "COLLECTION_ID"