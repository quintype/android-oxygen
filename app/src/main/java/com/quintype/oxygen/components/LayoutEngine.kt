package com.quintype.oxygen.components

import com.quintype.oxygen.TYPE_COLLECTION
import com.quintype.oxygen.models.CollectionAdapterItem
import com.quintype.oxygen.models.latest.home.ChildItem
import com.quintype.prothomalo.utils.IS_ADS_REQUIRED

class LayoutEngine {

    private val mComponentImpl = ComponentImpl()

    @Synchronized
    fun getComponents(parentItem: ChildItem): ArrayList<CollectionAdapterItem> {
        val assosicatedMetadata = parentItem.associatedMetadata
        val mComponent = TemplateComponent.getTemplateComponent(assosicatedMetadata)
        val collectionInnerListModelList = ArrayList<CollectionAdapterItem>()
        if (TYPE_COLLECTION.equals(parentItem.type)) {
            when (mComponent) {
                TemplateComponent.TYPE_MAIN_ROW,

                TemplateComponent.TYPE_COPONENT_4S -> {
                    if (parentItem.grandChildItem!!.size > 1) {
                        parentItem.grandChildItem!!.add(1, ChildItem())
                    }
                    mComponentImpl.prepareComponentEight(collectionInnerListModelList, parentItem)
                }
                TemplateComponent.TYPE_4S_HALF_FEATURED_NEW -> {
                    mComponentImpl.prepareStoriesHalfFeatured(collectionInnerListModelList, parentItem)
                }
                TemplateComponent.TYPE_HOME_TOP_SLIDER,
                TemplateComponent.TYPE_SLIDER_FOCUS_CARD,
                TemplateComponent.TYPE_SLIDER_ONE_STORY,
                TemplateComponent.TYPE_3S_SLIDER -> {
                    mComponentImpl.prepareSliderStories(collectionInnerListModelList, parentItem)
                }
                TemplateComponent.TYPE_5S_1AD,
                TemplateComponent.TYPE_7S_1AD,
                TemplateComponent.TYPE_12S_1AD_NEW,
                TemplateComponent.TYPE_19S_1AD_NEW -> {
                    mComponentImpl.prepareStoriesWithAd(collectionInnerListModelList, parentItem)
                }
                TemplateComponent.TYPE_4S_PHOTO_GALLERY_NEW -> {
                    if (parentItem.grandChildItem!!.size > 1) {
                        parentItem.grandChildItem!!.add(1, ChildItem())
                    }
                    mComponentImpl.prepareMediaStories(collectionInnerListModelList, parentItem)
                }

                /**
                 * Component Row 01
                 */
                TemplateComponent.TYPE_14S_3AD_1WIDGET,

                TemplateComponent.TYPE_6S_1AD_1WIDGET,
                TemplateComponent.TYPE_12S_1AD_1WIDGET_NEW,
                TemplateComponent.TYPE_CAROUSEL_2S_1Ad_1WIDGET -> {
                    mComponentImpl.prepareComponentOne(collectionInnerListModelList, parentItem)
                }

                /**
                 * Component Row 02
                 */
                TemplateComponent.TYPE_4C_12S_1AD,
                TemplateComponent.TYPE_4C_12S,
                TemplateComponent.TYPE_3C_15S_1AD_1POLL,

                TemplateComponent.TYPE_16S_4C_NEW,
                TemplateComponent.TYPE_12S_4C -> {
                    mComponentImpl.prepareComponentTwo(collectionInnerListModelList, parentItem)
                }

                /**
                 * Component Row 03
                 */
                TemplateComponent.TYPE_6S_1WIDGET_1POLL -> {
                    mComponentImpl.prepareComponentThree(collectionInnerListModelList, parentItem)
                }

                /**
                 * Component Row 04
                 */
                TemplateComponent.TYPE_6S_1AD_TAG_SEARCH -> {
                    mComponentImpl.prepareComponentFour(collectionInnerListModelList, parentItem)
                }

                /**
                 * Component Row 05
                 */
                TemplateComponent.TYPE_5_VIDEO_STORY,
                TemplateComponent.TYPE_PHOTO_STORY_SLIDER,
                TemplateComponent.TYPE_4S_CARDS -> {
                    mComponentImpl.prepareComponentFive(collectionInnerListModelList, parentItem)
                }

                /**
                 * Component Row 06
                 */
                TemplateComponent.TYPE_4S_HALF_FEATURED,
                TemplateComponent.TYPE_9S -> {
                    mComponentImpl.prepareComponentSix(collectionInnerListModelList, parentItem)
                }

                /**
                 * Component Row 07
                 */
                TemplateComponent.TYPE_5_OPINION_STORY -> {
                    mComponentImpl.prepareComponentSeven(collectionInnerListModelList, parentItem)
                }

                /**
                 * Component Row 08
                 */
                TemplateComponent.TYPE_SLIDER_FOCUSED_CARD -> {
                    mComponentImpl.prepareComponentEight(collectionInnerListModelList, parentItem)
                }

                /**
                 * Component Row 09
                 */
                TemplateComponent.TYPE_4S -> {
                    mComponentImpl.prepareComponentNine(collectionInnerListModelList, parentItem)
                }

                TemplateComponent.TYPE_BREAKING_NEWS -> {
                    mComponentImpl.prepareBreakingNews(collectionInnerListModelList, parentItem)
                }

                else -> {
                    mComponentImpl.prepareComponentTen(collectionInnerListModelList, parentItem)
                }
            }
            if (IS_ADS_REQUIRED) {
                mComponentImpl.prepareWidget(collectionInnerListModelList, parentItem)
            }
        } else {
            //prepare a single story item
            mComponentImpl.prepareSingleStory(collectionInnerListModelList, parentItem)
        }

        return collectionInnerListModelList
    }

}