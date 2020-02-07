package com.quintype.oxygen.models.storypresenter

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.SOURCE)
annotation class ElementViewType {

    class Process {
        companion object {
            internal fun getType(type: Int): Int {
                return type
            }
        }
    }

    companion object {
        val TEXT = 1
        val IMAGE = 2
        val YOUTUBE = 3
        val SOUND_CLOUD = 4
        val TITLE = 5
        val SUMMARY = 6
        val QUOTE = 7
        val AUDIO = 8
        val GIF = 9
        val BLURB = 16
        val BIG_FACT = 17
        val SLIDESHOW = 18
        val GALLERY = 19
        val BLOCK_QUOTE = 20
        val MEDIA = 21
        val VIDEO = 22
        val JS_EMBED = 23
        val TWEET = 24
        val QUESTION_ANSWER = 25
        val BIT_GRAVITY_VIDEO = 32
        val BRIGHTCOVE_VIDEO = 33
        val POLLTYPE = 34
        val ALSO_READ = 35
        val QUESTION = 36
        val ANSWER = 37
        val TIME_STAMP = 38
        val SORT_CARDS = 39
        val NESTED_STORY = 40
        val TABLE = 10
        val UNKNOWN = 48

        val STORY_HEADER = 51
        val STORY_TAG = 52
        val ME_TYPE = 53
        val STORY_FEEDBACK = 54
        val ATTACHMENT = 55
        val REFERENCE = 56
        val ME_TYPE_REACTION = 60
        val ME_TYPE_CLAP = 61

        val ICON_WITH_TITLE = 57
        val SOCIAL_SHARE_WIDGET = 58
        val CONTRIBUTORS_WIDGET = 59
        val ADS = 60
        val CARD_SHARE_WIDGET = 61
    }
}