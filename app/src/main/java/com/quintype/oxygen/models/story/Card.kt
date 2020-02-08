package com.quintype.oxygen.models.story

import android.os.Parcelable
import android.text.TextUtils
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.jsoup.Jsoup

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */
@Parcelize
data class Card(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("content-id")
    var contentId: String? = null,
    @SerializedName("story-elements")
    var storyElements: List<StoryElement> = emptyList(),
    @SerializedName("status")
    val status: String,
    @SerializedName("content-version-id")
    var contentVersionId: String? = null,
    @SerializedName("version")
    val version: String,
    @SerializedName("index")
    val index: Int,
    @SerializedName("totalCards")
    val totalCards: Int,
    @SerializedName("card-updated-at")
    var cardUpdatedAt: Long = 0,
    @SerializedName("card-added-at")
    var cardAddedAt: Long = 0,
    @SerializedName("metadata")
    val metadata: CardMetadata,
    var uiStoryElements: ArrayList<StoryElement>
) : Parcelable {
    /**
     * takes the default story elements and builds them to a new list of story elements for ui
     * purposes
     */
    fun buildUIStoryElements() {
        uiStoryElements = ArrayList()

        storyElements.forEachIndexed { index, storyElement ->
            if (storyElement.isTypeText && !storyElement.isTypeQuote
                && !storyElement.isTypeBlockQuote && !storyElement.isTypeBlurb
            ) {
                val htmlDoc = Jsoup.parse(storyElement.text())
                htmlDoc.outputSettings().prettyPrint(false).indentAmount(0)
                val blockQuoteElements = htmlDoc.select("blockquote")
                if (!blockQuoteElements.isEmpty()) {
                    val breakUpStoryElement = StoryElement.fromStoryElement(storyElement)
                    for (blockQuote in blockQuoteElements) {
                        val blockQuoteStoryElement =
                            StoryElement.fromStoryElement(breakUpStoryElement)
                        blockQuoteStoryElement.setTypeAsQuote()
                        blockQuoteStoryElement.text = blockQuote.html()

                        val textToBeRemoved = blockQuote.outerHtml()
                        val textRemovalIndex = breakUpStoryElement.text().indexOf(textToBeRemoved)
                        if (textRemovalIndex >= 0) {
                            val textBeforeQuoteStoryElement =
                                StoryElement.fromStoryElement(breakUpStoryElement)
                            textBeforeQuoteStoryElement.text = breakUpStoryElement.text()
                                .substring(
                                    0,
                                    breakUpStoryElement.text().indexOf(textToBeRemoved)
                                )
                            breakUpStoryElement.text = breakUpStoryElement.text().replace(
                                textBeforeQuoteStoryElement.text() + textToBeRemoved,
                                ""
                            )

                            if (!textBeforeQuoteStoryElement.text().isEmpty()) {
                                uiStoryElements.add(textBeforeQuoteStoryElement)
                            }
                            uiStoryElements.add(blockQuoteStoryElement)
                        } else {
                            //something is wrong with the index
                            break
                        }
                    }

                    if (!breakUpStoryElement.text().isEmpty()) {
                        uiStoryElements.add(breakUpStoryElement)
                    }

                } else {
                    if (!TextUtils.isEmpty(storyElement.text())) {
                        uiStoryElements.add(storyElement)
                    }
                }
            } else {
                if (storyElement.isTypeJsembed) {
                    storyElement.prepareForTwitter()
                }
                uiStoryElements.add(storyElement)
            }
        }
    }


    /**
     * @return First youtube element in this card
     */
    val firstYoutubeElement: StoryElement?
        get() {
            for (elem in storyElements) {
                if ("youtube-video".equals(elem.type(), ignoreCase = true)) {
                    return elem
                }
            }
            return null
        }

    /**
     * @return First youtube story element's url and will remove the same element from the card.
     */
    fun removeFirstYoutubeElement(): String {
        val mutableStoryElements = ArrayList(storyElements)

        for (elem in storyElements as MutableList) {
            if ("youtube-video".equals(elem.type(), ignoreCase = true)) {
                val youtubeURL = elem.url()!!
                mutableStoryElements.removeAt(storyElements.lastIndexOf(elem))
                storyElements = mutableStoryElements
                return youtubeURL
            }
        }
        return ""
    }

    fun storyElements(): List<StoryElement> {
        return storyElements
    }
}
