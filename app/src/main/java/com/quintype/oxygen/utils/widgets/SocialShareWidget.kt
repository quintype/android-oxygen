package com.quintype.oxygen.utils.widgets

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.vikatanapp.R
import com.quintype.oxygen.models.story.Story
import com.vikatanapp.vikatan.utils.*
import kotlinx.android.synthetic.main.social_share_widget_layout.view.*

/**
 * Created by Deepak on 09/01/19.
 */

class SocialShareWidget : LinearLayout {

    private var socialShareManager: SocialShareManager? = null
    internal var mContext: Context
    private var itemView: View? = null
    private var mStoryTemplate: String? = null

    constructor(context: Context) : super(context) {
        setupView(context)
        this.mContext = context
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        this.mContext = context
        setupView(context)
    }

    fun setShareStory(story: Story, storyTemplate: String?) {
        socialShareManager = SocialShareManager(mContext, story)
        mStoryTemplate = storyTemplate

        //Analytics Event
        val args = Bundle()
        args.putString(ACTION, SHARE)
        setAnalytics(
            context as Activity,
            UserAnalytics.EVENT,
            "[$ARTICLE] ",
            args,
            story.id()
        )

        if (itemView != null) {
            if (mStoryTemplate.equals(STORY_TEMPLATE_PHOTO_ALBUM) || mStoryTemplate.equals(STORY_TEMPLATE_VIDEO)) {
                /*Setting dark Theme*/
                val lightColor: Int = mContext.resources?.getColor(R.color.white_transparent_color)!!
                social_share_left_border.setBackgroundColor(lightColor)
                social_share_right_border.setBackgroundColor(lightColor)
                social_share_top_border.setBackgroundColor(lightColor)
                social_share_bottom_border.setBackgroundColor(lightColor)

                facebook_left_divider.setBackgroundColor(lightColor)
                twitter_left_divider.setBackgroundColor(lightColor)
                mail_left_divider.setBackgroundColor(lightColor)
                share_logo_left_divider.setBackgroundColor(lightColor)

                share_via_placeholder.setTextColor(mContext.resources?.getColor(R.color.white)!!)
            } else {
                /*Setting light Theme*/
                val darkColor: Int = mContext.resources?.getColor(R.color.border_grey_transparent_color)!!

                social_share_left_border.setBackgroundColor(darkColor)
                social_share_right_border.setBackgroundColor(darkColor)
                social_share_top_border.setBackgroundColor(darkColor)
                social_share_bottom_border.setBackgroundColor(darkColor)

                facebook_left_divider.setBackgroundColor(darkColor)
                twitter_left_divider.setBackgroundColor(darkColor)
                mail_left_divider.setBackgroundColor(darkColor)
                share_logo_left_divider.setBackgroundColor(darkColor)

                share_via_placeholder.setTextColor(mContext.resources?.getColor(R.color.colorAccent)!!)
            }
        }
    }

    private fun setupView(context: Context) {
        itemView = LayoutInflater.from(context).inflate(R.layout.social_share_widget_layout, this)

        social_share_whatsapp_iv.setOnClickListener {
            socialShareManager?.shareViaWhatsApp()
        }

        social_share_facebook_iv.setOnClickListener {
            socialShareManager?.shareViaFacebook()
        }

        social_share_twitter_iv.setOnClickListener {
            socialShareManager?.shareViaTwitter()
        }

        social_share_mail_iv.setOnClickListener {
            socialShareManager?.shareViaEmail()
        }

        social_share_logo_iv.setOnClickListener {
            socialShareManager?.shareToAll()
        }
    }
}
