package com.quintype.oxygen.utils.widgets

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.support.annotation.NonNull
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.vikatanapp.R

class CustomRatingBar : LinearLayout {

    private var mMaxStars = 5
    private var mCurrentScore = 2.5f
    private var mStarOnResource = R.drawable.ic_full_star_review
    private var mStarOffResource = R.drawable.ic_empty_star_review
    private var mStarHalfResource = R.drawable.ic_half_star_review
    private var mStarsViews: Array<ImageView>? = null
    private var mStarPadding: Float = 0.toFloat()
    private var onScoreChanged: IRatingBarCallbacks? = null
    private var mLastStarId: Int = 0
    private var mOnlyForDisplay: Boolean = false
    private var mLastX: Double = 0.toDouble()
    private var isHalfStars = true

    var score: Float
        get() = mCurrentScore
        set(score) {
            var score = score
            score = Math.round(score * 2) / 2.0f
            if (!isHalfStars)
                score = Math.round(score).toFloat()
            mCurrentScore = score
            refreshStars()
        }

    interface IRatingBarCallbacks {
        fun scoreChanged(score: Float)
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initializeAttributes(attrs, context)
        init()
    }

    private fun initializeAttributes(attrs: AttributeSet, context: Context) {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CustomRatingBar
        )
        for (i in 0 until typedArray.indexCount) {
            val attr = typedArray.getIndex(i)
            when (attr) {
                R.styleable.CustomRatingBar_maxStars -> mMaxStars = typedArray.getInt(attr, 5)
                R.styleable.CustomRatingBar_stars -> mCurrentScore = typedArray.getFloat(attr, 2.5f)
                R.styleable.CustomRatingBar_starHalf -> mStarHalfResource = typedArray.getResourceId(attr, R.drawable.ic_half_star_review)
                R.styleable.CustomRatingBar_starOn -> mStarOnResource = typedArray.getResourceId(attr, R.drawable.ic_full_star_review)
                R.styleable.CustomRatingBar_starOff -> mStarOffResource = typedArray.getResourceId(attr, R.drawable.ic_empty_star_review)
                R.styleable.CustomRatingBar_starPadding -> mStarPadding = typedArray.getDimension(attr, 0f)
                R.styleable.CustomRatingBar_onlyForDisplay -> mOnlyForDisplay = typedArray.getBoolean(attr, false)
                R.styleable.CustomRatingBar_halfStars -> isHalfStars = typedArray.getBoolean(attr, true)
            }
        }
        typedArray.recycle()
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        initializeAttributes(attrs, context)
        init()
    }

    private fun init() {
        mStarsViews = arrayOfNulls<ImageView>(mMaxStars) as Array<ImageView>
        for (i in 0 until mMaxStars) {
            val v = createStar()
            addView(v)
            mStarsViews!![i] = v
        }
        refreshStars()
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return true
    }

    /**
     * hardcore math over here
     *
     * @param x
     * @return
     */
    private fun getScoreForPosition(x: Float): Float {
        if (isHalfStars)
            return Math.round(x / (width.toFloat() / (mMaxStars * 3f)) / 3f * 2f).toFloat() / 2
        val value = Math.round(x / (width.toFloat() / mMaxStars)).toFloat()
        return if (value < 0) 1f else value
    }

    private fun getImageForScore(score: Float): Int {
        return if (score > 0)
            Math.round(score) - 1
        else
            -1
    }

    private fun refreshStars() {
        val flagHalf = mCurrentScore != 0f && mCurrentScore % 0.5 == 0.0 && isHalfStars
        for (i in 1..mMaxStars) {

            if (i <= mCurrentScore)
                mStarsViews!![i - 1].setImageResource(mStarOnResource)
            else {
                if (flagHalf && i - 0.5 <= mCurrentScore)
                    mStarsViews!![i - 1].setImageResource(mStarHalfResource)
                else
                    mStarsViews!![i - 1].setImageResource(mStarOffResource)
            }
        }
    }

    private fun createStar(): ImageView {
        val v = ImageView(context)
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        params.weight = 1f
        v.setPadding(mStarPadding.toInt(), 0, mStarPadding.toInt(), 0)
        v.adjustViewBounds = true
        v.scaleType = ImageView.ScaleType.FIT_CENTER
        v.layoutParams = params
        v.setImageResource(mStarOffResource)
        return v
    }

    private fun getImageView(position: Int): ImageView? {
        return try {
            mStarsViews!![position]
        } catch (e: Exception) {
            null
        }

    }

    override fun onTouchEvent(@NonNull event: MotionEvent): Boolean {
        if (mOnlyForDisplay)
            return true
        when (event.action) {
            MotionEvent.ACTION_UP -> {
                animateStarRelease(getImageView(mLastStarId))
                mLastStarId = -1
            }
            MotionEvent.ACTION_MOVE -> {
                if (Math.abs(event.x - mLastX) > 50)
                    requestDisallowInterceptTouchEvent(true)
                val lastscore = mCurrentScore
                mCurrentScore = getScoreForPosition(event.x)
                if (lastscore != mCurrentScore) {
                    animateStarRelease(getImageView(mLastStarId))
                    animateStarPressed(getImageView(getImageForScore(mCurrentScore)))
                    mLastStarId = getImageForScore(mCurrentScore)
                    refreshStars()
                    if (onScoreChanged != null)
                        onScoreChanged!!.scoreChanged(mCurrentScore)
                }
            }
            MotionEvent.ACTION_DOWN -> {
                mLastX = event.x.toDouble()
                val lastscore = mCurrentScore
                mCurrentScore = getScoreForPosition(event.x)
                animateStarPressed(getImageView(getImageForScore(mCurrentScore)))
                mLastStarId = getImageForScore(mCurrentScore)
                if (lastscore != mCurrentScore) {
                    refreshStars()
                    if (onScoreChanged != null)
                        onScoreChanged!!.scoreChanged(mCurrentScore)
                }
            }
        }
        return true
    }

    private fun animateStarPressed(star: ImageView?) {
        if (star != null)
            ViewCompat.animate(star).scaleX(1.2f).scaleY(1.2f).setDuration(100).start()
    }

    private fun animateStarRelease(star: ImageView?) {
        if (star != null)
            ViewCompat.animate(star).scaleX(1f).scaleY(1f).setDuration(100).start()
    }
}