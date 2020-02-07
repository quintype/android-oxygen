package com.quintype.oxygen.models.storypresenter

import android.os.Parcelable
import android.view.ViewGroup
import com.google.gson.JsonObject
import com.quintype.oxygen.models.entities.EntityModel
import com.quintype.oxygen.models.story.Card
import com.quintype.oxygen.models.story.SortCards
import com.quintype.oxygen.models.story.Story
import com.quintype.oxygen.models.story.StoryElement
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * An object representing StoryPresenter
 *
 *
 * Use the presenter to load an adapter or bind views.
 * Embed views.
 */
@Parcelize
data class StoryPresenter(
    internal var story: Story? = null,
    var flattenedStoryElements: MutableList<StoryElement>? = ArrayList()
) : Parcelable {

    private var cardPositions: IntArray? = null
    internal var cardEntityMap: Map<Int, List<EntityModel>> = HashMap()
    internal var storyElementToCardMap: MutableMap<String, Card> = HashMap()
    /**
     * // Known view types while recreating
     * viewType = 1 // actual view type
     * (1 * 100) + 1 = 101 // view type given to recycler view
     * 101 % 100 = 1
     *
     *
     * // custom view types
     * 30 + 1 = 31
     */

    internal var recreateHolderList: MutableList<Int> = ArrayList()

    internal var callback: BinderCallback? = null
    internal var embedElementsMap = LinkedHashMap<Int, Class<*>>()

    /**
     * Get story elements count
     *
     * @return int total number of elements including custom inserted elements
     */
    val elementCount: Int
        get() = if (flattenedStoryElements == null) {
            0
        } else flattenedStoryElements!!.size


    /**
     * Default constructor for default story templates like 'text', 'photo', etc.
     * @param story
     * @param adsPositionArray {This array will give the array of card level index to insert ADs, since this is a Mutable we can pass null if we don't to show any ADs in card level.}
     */
    internal constructor(story: Story?, adsPositionArray: IntArray?) : this() {
        this.story = story
        val positionArray = IntArray(story?.cards!!.size)
        //Add story elements to elements list.
        for ((cardIndex, card) in story.cards!!.withIndex()) {
            card.buildUIStoryElements()
            //keep a track of where each card starts
            positionArray[cardIndex] = flattenedStoryElements!!.size
            //flatten the elements in this card
            for (elem in card.uiStoryElements) {
                flattenedStoryElements!!.add(elem)
                storyElementToCardMap[elem.id()!!] = card
            }

            /*Inserting Ads as element type on given positions*/
            if (adsPositionArray != null) {
                for (adPosition in adsPositionArray) {
                    if (cardIndex == adPosition) {
                        flattenedStoryElements?.add(StoryElement.newStoryElement(StoryElement.TYPE_ADS))
                    }
                }
            }
        }
        cardPositions = positionArray
    }

    /**
     * This constructor is created especially for TheQuint Publisher - LiveBlog Template
     * Conditions for LiveBlog :
     * 1. Sort all the cards based on cardAddedAt value.
     * 2. Look for summary element and move it to the top of the list.
     * 3. HardCode ElementViewType SORT_CARDS below summary element if summary element exists, else SORT_CARDS will be on the top of the list
     *
     * @param story
     * @param newestFirst {By Default the order should be Newest first}
     * @param adsPositionArray {This array will give the array of card level index to insert ADs, since this is a Mutable we can pass null if we don't to show any ADs in card level.}
     */
    internal constructor(story: Story?, newestFirst: Boolean, adsPositionArray: IntArray?) : this() {
        this.story = story
        val positionArray = IntArray(story?.cards!!.size)

        //Hardcoding ElementViewType SORT_CARDS to the top
        flattenedStoryElements!!.add(0, StoryElement.newStoryElement(StoryElement.TYPE_SORT_CARDS))

        val cardList = story.cards
        if (!newestFirst)
            Collections.sort(
                cardList,
                SortCards()
            )// Collections sort will always give list in ascending order which is oldest first.
        else
            Collections.sort(cardList, Collections.reverseOrder(SortCards()))

        for ((cardIndex, card) in story.cards!!.withIndex()) {
            card.buildUIStoryElements()
            //keep a track of where each card starts
            positionArray[cardIndex] = flattenedStoryElements!!.size

            /*If the card is pinned move the whole card to the top. The pinned card expected to have summary element and displayed about ElementViewType SORT_CARDS*/
            if (card.metadata.attributes() != null) {
                val attributeJsonObj = card.metadata.attributes()
                if (attributeJsonObj!!.has("is-pinned?")) {
                    val isCardPinned = attributeJsonObj.get("is-pinned?").asBoolean
                    if (isCardPinned) {
                        //Timber.d("This card is pinned(positive), add(hardcode) all the story elements to the top of the story(flattenedStoryElements).");
                        val pinnedCardStoryElements = card.uiStoryElements
                        /*Lopping it in a reverse order, because we are hardcoding the insert position as '0' always*/
                        for (i in pinnedCardStoryElements.size downTo 1) {
                            val storyElement = pinnedCardStoryElements[i - 1]
                            storyElement.setCardPinStatus(true)
                            flattenedStoryElements!!.add(0, storyElement)
                        }
                    } else {
                        //Timber.d("This card is not pinned(negative), add story elements in the respective positions.");
                        addLiveBlogStoryElements(card, attributeJsonObj)
                    }
                } else {
                    //Timber.d("There is no card metadata'is-pinned',This card is not pinned, add story elements in the respective positions.");
                    addLiveBlogStoryElements(card, attributeJsonObj)
                }
            } else {
                //Timber.d("Card metadata is null,add story elements in the respective positions.");
                addLiveBlogStoryElements(card, null)
            }

            /*Inserting Ads as element type on given positions*/
            if (adsPositionArray != null) {
                for (adPosition in adsPositionArray) {
                    if (cardIndex == adPosition) {
                        flattenedStoryElements?.add(StoryElement.newStoryElement(StoryElement.TYPE_ADS))
                    }
                }
            }
        }
        cardPositions = positionArray
    }

    /**
     * This constructor is created for Default LiveBlog and Listicle story templates
     * Conditions for LiveBlog - No any conditions, just follow the API order.
     * Conditions for Listing - Look for the first image element inside a card and move that image to the top of the card.
     *
     * @param story
     * @param storyType {This is story Template basically}
     * @param adsPositionArray {This array will give the array of card level index to insert ADs, since this is a Mutable we can pass null if we don't to show any ADs in card level.}
     */
    internal constructor(story: Story?, storyType: String, adsPositionArray: IntArray?) : this() {
        this.story = story
        val positionArray = IntArray(story?.cards!!.size)
        val cardList = story.cards

        /*For Listicle story we have a bulletin value, which determines the order of the cards.
        If the value is '321' then we need to reverse the cards, if it is '123' no need to do anything.
        Assuming we always get the intro card as the first card and we should not change the intro cards order.*/

        /* if (storyType.equals(STORY_TEMPLATE_LISTICLE) && (!TextUtils.isEmpty(story.bulletType)) && story.bulletType.equals(LISTICLE_BULLETIN_321)) {
            List<Card> cardsCopy = cardList;
            *//*Before reversing the cards list exclude the intro card.*//*
            Card introCard = cardList.get(0);
            cardsCopy.remove(0);

            Collections.reverse(cardsCopy);
            cardsCopy.add(0, introCard);
            cardList = cardsCopy;
        }*/

        for ((cardIndex, i) in cardList!!.indices.withIndex()) {
            val card = cardList[i]
            //for (Card card : story.cards()) {
            card.buildUIStoryElements()
            //keep a track of where each card starts
            positionArray[cardIndex] = flattenedStoryElements!!.size

            if (STORY_TEMPLATE_DEFAULT_LIVE_BLOG == storyType) {
                addLiveBlogStoryElements(card, card.metadata.attributes())
            } else if (STORY_TEMPLATE_LISTICLE == storyType && LISTICLE_BULLETIN_123 == story.bulletType)
                addListicleStoryElement(
                    card,
                    i
                )/*No need to do ant thing just send the position as it is.*/
            else if (STORY_TEMPLATE_LISTICLE == storyType && LISTICLE_BULLETIN_321 == story.bulletType) {
                /*If the bulletin is in reverse order the position also as to be in reverse.*/
                /*Ignore the first item, we are assuming first item will always be the intro card.*/
                if (i == 0)
                    addListicleStoryElement(card, i)
                else
                    addListicleStoryElement(card, cardList.size - i)
            } else if (STORY_TEMPLATE_LISTICLE == storyType && LISTICLE_BULLETIN_BULLETS == story.bulletType)
                addListicleStoryElement(
                    card,
                    0
                )/*No need to do ant thing, no need of positions also.*/

            /*Inserting Ads as element type on given positions*/
            if (adsPositionArray != null) {
                for (adPosition in adsPositionArray) {
                    if (cardIndex == adPosition) {
                        flattenedStoryElements?.add(StoryElement.newStoryElement(StoryElement.TYPE_ADS))
                    }
                }
            }
        }
        cardPositions = positionArray
    }

    /**
     * @return an array of the positions of the first story element in each card
     */
    fun cardPositions(): IntArray {
        return cardPositions!!
    }


    fun getStoryElementToCardMap(): Map<String, Card> {
        return storyElementToCardMap
    }

    private fun addLiveBlogStoryElements(card: Card, attributeJsonObj: JsonObject?) {
        // Creating the new StoryElement of type TYPE_LIVE_BLOG_ADDED_AT_TIME_STAMP.
        val timeStampStoryElement =
            StoryElement.newStoryElement(StoryElement.TYPE_LIVE_BLOG_ADDED_AT_TIME_STAMP)
        timeStampStoryElement.cardAddedAt = card.cardAddedAt

        if (attributeJsonObj != null && attributeJsonObj.has("key-event")) {
            timeStampStoryElement.isKeyEvent = attributeJsonObj.get("key-event").asBoolean
        }

        val storyElements = card.uiStoryElements
        for ((storyElementIndex, elem) in storyElements.withIndex()) {
            // If the Story template type is LIVE BLOG, and if the StoryElement is in the 1st position of the card then Add(Hardcode) TimeStamp element
            // before adding the normal StoryElement
            if (!elem.isTypeSummary && storyElementIndex == 0) {
                flattenedStoryElements!!.add(timeStampStoryElement)
            }
            flattenedStoryElements!!.add(elem)
        }

        /*Adding card share widget at the end of card if card social share is enabled. This is the new feature added for prothomalo.
         Note: Adding this by default because for other publishers if they check for this type then only it will be visible. */
        if (card.metadata.socialShare?.shareable!!) {
            val cardSocialShareElement = StoryElement.newStoryElement(StoryElement.TYPE_CARD_SOCIAL_SHARE)
            cardSocialShareElement.cardID = card.id
            cardSocialShareElement.cardSocialShareTitle = card.metadata.socialShare?.socialShareTitle
            flattenedStoryElements!!.add(cardSocialShareElement)
        }
    }

    /**
     * For Listicle story inside cards the storyElements order will be mostly like 1.Title, 2.Image, & 3.Text(Description).
     * But as per the design we need to show the image first, assuming all the cards will have one image at-least.
     * Look for the first image element inside a card and move that image to the top of the card.
     *
     * @param card
     * @param cardPosition
     */
    private fun addListicleStoryElement(card: Card, cardPosition: Int) {
        var isImageReplaced = false
        val lastKnownSize =
            flattenedStoryElements!!.size// Size of the flattenedStoryElements List before adding this card elements.
        val storyElements = card.uiStoryElements
        for (elem in storyElements) {
            if ((elem.isTypeImage || elem.isTypeImageGif) && !isImageReplaced) {
                elem.listicleCardCount = cardPosition// Assuming we always get the Intro card.
                flattenedStoryElements!!.add(
                    lastKnownSize,
                    elem
                ) // Assuming we always get the Intro card.
                isImageReplaced = true
            } else
                flattenedStoryElements!!.add(elem)
        }
    }

    /**
     * Set [BinderCallback] for unknown StoryElement types
     *
     * @param callback [BinderCallback]
     */
    fun binderCallback(callback: BinderCallback): StoryPresenter {
        this.callback = callback
        return this
    }

    /**
     * Insert a custom element into a Story.
     *
     *
     * for instance an Ad.
     *
     *
     * Class type is used in [BinderCallback] to get the Binder for the position.
     *
     * @param element    [StoryElement] to be inserted
     * @param atPosition position to insert the story element.
     * @param cls        [StoryElementBinder] class type of the Binder.
     */
    fun insertElement(element: StoryElement?, atPosition: Int, cls: Class<*>?) {
        var atPosition = atPosition
        if (element != null && cls != null) {
            if (atPosition >= flattenedStoryElements!!.size) {
                //add element at last if the position is greater than the size itself.
                atPosition = flattenedStoryElements!!.size
                flattenedStoryElements!!.add(element)
                embedElementsMap[atPosition] = cls
            } else {
                //insert the element at a position
                flattenedStoryElements!!.add(atPosition, element)

                //increment all the keys after the position the element was added.
                val tempMap = LinkedHashMap<Int, Class<*>>()
                tempMap[atPosition] = cls

                for (key in embedElementsMap.keys) {
                    val c = embedElementsMap[key]
                    if (key > atPosition) {
                        c?.let { tempMap.put(key + 1, it) }
                    } else {
                        c?.let { tempMap.put(key, it) }
                    }
                }
                embedElementsMap = tempMap
            }

        } else {
            throw IllegalArgumentException("StoryElement or StoryElementBinder type is null")
        }
    }

    /**
     * Insert custom StoryElement Binder with a dummy StoryElement
     *
     * @param atPosition position to insert the story element.
     * @param cls        [StoryElementBinder] class type of the Binder.
     */
    fun insertElementBinder(atPosition: Int, cls: Class<*>) {
        insertElement(StoryElement.dummyElement(), atPosition, cls)
    }

    /**
     * Insert custom StoryElement Binder with a icon title StoryElement
     *
     * @param atPosition position to insert the story element.
     * @param cls        [StoryElementBinder] class type of the Binder.
     */
    fun insertIconWithTitleBinder(atPosition: Int, cls: Class<*>) {
        insertElement(StoryElement.iconWithRightTitleElement(), atPosition, cls)
    }

    /**
     * Insert custom StoryElement Binder with a sharewidget StoryElement
     *
     * @param atPosition position to insert the story element.
     * @param cls        [StoryElementBinder] class type of the Binder.
     */
    fun insertShareWidgetBinder(atPosition: Int, cls: Class<*>) {
        insertElement(StoryElement.shareWidgetElement(), atPosition, cls)
    }

    /**
     * Get element ViewType [ElementViewType]
     *
     * @param position int position of a StoryElement in the story
     * @return A custom viewType as int or [ElementViewType]
     */
    fun getElementViewType(position: Int): Int {
        val viewType: Int
        if (position >= flattenedStoryElements!!.size || position < 0) {
            //No Elements
            viewType = ElementViewType.UNKNOWN
        } /*else if (embedElementsMap.containsKey(position)&&
                !embedElementsMap.containsKey(ElementViewType.ICON_WITH_TITLE)) {//skip for the icon with title
            //Contains custom elements
            viewType = ElementViewType.UNKNOWN + position + 1;
        } */
        else {
            //Known element types.
            val element = flattenedStoryElements!![position]
            if (element.isTypeImage) {
                viewType = ElementViewType.IMAGE
            } else if (element.isTypeYoutube) {
                viewType = ElementViewType.YOUTUBE
            } else if (element.isTypeBitGravity) {
                viewType = ElementViewType.BIT_GRAVITY_VIDEO
            } else if (element.isTypeBrightCoveVideo) {
                viewType = ElementViewType.BRIGHTCOVE_VIDEO
            } else if (element.isTypeSoundcloud) {
                viewType = ElementViewType.SOUND_CLOUD
            } else if (element.isTypeAudio) {
                viewType = ElementViewType.AUDIO
            } else if (element.isImageGallery) {
                viewType = ElementViewType.GALLERY
            } else if (element.isTypeBigFact) {
                viewType = ElementViewType.BIG_FACT
            } else if (element.isTypeBlurb) {
                viewType = ElementViewType.BLURB
            } else if (element.isTypeBlockQuote) {
                viewType = ElementViewType.BLOCK_QUOTE
            } else if (element.isTypeQuestionAnswer) {
                viewType = ElementViewType.QUESTION_ANSWER
            } else if (element.isTypeVideo) {
                viewType = ElementViewType.VIDEO
            } else if (element.isTypeQuote) {
                viewType = ElementViewType.QUOTE
            } else if (element.isImageSlideshow) {
                viewType = ElementViewType.SLIDESHOW
            } else if (element.isTypeImageGif) {
                viewType = ElementViewType.GIF
            } else if (element.isTypePollType) {
                viewType = ElementViewType.POLLTYPE
            } else if (element.isTypeJsEmbedWithTwitter) {
                viewType = ElementViewType.TWEET
            } else if (element.isTypeJsembed) {
                viewType = ElementViewType.JS_EMBED
            } else if (element.isTypeMedia) {
                viewType = ElementViewType.MEDIA
            } else if (element.isTypeSummary) {
                viewType = ElementViewType.SUMMARY
            } else if (element.isTypeTitle) {
                viewType = ElementViewType.TITLE
            } else if (element.isAlsoRead) {
                viewType = ElementViewType.ALSO_READ
            } else if (element.isTypeQuestion) {
                viewType = ElementViewType.QUESTION
            } else if (element.isTypeAnswer) {
                viewType = ElementViewType.ANSWER
            } else if (element.isTypeText) {
                viewType = ElementViewType.TEXT
            } else if (element.isTypeTable) {
                viewType = ElementViewType.TABLE
            } else if (element.isTypeTimeStamp) {
                viewType = ElementViewType.TIME_STAMP
            } else if (element.isTypeSortCards) {
                viewType = ElementViewType.SORT_CARDS
            } else if (element.isTypeNestedStory) {
                viewType = ElementViewType.NESTED_STORY
            } else if (element.isAttachment) {
                viewType = ElementViewType.ATTACHMENT
            } else if (element.isReferences) {
                viewType = ElementViewType.REFERENCE
            } else if (element.isIconWithTitle) {
                viewType = ElementViewType.ICON_WITH_TITLE
            } else if (element.isSocialShareWidget) {
                viewType = ElementViewType.SOCIAL_SHARE_WIDGET
            } else if (element.isTypeCardSocialShare) {
                viewType = ElementViewType.CARD_SHARE_WIDGET
            } else if (element.isAdType) {
                viewType = ElementViewType.ADS
            } else {
                viewType = ElementViewType.UNKNOWN
            }
        }

        return if (recreateHolderList.contains(viewType)) {
            //If recreate view holders
            INCREMENTAL_BASE_VIEW_TYPE * viewType + position
        } else {
            viewType
        }

    }

    /**
     * Get Story element at position from the list of story elements in a story
     *
     * @param position int position for Story Element
     * @return [StoryElement]
     */
    fun getElement(position: Int): StoryElement {
        return flattenedStoryElements!![position]
    }

    @ElementViewType
    private fun getInternalElementType(type: Int): Int {
        return ElementViewType.Process.getType(type)
    }

    /**
     * Get
     * [StoryElementBinder] for viewtype. It can be custom viewtype or [ElementViewType]
     *
     * @param parent   [ViewGroup]
     * @param viewType A custom viewtype or [ElementViewType]
     * @return [StoryElementBinder]
     */
    fun getElementBinderForViewType(parent: ViewGroup, viewType: Int): StoryElementBinder {
        if (callback != null) {
            var type = viewType
            val binder: StoryElementBinder
            if (viewType > ElementViewType.UNKNOWN && viewType < INCREMENTAL_BASE_VIEW_TYPE) {
                //get binder for custom element inserted
                val position = viewType - ElementViewType.UNKNOWN - 1
                binder = callback!!.onCreateElementBinder(
                    parent,
                    embedElementsMap[position] as Class<out StoryElementBinder>?
                )
            } else if (viewType > INCREMENTAL_BASE_VIEW_TYPE) {
                type = viewType / INCREMENTAL_BASE_VIEW_TYPE//(viewType - position) /
                // INCREMENTAL_BASE_VIEW_TYPE;
                binder = getElementBinderForViewType(parent, type)
            } else {
                //get binder for known view types.
                binder = callback!!.onCreateElementBinder(
                    parent,
                    callback!!.onRegisterViewTypeClass(getInternalElementType(type))
                )
            }
            if (binder.recreate()) {
                recreateHolderList.add(type)
            }
            return binder
        } else {
            throw RuntimeException("Callback is not set")
        }
    }

    /**
     * Bind StoryElementBinder with the view.
     *
     * @param binder   [StoryElementBinder]
     * @param position int position on RecyclerView
     */
    fun bind(binder: StoryElementBinder, position: Int) {
        binder.bind(flattenedStoryElements!![position])
    }

    fun story(): Story? {
        return story
    }

    fun updateVideoStoryElementList() {
        if (story != null && story!!.template!!.equals(
                "VIDEO",
                ignoreCase = true
            ) && flattenedStoryElements != null && !flattenedStoryElements!!.isEmpty()
        ) {
            val modifiedStoryElement = ArrayList(flattenedStoryElements!!)

            for (storyElement in flattenedStoryElements!!) {
                if (storyElement.isTypeVideo || storyElement.isTypeYoutube) {
                    modifiedStoryElement.removeAt(flattenedStoryElements!!.lastIndexOf(storyElement))
                    modifiedStoryElement.add(0, storyElement)

                    flattenedStoryElements = modifiedStoryElement
                    break
                }
            }
        }
    }

    fun updatePhotoStoryElementList() {
        if (story != null && story!!.template!!.equals(
                "PHOTO",
                ignoreCase = true
            ) && flattenedStoryElements != null && !flattenedStoryElements!!.isEmpty()
        ) {
            val modifiedStoryElement = ArrayList(flattenedStoryElements!!)

            for (storyElement in flattenedStoryElements!!) {
                /*If Story element of type composite and subtype 'slide-show' or 'gallery', then move that element to the top. References is also of type composite which we should not consider. */
                if (storyElement.isTypeComposite && !storyElement.isReferences) {
                    modifiedStoryElement.removeAt(flattenedStoryElements!!.lastIndexOf(storyElement))
                    modifiedStoryElement.add(0, storyElement)

                    flattenedStoryElements = modifiedStoryElement
                    break
                }
            }
        }
    }

    fun flattenedStoryElementList(): List<StoryElement>? {
        return flattenedStoryElements
    }

    /**
     * Callback to register elements types with [StoryElementBinder]
     */
    interface BinderCallback {
        fun onRegisterViewTypeClass(@ElementViewType viewType: Int): Class<out StoryElementBinder>

        fun onCreateElementBinder(
            parent: ViewGroup,
            cls: Class<out StoryElementBinder>?
        ): StoryElementBinder
    }

    companion object {
        val LISTICLE_BULLETIN_321 = "321"
        val LISTICLE_BULLETIN_123 = "123"
        val LISTICLE_BULLETIN_BULLETS = "bullets"
        val STORY_TEMPLATE_DEFAULT_LIVE_BLOG = "default_live_blog_story"
        val STORY_TEMPLATE_LISTICLE = "listicle_story"

        //base view type to increment custom view types
        internal val INCREMENTAL_BASE_VIEW_TYPE = 1000

        /**
         * Create StoryPresenter
         *
         *
         * Setup StoryElements of the Story
         *
         * @param story [Story]
         * @return [StoryPresenter]
         * @param adsPositionArray {This array will give the array of card level index to insert ADs, since this is a Mutable we can pass null if we don't to show any ADs in card level.}
         */
        fun createDefaultStoryPresenter(story: Story?, adsPositionArray: IntArray?): StoryPresenter {
            return StoryPresenter(story, adsPositionArray)
        }

        /**
         * Create StoryPresenter
         *
         *
         * Setup StoryElements of the Story
         *
         * @param story       [Story]
         * @param newestFirst {Story cards sorting order, can be either OldestFirst 'FALSE' or NewestFirst'TRUE' }
         * @param adsPositionArray {This array will give the array of card level index to insert ADs, since this is a Mutable we can pass null if we don't to show any ADs in card level.}
         * @return [StoryPresenter]
         */
        fun createTheQuintLiveBlogStoryPresenter(
            story: Story?,
            newestFirst: Boolean,
            adsPositionArray: IntArray?
        ): StoryPresenter {
            return StoryPresenter(story, newestFirst, adsPositionArray)
        }

        /**
         * Create StoryPresenter
         *
         *
         * Setup StoryElements of the Story
         *
         * @param story     [Story]
         * @param storyType {This is story Template basically }
         * @param adsPositionArray {This array will give the array of card level index to insert ADs, since this is a Mutable we can pass null if we don't to show any ADs in card level.}
         * @return [StoryPresenter]
         */
        fun createTemplateStoryPresenter(story: Story?, storyType: String, adsPositionArray: IntArray?): StoryPresenter {
            return StoryPresenter(story, storyType, adsPositionArray)
        }
    }
}
