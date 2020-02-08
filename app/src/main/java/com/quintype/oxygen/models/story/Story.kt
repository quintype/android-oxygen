package com.quintype.oxygen.models.story

import android.os.Parcelable
import android.text.TextUtils
import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.author.Author
import com.quintype.oxygen.models.author.ContributorModel
import com.quintype.oxygen.models.latest.home.ChildItem
import com.quintype.oxygen.models.sections.Section
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.util.*

@Parcelize
data class Story(
    @SerializedName("updated-at")
    var updatedAt: Long = 0,
    @SerializedName("tags")
    var tags: List<Tag>? = null,
    @SerializedName("headline")
    var headline: String? = null,
    @SerializedName("subheadline")
    var subHeadLine: String? = null,
    @SerializedName("story-content-id")
    var storyContentId: String? = null,
    @SerializedName("slug")
    var slug: String? = null,
    @SerializedName("linked-stories")
    var linkedStories: HashMap<String, ChildItem>? = null,
    @SerializedName("last-published-at")
    var lastPublishedAt: Long = 0,
    @SerializedName("sections")
    var sections: List<Section>? = null,
    @SerializedName("owner-name")
    var ownerName: String? = null,
    @SerializedName("access")
    var access: String? = null,
    @SerializedName("access-level-value")
    var accessLevelValues: String? = null,
    @SerializedName("push-notification")
    var pushNotification: String? = null,
    @SerializedName("comments")
    var comments: String? = null,
    @SerializedName("is-published")
    var isPublished: String? = null,
    @SerializedName("publisher-id")
    var publisherId: String? = null,
    @SerializedName("published-at")
    var publishedAt: Long = 0,
    @SerializedName("storyline-id")
    var storyLineId: String? = null,
    @SerializedName("storyline-title")
    var storylineTitle: String? = null,
    @SerializedName("summary")
    var summary: String? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("hero-image-s3-key")
    var heroImageS3Key: String? = null,
    @SerializedName("cards")
    var cards: List<Card>? = emptyList(),
    @SerializedName("url")
    var storyUrl: String? = null,
    @SerializedName("story-version-id")
    var storyVersionId: String? = null,
    @SerializedName("author-name")
    var authorName: String? = null,
    @SerializedName("author-id")
    var authorId: String? = null,
    @SerializedName("owner-id")
    var ownerId: String? = null,
    @SerializedName("first-published-at")
    var firstPublishedAt: Long = 0,
    @SerializedName("hero-image-caption")
    var heroImageCaption: String? = null,
    @SerializedName("hero-image-attribution")
    var heroImageAttribution: String? = null,
    @SerializedName("version")
    var version: String? = null,
    @SerializedName("bullet-type")
    var bulletType: String? = null,
    @SerializedName("created-at")
    var createdAt: Long = 0,
    @SerializedName("assignee-id")
    var assigneeId: String? = null,
    @SerializedName("assignee-name")
    var assigneeName: String? = null,
    @SerializedName("hero-image-metadata")
    var heroImageMeta: ImageMetaData? = null,
    @SerializedName("story-template")
    var template: String? = null,
    @SerializedName("metadata")
    var storyMetaData: StoryMetaData? = null,
    @SerializedName("authors")
    var authors: List<Author>? = emptyList(),
    @SerializedName("alternative")
    var alternative: Alternative? = null,
    @SerializedName("linked-entities")
    var linkedEntities: @RawValue JsonArray? = null,
    @SerializedName("associated-series-collection-ids")
    var associatedSeriesCollectionIDs: List<String>? = null,
    @SerializedName("contributors")
    var contributors: List<ContributorModel>? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("is-live-blog")
    var isStoryLive: Boolean = false,
    var mMagazinePageNumber: Int = 1
) : Parcelable {
    /**
     * @return whether this story element is image type and gif
     */
    val isTypeImageGif: Boolean
        get() = if (!TextUtils.isEmpty(heroImageS3Key))
            heroImageS3Key!!.endsWith(".gif")
        else
            false


    /**
     * check whether the story's template is a video template
     *
     * @return true if the story's template is a video template, false otherwise
     */
    val isVideoTemplate: Boolean
        get() = TYPE_TEMPLATE_VIDEO.equals(template, ignoreCase = true)

    /**
     * check whether the story's template is a review template
     *
     * @return true if the story's template is a review template, false otherwise
     */
    val isReviewTemplate: Boolean
        get() = TYPE_TEMPLATE_REVIEW.equals(template, ignoreCase = true)

    /**
     * check whether the story's template is a live blog
     *
     * @return true if the story's template is a live blog, false otherwise
     */
    val isLiveBlogTemplate: Boolean
        get() = TYPE_TEMPLATE_LIVE_BLOG.equals(template, ignoreCase = true)

    /**
     * check whether the story's theme is a longform or parallax
     *
     * @return true if the story's theme is longform or parallax, false otherwise. As of now we
     * are considering both as a same. And looking only for the 1st item inside the 'theme' array.
     */
//    val isLongFormStory: Boolean
//        get() {
//            try {
//                val storyAttributes = storyMetaData!!.storyAttributes
//                if (storyAttributes!!.has(STORY_THEME)) {
//                    val themeArray = storyAttributes.getAsJsonArray(STORY_THEME)
//                    if (themeArray.size() > 0)
//                        if (themeArray.get(0) != null && themeArray.get(0).asString == STORY_THEME_LONGFORM || themeArray.get(
//                                0
//                            )
//                                .asString == STORY_THEME_PARALLAX
//                        ) {
//                            return true
//                        }
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//            return false
//        }

    /**
     * @return First youtube story element
     */
    val firstYoutubeElement: StoryElement?
        get() {
            for (card in cards!!) {
                val elem = card.firstYoutubeElement
                if (elem != null) {
                    return elem
                }
            }
            return null
        }

    /**
     * @return First youtube story element's url and will remove the same element from the card.
     */
    fun removeFirstYoutubeElement(): String {
        for (card in cards!!) {
            val youTubeURL = card.removeFirstYoutubeElement()
            if (!TextUtils.isEmpty(youTubeURL)) {
                return youTubeURL
            }
        }
        return ""
    }

    /**
     * @return story element for hero element
     */
    private val heroElement: StoryElement?
        get() {
            if (!TextUtils.isEmpty(this.heroImageS3Key)) {
                val heroElement = StoryElement()
                heroElement.imageS3Key = this.heroImageS3Key
                heroElement.id = StoryElement.ID_HERO
                heroElement.title = this.heroImageCaption
                heroElement.imageAttribution = this.heroImageAttribution
                heroElement.type = StoryElement.TYPE_STORY_ELEMENT_IMAGE
                return heroElement
            }
            return null
        }

    /**
     * @return list of all image elements along with a fake hero element
     */
    val imageElementsWithHeroElement: List<StoryElement>
        get() {
            val imageElements = ArrayList<StoryElement>()
            val heroCard = heroElement
            if (heroCard != null)
                imageElements.add(heroCard)
            if (cards != null) {
                for (card in cards!!) {
                    for (elem in card.storyElements()) {
                        if (elem.isTypeImage && !elem.isTypeImageGif) {
                            imageElements.add(elem)
                        }
                    }
                }
            }
            return imageElements
        }

    /**
     * @return a list of all image elements along with hero element and gif elements
     */
    val allImageElements: List<StoryElement>
        get() {
            val imageElements = ArrayList<StoryElement>()
            val heroCard = heroElement
            if (heroCard != null)
                imageElements.add(heroCard)
            if (cards != null) {
                for (card in cards!!) {
                    for (elem in card.storyElements()) {
                        if (elem.isTypeImage) {
                            imageElements.add(elem)
                        }
                    }
                }
            }
            return imageElements
        }

    val imageElementsInCards: List<StoryElement>
        get() {
            val imageElements = ArrayList<StoryElement>()
            if (cards != null) {
                for (card in cards!!) {
                    for (elem in card.storyElements()) {
                        if (elem.isTypeComposite && (elem.subTypeMeta() != null && elem
                                .subTypeMeta()!!.isTypeGallery() || elem.subTypeMeta()!!.isTypeSlideShow())
                        ) {
                            imageElements.addAll(elem.storyElements()!!)
                        }
                    }
                }
            }
            return imageElements
        }

    val slideshowImageElementsInCards: List<StoryElement>
        get() {
            val imageElements = ArrayList<StoryElement>()
            if (cards != null) {
                for (card in cards!!) {
                    for (elem in card.storyElements()) {
                        if (elem.isTypeComposite || elem.subTypeMeta()!!.isTypeSlideShow()) {
                            imageElements.addAll(elem.storyElements()!!)
                        }
                    }
                }
            }
            return imageElements
        }

    constructor(
        id: String?,
        slug: String?,
        heroImageS3Key: String?,
        headline: String?,
        publishedAt: Long?
    ) : this() {
        this.id = id
        this.slug = slug
        this.heroImageS3Key = heroImageS3Key
        this.headline = headline
        this.publishedAt = publishedAt!!
    }

    constructor(
        id: String?,
        slug: String?,
        heroImageS3Key: String?,
        headline: String?,
        publishedAt: Long?,
        authorName: String?
    ) : this() {
        this.id = id
        this.slug = slug
        this.heroImageS3Key = heroImageS3Key
        this.headline = headline
        this.publishedAt = publishedAt!!
        this.authorName = authorName
    }

    /**
     * Created this constructor for TheQuint's Explainer Story
     *
     * @param id
     * @param cards
     */
    constructor(id: String, cards: List<Card>) : this() {
        this.id = id
        this.cards = cards
    }

    fun cards(cards: List<Card>) {
        this.cards = cards
    }

    fun hasLinkedEntities(): Boolean {
        return linkedEntities != null
    }

    fun getLinkedStorySlug(storyId: String): String? {
        if (!TextUtils.isEmpty(storyId) && linkedStories != null && !linkedStories!!.isEmpty()) {
            val linkedStoryValue = linkedStories!![storyId]
            return linkedStoryValue?.slug
        } else {
            return null
        }
    }

    /**
     * @return Whether this story has story line id
     */
    fun hasStoryLineId(): Boolean {
        return !TextUtils.isEmpty(storyLineId)
    }

    /**
     * checks if all the fields of the story are present in the calling story object.
     *
     * @return if all fields are not present returns true other wise returns false
     */
    fun requiresFields(): Boolean {
        return cards!!.isEmpty()
    }

    /**
     * check if the story has tags.
     *
     * @return true if the story has tags false otherwise
     */
    fun hasTags(): Boolean {
        return tags != null && !tags!!.isEmpty()
    }

    /**
     * @return Id field of a story
     */
    fun id(): String? {
        return if (TextUtils.isEmpty(id)) {
            //Timber.d("Story id is empty, will fallback to content id");
            storyContentId
        } else {
            id
        }
    }

    companion object {
        val INVALID_ID = "-1"
        /**
         * method to get an instance of an invalid story
         *
         * @return - an invalid story instance
         */
        val invalidStory = Story()

        //Story Types
        val TYPE_TEMPLATE_LISTICLE = "listicle"
        val TYPE_TEMPLATE_VIDEO = "video"
        val TYPE_TEMPLATE_LIVE_BLOG = "live-blog"
        val TYPE_TEMPLATE_NEWS_ELSEWHERE = "news-elsewhere"
        val TYPE_BULLET_ASCENDING = "123"
        val TYPE_BULLET_DESCENDING = "321"
        val TYPE_BULLET_BULLETED = "bullets"
        val TYPE_TEMPLATE_REVIEW = "review"

        val STORY_THEME = "theme"
        val STORY_THEME_LONGFORM = "longform"
        val STORY_THEME_PARALLAX = "parallax"

        init {
            invalidStory.id = INVALID_ID
        }

        /**
         * checks if the story is invalid
         *
         * @param story - the story to be checked
         * @return true if invalid, false otherwise
         */
        fun isInvalid(story: Story): Boolean {
            return INVALID_ID == story.id
        }

        /**
         * a method to get a invalid story with the slug set so that the actual story can be queried
         * from its slug
         *
         * @param slug - the slug that needs to be set in the slug story
         * @return a new story object
         */
        fun getSlugStory(slug: String?): Story {
            val slugStory = invalidStory
            if (slug == null || slug.isEmpty()) {
                throw IllegalArgumentException("Slug cannot be null or empty when asking for a " + "slug story")
            }
            slugStory.slug = slug
            return slugStory
        }

        /**
         * checks if the story passed as a parameter is an invalid story but contains a slug from
         * which actual story can be
         * queried
         *
         * @param story - a reference to the story
         * @return true if the story is a slug story, false otherwise
         */
        fun isSlugStory(story: Story): Boolean {
            return !TextUtils.isEmpty(story.slug)
        }
    }
}