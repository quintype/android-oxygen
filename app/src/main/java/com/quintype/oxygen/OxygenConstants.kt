package com.quintype.oxygen

/**
 * Created TemplateCollectionWithRx by rakshith on 7/23/18.
 */
class OxygenConstants {
    companion object {
        var BASE_URL: String? = null
        var POLLTYPE_HOST_URL: String? = null
        private var METYPE_BASE_URL: String? = null

        fun getMetypeBaseURL(isOrigin: Boolean = false): String {
            if (isOrigin) {
                return METYPE_BASE_URL!!.substring(0, METYPE_BASE_URL!!.trim().length - 1)
            }
            return METYPE_BASE_URL!!
        }

        fun initBaseUrl(mBaseUrl: String) {
            BASE_URL = mBaseUrl
        }

        fun initPolltypeUrl(pollTypeHost: String) {
            POLLTYPE_HOST_URL = pollTypeHost
        }

        fun initMetTypeUrl(metypeUrl: String) {
            METYPE_BASE_URL = metypeUrl
        }

        const val COLLECTION_HOME: String = "home"
        const val PAGE_LIMIT_CHILD: Int = 5
        const val STORY_LIMIT: Int = 20
        const val COMMENTS_LIMIT: Int = 20
        const val TRENDING_STORY_LIMIT: Int = 5

        const val TYPE_BREAKING_NEWS: String = "breaking-news"
        const val WIDGET_TEMPLATE: String = "widget"
        const val KEY_BUNDLE: String = "bundle"
        const val KEY_TRENDING: String = "trending"
        const val KEY_WIDGET: String = "widget"
        const val MAGAZINE_STORY_FIELDS: String =
            "id,hero-image-s3-key,sections,headline,authors,created-at,hero-image-caption,story-content-id,alternative,hero-image-metadata,slug,last-published-at,published-at,first-published-at,story-template,subheadline,author-name,cards,tags,subheadline,access-level-values,access"
        const val LIMITED_STORY_FIELDS = "id,headline,slug"

        /**
         * OxygenConstants for query params
         */
        const val CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8 = "Content-Type: application/json; " + "charset=utf-8"
        const val CONTENT_TYPE_HTTP_ACCESS_TOKEN = "Access-token"
        const val COLLECTION_SLUG = "collections-slug"
        const val QUERY_PARAM_KEY_LIMIT = "limit"
        const val QUERY_PARAM_KEY_ID = "id"
        const val QUERY_PARAM_POLL_ID = "poll-id"
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
        const val QUERY_PARAM_KEY_CONTENT_TYPE = "content-types"
        const val QUERY_PARAM_KEY_ACCOUNT_ID = "account_id"
        const val QUERY_PARAM_KEY_JWT = "jwt"
        const val QUERY_PARAM_HEADER_ORIGIN = "origin"
        const val QUERY_PARAM_KEY_PAGE = "page"
        const val QUERY_PARAM_KEY_PER_PAGE = "per_page"

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
const val BREAKING_NEWS_SLUG: String = "breaking-news"
const val COLLECTION_METADATA_TYPE_BUNDLE: String = "bundle"

const val ACCOUNT_ID = "account_id"
const val PAGE_ID = "page_id"
const val JWT = "jwt"
const val PAGE = "page"
const val ORIGIN = "Origin"

/**
 * Viewholder types
 */
const val TYPE_INVALID_TYPE = 0

/**
 * Screen width percentage
 */
const val WIDTH_PERCENTAGE_30: Double = 0.30
const val WIDTH_PERCENTAGE_75: Double = 0.75
const val WIDTH_PERCENTAGE_80: Double = 0.80
const val WIDTH_PERCENTAGE_95: Double = 0.95
const val WIDTH_PERCENTAGE_100: Double = 1.00

/**
 * constants for layout names
 */
const val COMPONENT_FIVE_STORY_1AD = "five-story-one-ad"
const val COMPONENT_16S_4C = "sixteen-story-4c"
const val COMPONENT_4S_2C4S_1AD_1WIDGET = "4S-2C4S-1Ad-1Widget"
const val COMPONENT_2C_4S = "two-collection-four-story"

/**
 *  deciding wither we need innerCollection to show stories inside component
 *  Need to add layout name inside this method if we need to make inner collection API call
 */
fun isInnerCollectionRequired(layoutName: String?): Boolean {
    return when (layoutName) {
        COMPONENT_16S_4C, COMPONENT_4S_2C4S_1AD_1WIDGET, COMPONENT_2C_4S -> true
        else -> false
    }
}

const val TYPE_STORY: String = "story"
const val TYPE_COLLECTION: String = "collection"

const val STORY_FIELDS: String =
    "id,hero-image-s3-key,sections,headline,authors,created-at,hero-image-caption,story-content-id,alternative,hero-image-metadata,slug,last-published-at,published-at,first-published-at,story-template,subheadline,author-name,access-level-values,access,url,metadata"
const val SEARCH_STORY_FIELDS =
    "author-name,tags,headline,authors,story-content-id,slug,last-published-at,subheadline,sections,hero-image-metadata,published-at,id,hero-image-s3-key,author-id,first-published-at,hero-image-caption,story-template,created-at,alternative,access-level-values,access,metadata"

const val AUTHOR_STORY_FIELDS =
    "id,slug,headline,hero-image-s3-key,hero-image-caption,hero-image-metadata,alternative,last-published-at,subheadline,author-name,url,story-template,authors,metadata"
const val QUERY_PARAM_KEY_PLATFORM = "platform"
const val QUERY_PARAM_KEY_DEVICE_ID = "deviceid"

const val QUERY_PARAM_EXTERNAL_IDS = "external-ids"

const val KEY_PARAM_ID = "id"
const val CALCULATE_HEIGHT = "CALCULATE_HEIGHT"
const val CALCULATE_WIDTH = "CALCULATE_WIDTH"