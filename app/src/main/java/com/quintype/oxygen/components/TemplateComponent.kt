package com.quintype.oxygen.components

import com.quintype.oxygen.models.collection.AssociatedMetadata
import java.util.*

enum class TemplateComponent(val layoutName: String) {
    TYPE_DEFAULT_COMPONENT("default-fallback"),//default component if nothing is matched to remote name.
    TYPE_BREAKING_NEWS("breaking-news"),

    TYPE_HOME_TOP_SLIDER("SliderCollectionOfCollections"),
    TYPE_MAIN_ROW("MainRow"),
    TYPE_SLIDER_ONE_STORY("SliderOneStory"),
    TYPE_CAROUSEL_2S_1Ad_1WIDGET("OneCarouselTwoStoriesOneAdOneWidget"),
    TYPE_3S_SLIDER("ThreeStoryAutoSlider"),
    TYPE_COPONENT_4S("FourStory4s"),
    TYPE_4S_HALF_FEATURED_NEW("FourStoryHalfFeatured"),
    TYPE_4S_PHOTO_GALLERY_NEW("FourStoryPhotoGallery"),
    TYPE_SLIDER_FOCUS_CARD("SliderFocusedCard"),
    TYPE_5S_1AD("FiveStoryOneAd"),
    TYPE_6S_1AD_1WIDGET("SixStoryOneAdOneWidget"),
    TYPE_7S_1AD("SevenStoriesOneAd"),
    TYPE_7S("SevenStory7s"),
    TYPE_12S_1AD_NEW("TwelveStoriesOneAd"),
    TYPE_12S_1AD_1WIDGET_NEW("TwelveStoriesOneAdOneWidget"),
    TYPE_16S_4C_NEW("SixteenStory4C"),
    TYPE_19S_1AD_NEW("NineteenStoriesOneAd"),
    TYPE_12S_4C("TwentyStory4C"),

    TYPE_14S_3AD_1WIDGET("FourteenStories3Ad1Widget"), //14 stories with 3 Ads and 1 Widget // Row 01
    TYPE_4C_12S_1AD("FourCollection12Stories1Ad"), //4 collections where 3 collections show tab stories and 1 collections show stories with 1 ad //Row 02
    TYPE_4C_12S("FourCollection12Stories"),//4 collections, 3 stories each // Row 02
    TYPE_3C_15S_1AD_1POLL("ThreeCollection15Stories1AdWithPoll"),//10 Stories, 2 Sub Collections with 5 stories each, Polling // Row 02
    TYPE_6S_1WIDGET_1POLL("SixStories1WidgetWithPoll"), //6 stories, 1 Widget and Poll Story // Row 03
    TYPE_6S_1AD_TAG_SEARCH("SixStories1AdWithTagSearch"),//6 stories, 1 Ad, 1 tag filtered search // Row 04
    TYPE_4S_CARDS("FourStoryCards"),//four stories with overlay headline // Row 05
    TYPE_5_VIDEO_STORY("FiveVideoStories"),//video stories // Row 05
    TYPE_PHOTO_STORY_SLIDER("PhotoStoryWithSlider"), //4 Photo Story Desktop and Slider in Mobile // Row 05
    TYPE_4S_HALF_FEATURED("FourStoryHalfFeatured"), //4 Story Half Featured // Row 06
    TYPE_9S("NineStories"), //9 stories where 5 small story cards on 2 columns and 1 big story card // Row 06
    TYPE_5_OPINION_STORY("FiveOpinionStories"), //5 opinion stories // Row 07
    TYPE_SLIDER_FOCUSED_CARD("SliderFocusedCard"), //Slider Focused Card // Row 08
    TYPE_4S("FourStories"), //4 Story with 1 Big and Three Small // Row 09
    ;

    companion object {
        private val mComponentMap = HashMap<String, TemplateComponent>()

        init {
            values().forEach { constant ->
                mComponentMap.put(constant.layoutName, constant)
            }
        }

        @Synchronized
        fun getTemplateComponent(metadata: AssociatedMetadata?): TemplateComponent? {
            return mComponentMap[metadata?.layout]
                ?: //return fallback type if type is null
                return TYPE_DEFAULT_COMPONENT
        }
    }
}