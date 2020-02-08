package com.quintype.oxygen.models.story

import android.os.Parcelable
import android.support.annotation.StringDef
import android.text.TextUtils
import android.util.Base64
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.nio.charset.StandardCharsets
import java.util.*
import java.util.regex.Pattern
import kotlin.text.Charsets.UTF_8

/**
 * Encapsulates story element present inside a [Card]
 *
 * @author Imran imran@quintype.com
 * @author Madhu madhu@quintype.com
 */
@Parcelize
open class StoryElement(
    @SerializedName("image-metadata")
    var imageMeta: ImageMetaData? = null,
    @SerializedName("polltype-id")
    var polltypeId: String? = null,
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("text")
    var text: String? = null,
    @SerializedName("type")
    var type: String? = "",
    @SerializedName("image-s3-key")
    var imageS3Key: String? = null,
    @SerializedName("image-attribution")
    var imageAttribution: String? = null,
    var nestedStory: Story? = null,
    @SerializedName("embed-url")
    private var embedUrl: String? = null,
    @SerializedName("url")
    private var url: String? = null,
    @SerializedName("page-url")
    private var pageUrl: String? = null,
    @SerializedName("embed-js")
    private var embedJs: String? = "",
    @SerializedName("subtype")
    private var subType: String? = null,
    @SerializedName("metadata")
    var subTypeMeta: StoryElementSubTypeMetaData? = null,
    @SerializedName("story-elements")
    private var storyElements: List<StoryElement> = emptyList(),
    @SerializedName("data")
    private var data: StoryElementData? = null,
    @SerializedName("file-name")
    private var attachmentFileName: String? = null,
    private var decodedJsEmbed: String? = "",
    private var tweetId: Long? = null,
    /**
     * @return true is StoryElement is of type [.TYPE_STORY_ELEMENT_JSEMBED] and Twitter
     */
    var isTypeJsEmbedWithTwitter: Boolean = false,
    var cardAddedAt: Long? = null,// Used for TheQuint's LiveBlogTemplate
    var isKeyEvent: Boolean = false,// Used for TheQuint's LiveBlogTemplate
    var isCardPinned: Boolean = false,
    var listicleCardCount: Int = 0,//Used for Listicle Story Template
    var pageIndex: Int = 0,
    var cardID: String? = null,
    var cardSocialShareTitle: String? = null
) : Parcelable {
    /**
     * @return whether this story element is text type
     */
    val isTypeText: Boolean
        get() = type!!.equals(TYPE_TEXT, ignoreCase = true)

    /**
     * @return whether this story element is image type and gif
     */
    val isTypeImageGif: Boolean
        get() = type!!.equals(TYPE_STORY_ELEMENT_IMAGE, ignoreCase = true) && imageS3Key!!.endsWith(
            ".gif"
        )

    /**
     * @return whether this story element is image type
     */
    val isTypeImage: Boolean
        get() = type!!.equals(TYPE_STORY_ELEMENT_IMAGE, ignoreCase = true)

    /**
     * @return whether this story element is video type
     */
    val isTypeVideo: Boolean
        get() = type!!.equals(TYPE_STORY_ELEMENT_VIDEO, ignoreCase = true)

    /**
     * @return whether this story element is youtube type
     */
    val isTypeYoutube: Boolean
        get() = type!!.equals(TYPE_STORY_ELEMENT_YOUTUBE_VIDEO, ignoreCase = true)

    /**
     * @return whether this story element is BitGravity Video
     */
    val isTypeBitGravity: Boolean
        get() = if (subType != null)
            isTypeExternal && subType!!.equals(
                TYPE_STORY_ELEMENT_SUB_BIT_GRAVITY, ignoreCase = true
            )
        else
            false

    /**
     * @return whether this story element is media type
     */
    val isTypeMedia: Boolean
        get() = type!!.equals(TYPE_STORY_ELEMENT_MEDIA, ignoreCase = true)

    /**
     * @return whether this story element is audio type
     */
    val isTypeAudio: Boolean
        get() = type!!.equals(TYPE_STORY_ELEMENT_AUDIO, ignoreCase = true)

    /**
     * @return whether this story element is quote type
     */
    val isTypeVirtualQuote: Boolean
        get() = type!!.equals(TYPE_STORY_ELEMENT_VIRTUAL_QUOTE, ignoreCase = true)

    /**
     * @return whether this story element is blog posted at type
     */
    val isTypeBlogPostedAt: Boolean
        get() = type!!.equals(TYPE_STORY_ELEMENT_BLOG_POSTED_AT, ignoreCase = true)

    /**
     * @return whether this story element is blog updated at type
     */
    val isTypeBlogUpdatedAt: Boolean
        get() = type!!.equals(TYPE_STORY_ELEMENT_BLOG_UPDATED_AT, ignoreCase = true)

    /**
     * @return whether this story element is soundcloud type
     */
    val isTypeSoundcloud: Boolean
        get() = type!!.equals(TYPE_STORY_ELEMENT_SOUNDCLOUD_AUDIO, ignoreCase = true)

    /**
     * @return whether this story element is title type
     */
    val isTypeTitle: Boolean
        get() = type!!.equals(TYPE_STORY_ELEMENT_TITLE, ignoreCase = true)

    /**
     * @return whether this story element is title type
     */
    val isTypeTable: Boolean
        get() = if (subType != null) type!!.equals(
            TYPE_STORY_ELEMENT_DATA,
            ignoreCase = true
        ) && subType!!.equals(
            SUB_TYPE_STORY_ELEMENT_TABLE,
            ignoreCase = true
        ) else false

    /**
     * @return whether this story element is timeStamp type
     */
    val isTypeTimeStamp: Boolean
        get() = type!!.equals(TYPE_LIVE_BLOG_ADDED_AT_TIME_STAMP, ignoreCase = true)

    /**
     * @return whether this story element is timeStamp type
     */
    val isTypeCardSocialShare: Boolean
        get() = type!!.equals(TYPE_CARD_SOCIAL_SHARE, ignoreCase = true)

    /**
     * @return whether this story element is dummy sort card type
     */
    val isTypeSortCards: Boolean
        get() = type!!.equals(TYPE_SORT_CARDS, ignoreCase = true)

    /**
     * @return whether this story element is dummy type nested story
     */
    val isTypeNestedStory: Boolean
        get() = type!!.equals(TYPE_NESTED_STORY, ignoreCase = true)

    /**
     * @return whether this story element is poll type
     */
    val isTypePollType: Boolean
        get() {

            if (type!!.equals(TYPE_STORY_ELEMENT_POLLTYPE, ignoreCase = true)) {
                return true
            } else if (!TextUtils.isEmpty(polltypeId)) {
                return true
            } else if (isTypeJsembed) {
                if (TextUtils.isEmpty(decodedJsEmbed)) {
                    val data = Base64.decode(embedJs, Base64.DEFAULT)
                    var decodedJsEmbed = String(data, UTF_8)
                    decodedJsEmbed = decodedJsEmbed.replace("src=\"//", "src=\"http://")
                    val m = POLLTYPE_EMBED_PATTERN.matcher(decodedJsEmbed)
                    var pollId: String? = null
                    while (m.find() && pollId == null) {
                        println(m.group())
                        pollId = m.group()
                    }
                    if (TextUtils.isEmpty(pollId)) {
                        return false
                    } else {
                        assert(pollId != null)
                        polltypeId = pollId!!.replace("[^0-9]".toRegex(), "")
                        return true
                    }
                } else {
                    val m = POLLTYPE_EMBED_PATTERN.matcher(decodedJsEmbed)
                    var pollId: String? = null
                    while (m.find() && pollId == null) {
                        println(m.group())
                        pollId = m.group()
                    }
                    if (TextUtils.isEmpty(pollId)) {
                        return false
                    } else {
                        assert(pollId != null)
                        polltypeId = pollId!!.replace("[^0-9]".toRegex(), "")
                        return true
                    }
                }
            } else {
                return false
            }
        }

    /**
     * @return whether this story element is external file type
     */

    val isTypeExternal: Boolean
        get() = type!!.equals(TYPE_STORY_ELEMENT_EXTERNAL, ignoreCase = true)

    /**
     * @return whether the type of this element is summary
     */
    val isTypeSummary: Boolean
        get() = if (subType != null)
            isTypeText && subType!!.equals(SUB_TYPE_STORY_ELEMENT_SUMMARY, ignoreCase = true)
        else
            false

    /**
     * @return whether the type of the element is quote
     */
    val isTypeQuote: Boolean
        get() = if (subType != null)
            isTypeText && subType!!.equals(SUB_TYPE_STORY_ELEMENT_QUOTE, ignoreCase = true)
        else
            false

    /**
     * @return whether the type of the element is blockquote
     */
    val isTypeBlockQuote: Boolean
        get() = if (subType != null)
            isTypeText && subType!!.equals(SUB_TYPE_STORY_ELEMENT_BLOCKQUOTE, ignoreCase = true)
        else
            false

    val isTypeQuestionAnswer: Boolean
        get() = if (subType != null)
            isTypeText && subType!!.equals(
                SUB_TYPE_STORY_ELEMENT_QUESTION_ANSWER,
                ignoreCase = true
            )
        else
            false

    val isTypeQuestion: Boolean
        get() = if (subType != null)
            isTypeText && subType!!.equals(SUB_TYPE_STORY_ELEMENT_QUESTION, ignoreCase = true)
        else
            false

    val isTypeAnswer: Boolean
        get() = if (subType != null)
            isTypeText && subType!!.equals(SUB_TYPE_STORY_ELEMENT_ANSWER, ignoreCase = true)
        else
            false

    /**
     * @return whether the type of the element is blurb
     */
    val isTypeBlurb: Boolean
        get() = if (subType != null)
            isTypeText && subType!!.equals(SUB_TYPE_STORY_ELEMENT_BLURB, ignoreCase = true)
        else
            false

    /**
     * @return whether the type of the element is composite
     */
    val isTypeComposite: Boolean
        get() = type!!.equals(TYPE_STORY_ELEMENT_COMPOSITE, ignoreCase = true)

    /**
     * @return whether the type of the element is image gallery
     */
    val isImageGallery: Boolean
        get() = if (subType != null)
            isTypeComposite && subType!!.equals(
                SUB_TYPE_STORY_ELEMENT_IMAGE_GALLERY,
                ignoreCase = true
            ) && subTypeMeta!!.isTypeGallery()
        else
            false

    val isReferences: Boolean
        get() = if (subType != null)
            isTypeComposite && subType!!.equals(
                SUB_TYPE_STORY_ELEMENT_REFERENCES,
                ignoreCase = true
            )
        else
            false

    val isIconWithTitle: Boolean
        get() = TYPE_ICON_WITH_TITLE == type

    /**
     * Check
     *
     * @return whether the type of the element is image slideshow
     */
    val isImageSlideshow: Boolean
        get() = if (subType != null)
            isTypeComposite && subType!!.equals(
                SUB_TYPE_STORY_ELEMENT_IMAGE_GALLERY,
                ignoreCase = true
            ) && subTypeMeta!!.isTypeSlideShow()
        else
            false

    /**
     * @return whether this story element is jsembed type
     */
    val isTypeJsembed: Boolean
        get() = type!!.equals(TYPE_STORY_ELEMENT_JSEMBED, ignoreCase = true)

    val isTypeTwitter: Boolean
        get() = if (subType != null)
            isTypeJsembed && subType!!.equals(SUB_TYPE_STORY_ELEMENT_TWEET, ignoreCase = true)
        else
            false

    /**
     * @return whether this story element is type big fact
     */
    val isTypeBigFact: Boolean
        get() = if (subType != null)
            isTypeText && subType!!.endsWith(SUB_TYPE_STORY_ELEMENT_BIG_FACT)
        else
            false

    /**
     * @return whether this story element is type brightcove-video
     */
    val isTypeBrightCoveVideo: Boolean
        get() = if (subType != null)
            isTypeExternal && subType!!.endsWith(SUB_TYPE_STORY_ELEMENT_BRIGHTCOVE)
        else
            false

    /**
     * @return whether this story element is type AlsoRead
     */
    val isAlsoRead: Boolean
        get() = if (subType != null)
            isTypeText && subType!!.endsWith(SUB_TYPE_STORY_ELEMENT_ALSO_READ)
        else
            false

    val isAttachment: Boolean
        get() = if (type != null && type!!.equals(TYPE_STORY_ELEMENT_FILE, ignoreCase = true))
            subType != null && subType!!.equals(
                SUB_TYPE_STORY_ELEMENT_ATTACHMENT,
                ignoreCase = true
            )
        else
            false

    /**
     * @return whether the type of the element is composite
     */
    val isGalleryAds: Boolean
        get() = type!!.equals(TYPE_GALLERY_ADS, ignoreCase = true)

    /**
     * @return whether this story element image is landscape, true by default
     */
    val isImageLandscape: Boolean
        get() = if (imageMeta != null) {
            imageMeta!!.width > imageMeta!!.height
        } else true

    /**
     * return tru if its social share widget
     *
     * @return
     */
    val isSocialShareWidget: Boolean
        get() = TYPE_SHARE_WIDGET == type

    /**
     * return tru if its social share widget
     *
     * @return
     */
    val isAdType: Boolean
        get() = TYPE_ADS == type

    /**
     * @return String Id of StoryElement
     */
    fun id(): String? {
        return id
    }

    /**
     * @return String title of StoryElement
     */
    fun title(): String? {
        return title
    }

    /**
     * @return String description of StoryElement
     */
    fun description(): String? {
        return description
    }

    /**
     * @return String StoryElement text
     */
    fun text(): String {
        try {
            return this.text!!
        } catch (ex: NullPointerException) {
            ex.printStackTrace()
            return ""
        }
    }

    /**
     * @return String StoryElement type
     */
    fun type(): String? {
        return type
    }

    /**
     * @return String image Url for StoryElement
     */
    fun imageS3Key(): String? {
        return imageS3Key
    }

    /**
     * @return String embed Url
     */
    fun embedUrl(): String? {
        return embedUrl
    }

    /**
     * @return String StoryElement url
     */
    fun url(): String? {
        return url
    }

    /**
     * @return String StoryElement fileName
     */
    fun fileName(): String? {
        return attachmentFileName
    }

    /**
     * @return String StoryElement page urls
     */
    fun pageUrl(): String? {
        return pageUrl
    }

    /**
     * @return String StoryElement embed js
     */
    fun embedJs(): String? {
        return embedJs
    }

    /**
     * @return [ImageMetaData]
     */
    fun imageMeta(): ImageMetaData? {
        return imageMeta
    }

    /**
     * @return String StoryElement subtype
     */
    fun subType(): String? {
        return subType
    }

    /**
     * @return [StoryElementSubTypeMetaData]
     */
    fun subTypeMeta(): StoryElementSubTypeMetaData? {
        return subTypeMeta
    }

    /**
     * @return List of internal StoryElements
     */
    fun storyElements(): List<StoryElement>? {
        return storyElements
    }

    /**
     * @return String decoded Js Embed
     */
    fun decodedJsEmbed(): String? {
        return decodedJsEmbed
    }

    /**
     * @return contents of a table element and the content type
     */
    fun data(): StoryElementData? {
        return data
    }

    /**
     * set the type of the calling story element as quote
     */
    fun setTypeAsQuote() {
        type = TYPE_STORY_ELEMENT_VIRTUAL_QUOTE
    }

    /**
     * set the type of the calling story element as blog posted at
     */
    fun setTypeAsBlogPostedAt() {
        type = TYPE_STORY_ELEMENT_BLOG_POSTED_AT
    }

    /**
     * set the type of the calling story element as blog updated at
     */
    fun setTypeAsBlogUpdatedAt() {
        type = TYPE_STORY_ELEMENT_BLOG_UPDATED_AT
    }

    fun tweetId(): Long? {
        return tweetId
    }

    /**
     * Parses and prepares this story element for twitter
     */
    fun prepareForTwitter() {
        isTypeJsEmbedWithTwitter = false
        if (subType != null) {
            if (subType!!.equals(
                    SUB_TYPE_STORY_ELEMENT_TWEET,
                    ignoreCase = true
                ) && !TextUtils.isEmpty(subTypeMeta!!.tweetId)
            ) {
                //Timber.d("Sub type matches twitter");
                try {
                    tweetId = java.lang.Long.valueOf(subTypeMeta?.tweetId!!)
                    isTypeJsEmbedWithTwitter = true
                } catch (e: Exception) {
                    //Timber.e(e, "Failed parsing twitter sub type");
                }

            } else
                isJsEmbedTwitterElement()
        } else {
            isJsEmbedTwitterElement()
        }

    }

    private fun isJsEmbedTwitterElement() {
        if (!TextUtils.isEmpty(embedJs)) {
            try {
                val data = Base64.decode(embedJs, Base64.DEFAULT)
                decodedJsEmbed = String(data, StandardCharsets.UTF_8)
                decodedJsEmbed = decodedJsEmbed!!.replace("src=\"//", "src=\"https://")
                val matcher = TWITTER_EMBED_PATTERN.matcher(decodedJsEmbed)
                if (matcher.find()) {
                    val tid = matcher.group(3)
                    tweetId = java.lang.Long.valueOf(tid)
                    isTypeJsEmbedWithTwitter = true
                }
            } catch (e: Exception) {
                //Timber.e(e, "Failed parsing js embed");
                decodedJsEmbed = ""
            }

        }
    }

    /**
     * @return String imageAttribution of StoryElement
     */
    fun imageAttribution(): String? {
        return imageAttribution
    }

    fun nestedStory(): Story {
        return nestedStory!!
    }

    fun nestedStory(nestedStory: Story) {
        this.nestedStory = nestedStory
    }

    fun title(title: String) {
        this.title = title
    }

    fun setCardPinStatus(cardPinStatus: Boolean) {
        this.isCardPinned = cardPinStatus
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef
    annotation class TYPE

    companion object {
        //quote: is virtual and client side only type
        val TYPE_STORY_ELEMENT_VIRTUAL_QUOTE = "virtual-quote"
        //posted-at: is virtual and client side only type, specific to live-blog style story template
        val TYPE_STORY_ELEMENT_BLOG_POSTED_AT = "blog-posted-at"
        //created-at: is virtual and client side only type, specific to live-blog style story template
        val TYPE_STORY_ELEMENT_BLOG_UPDATED_AT = "blog-updated-at"
        val INVALID_ELEMENT = StoryElement()
        val TYPE_SHARE_WIDGET = "ShareWidget"
        val TYPE_ADS = "Ad"
        //Known types for StoryElement
        val TYPE_TEXT = "text"
        val TYPE_LIVE_BLOG_ADDED_AT_TIME_STAMP =
            "type_live_blog_added_at_time_stamp"
        val TYPE_SORT_CARDS = "type_sort_cards" // Dummy type specifically
        val TYPE_GALLERY_ADS = "type_gallery_ads" // Dummy type specifically
        // for 'TheQuint'
        val TYPE_NESTED_STORY = "type_nested_story"
        val SUPPORTED_ELEM_TYPES: MutableSet<String> = HashSet()
        val ID_HERO = "hero"
        val TYPE_ICON_WITH_TITLE = "IconWithTitle"

        val TYPE_STORY_ELEMENT_IMAGE = "image"
        val TYPE_CARD_SOCIAL_SHARE = "type_card_social_share"
        //INVALID storyElement
        internal val INVALID_ID = "-1"
        private val TYPE_STORY_ELEMENT_JSEMBED = "jsembed"
        private val TYPE_STORY_ELEMENT_SOUNDCLOUD_AUDIO = "soundcloud-audio"
        private val TYPE_STORY_ELEMENT_AUDIO = "audio"
        private val TYPE_STORY_ELEMENT_MEDIA = "media"
        private val TYPE_STORY_ELEMENT_YOUTUBE_VIDEO = "youtube-video"
        private val TYPE_STORY_ELEMENT_EXTERNAL = "external-file"
        private val TYPE_STORY_ELEMENT_SUB_BIT_GRAVITY = "bitgravity-video"
        private val TYPE_STORY_ELEMENT_VIDEO = "video"
        private val TYPE_STORY_ELEMENT_TITLE = "title"
        private val TYPE_STORY_ELEMENT_COMPOSITE = "composite"
        private val TYPE_STORY_ELEMENT_POLLTYPE = "polltype"
        private val TYPE_STORY_ELEMENT_DATA = "data"
        private val TYPE_STORY_ELEMENT_FILE = "file"
        private val SUB_TYPE_STORY_ELEMENT_SUMMARY = "summary"
        private val SUB_TYPE_STORY_ELEMENT_TWEET = "tweet"
        private val SUB_TYPE_STORY_ELEMENT_QUOTE = "quote"
        private val SUB_TYPE_STORY_ELEMENT_BLOCKQUOTE = "blockquote"
        private val SUB_TYPE_STORY_ELEMENT_BLURB = "blurb"
        private val SUB_TYPE_STORY_ELEMENT_IMAGE_GALLERY = "image-gallery"
        private val SUB_TYPE_STORY_ELEMENT_BIG_FACT = "bigfact"
        private val SUB_TYPE_STORY_ELEMENT_QUESTION_ANSWER = "q-and-a"
        private val SUB_TYPE_STORY_ELEMENT_BRIGHTCOVE = "brightcove-video"
        private val SUB_TYPE_STORY_ELEMENT_ALSO_READ = "also-read"
        private val SUB_TYPE_STORY_ELEMENT_POLLTYPE = "opinion-poll"
        private val SUB_TYPE_STORY_ELEMENT_TABLE = "table"
        private val SUB_TYPE_STORY_ELEMENT_QUESTION = "question"
        private val SUB_TYPE_STORY_ELEMENT_ANSWER = "answer"
        private val SUB_TYPE_STORY_ELEMENT_ATTACHMENT = "attachment"
        private val SUB_TYPE_STORY_ELEMENT_REFERENCES = "references"
        private val TWITTER_EMBED_PATTERN =
            Pattern.compile("(https|http)://twitter\\.com/(\\w{1,15})/status/(\\d+)")
        private val POLLTYPE_EMBED_PATTERN = Pattern.compile("data-polltype-embed-id=\\d+")

        init {
            INVALID_ELEMENT.id = INVALID_ID
        }

        init {
            SUPPORTED_ELEM_TYPES.add(TYPE_STORY_ELEMENT_VIRTUAL_QUOTE)
            SUPPORTED_ELEM_TYPES.add(TYPE_TEXT)
            SUPPORTED_ELEM_TYPES.add(TYPE_STORY_ELEMENT_JSEMBED)
            SUPPORTED_ELEM_TYPES.add(TYPE_STORY_ELEMENT_SOUNDCLOUD_AUDIO)
            SUPPORTED_ELEM_TYPES.add(TYPE_STORY_ELEMENT_AUDIO)
            SUPPORTED_ELEM_TYPES.add(TYPE_STORY_ELEMENT_MEDIA)
            SUPPORTED_ELEM_TYPES.add(TYPE_STORY_ELEMENT_YOUTUBE_VIDEO)
            SUPPORTED_ELEM_TYPES.add(TYPE_STORY_ELEMENT_VIDEO)
            SUPPORTED_ELEM_TYPES.add(TYPE_STORY_ELEMENT_IMAGE)
            SUPPORTED_ELEM_TYPES.add(TYPE_STORY_ELEMENT_TITLE)
            SUPPORTED_ELEM_TYPES.add(TYPE_STORY_ELEMENT_COMPOSITE)
            SUPPORTED_ELEM_TYPES.add(TYPE_STORY_ELEMENT_DATA)
            SUPPORTED_ELEM_TYPES.add(SUB_TYPE_STORY_ELEMENT_TWEET)
            SUPPORTED_ELEM_TYPES.add(SUB_TYPE_STORY_ELEMENT_QUOTE)
            SUPPORTED_ELEM_TYPES.add(SUB_TYPE_STORY_ELEMENT_BLOCKQUOTE)
            SUPPORTED_ELEM_TYPES.add(SUB_TYPE_STORY_ELEMENT_BLURB)
            SUPPORTED_ELEM_TYPES.add(SUB_TYPE_STORY_ELEMENT_IMAGE_GALLERY)
            SUPPORTED_ELEM_TYPES.add(SUB_TYPE_STORY_ELEMENT_BIG_FACT)
            SUPPORTED_ELEM_TYPES.add(SUB_TYPE_STORY_ELEMENT_QUESTION_ANSWER)
            SUPPORTED_ELEM_TYPES.add(SUB_TYPE_STORY_ELEMENT_BRIGHTCOVE)
            SUPPORTED_ELEM_TYPES.add(SUB_TYPE_STORY_ELEMENT_POLLTYPE)
            SUPPORTED_ELEM_TYPES.add(SUB_TYPE_STORY_ELEMENT_TABLE)
            SUPPORTED_ELEM_TYPES.add(TYPE_LIVE_BLOG_ADDED_AT_TIME_STAMP)
            SUPPORTED_ELEM_TYPES.add(TYPE_SORT_CARDS)
            SUPPORTED_ELEM_TYPES.add(TYPE_NESTED_STORY)
            SUPPORTED_ELEM_TYPES.add(TYPE_ICON_WITH_TITLE)
            SUPPORTED_ELEM_TYPES.add(TYPE_SHARE_WIDGET)
            SUPPORTED_ELEM_TYPES.add(TYPE_CARD_SOCIAL_SHARE)
        }

        fun dummyElement(): StoryElement {
            val element = StoryElement()
            element.id = UUID.randomUUID().toString()
            element.type = "text"
            return element
        }

        fun iconWithRightTitleElement(): StoryElement {
            val element = StoryElement()
            element.id = UUID.randomUUID().toString()
            element.type = TYPE_ICON_WITH_TITLE
            return element
        }

        fun newStoryElement(elementType: String): StoryElement {
            val element = StoryElement()
            element.id = UUID.randomUUID().toString()
            element.type = elementType
            return element
        }

        fun shareWidgetElement(): StoryElement {
            val element = StoryElement()
            element.id = UUID.randomUUID().toString()
            element.type = TYPE_SHARE_WIDGET
            return element
        }

        /**
         * returns a partial clone of the story element to be used (used for breaking current story
         * element into multiple)
         *
         * @param storyElement - the story element that needs to be broken
         * @return the partially cloned story element object
         */
        fun fromStoryElement(storyElement: StoryElement): StoryElement {
            return StoryElement(storyElement)
        }
    }

    constructor(storyElement: StoryElement) : this()
}
