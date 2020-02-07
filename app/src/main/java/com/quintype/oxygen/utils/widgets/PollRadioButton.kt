package com.quintype.oxygen.utils.widgets


import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.content.ContextCompat
import com.quintype.prothomalo.R
import java.util.*

/**
 * Created by rakshith on 3/10/17.
 */
class PollRadioButton : AppCompatRadioButton {
    private var backgroundPaint: Paint? = null
    private var progressPaint: Paint? = null
    private var strokePaint: Paint? = null
    private var mTextPaint: TextPaint? = null
    private var isCurrentVote: Boolean = false
    // made progressValue an Object rather than primitive int so that we know whether
    // the variable is empty or it has 0 value:
    private var progressValue: Int? = null
    internal var bounds = Rect()
    internal var buttonArea = RectF()

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }


    fun setProgressValue(progressValue: Int) {
        this.progressValue = progressValue
        invalidate()
    }

    fun setIsCurrentVote(currentVote: Boolean) {
        isCurrentVote = currentVote
    }

    private fun init(context: Context) {
        //to format the radio button text
        mTextPaint = TextPaint()
        mTextPaint!!.color = Color.BLACK
        mTextPaint!!.textSize = textSize
        mTextPaint!!.isAntiAlias = true
        mTextPaint!!.textAlign = Paint.Align.CENTER
        mTextPaint!!.typeface = Typeface.DEFAULT

        //to format the progress bar that shows the vote percentage
        progressPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        progressPaint!!.color = ContextCompat.getColor(context, R.color.poll_progress_color)
        progressPaint!!.isAntiAlias = true
        progressPaint!!.strokeWidth = 4f
        progressPaint!!.setShadowLayer(6f, 5f, 5f, ContextCompat.getColor(context, android.R.color.white))
        //to format the background of each radio button
        backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        backgroundPaint!!.color = ContextCompat.getColor(context, R.color.poll_opinion_background)
        backgroundPaint!!.isAntiAlias = true
        backgroundPaint!!.strokeWidth = 4f
        backgroundPaint!!.setShadowLayer(6f, 5f, 5f, ContextCompat.getColor(context, android.R.color.white))

        strokePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    }

    //to set a custom typeFace to the radio button text
    fun setCustomTypeface(typeFace: Typeface) {
        mTextPaint!!.typeface = typeFace
        typeface = typeFace
        invalidate()
    }

    /**
     * We need rounded corners for each of our opinions, so we are building the Path for the
     * background RectF and the progress RectF from arcs and lines, in lieu of using
     * addRoundRect(). The arcTo() method is really handy, in this case, since it'll automatically
     * lineTo() its starting point. Knowing this, we can simply move around the corners, check if
     * a given corner is to be rounded, and lineTo() or arcTo() as needed.
     *
     * @param bounds      the RectF whose path is to be drawn
     * @param radius      for the rounded corners
     * @param topLeft     whether this corner is to be rounded
     * @param topRight    "
     * @param bottomRight "
     * @param bottomLeft  "
     * @return the built Path
     */
    private fun getPath(
        bounds: RectF, radius: Float, topLeft: Boolean,
        topRight: Boolean, bottomRight: Boolean, bottomLeft: Boolean
    ): Path {

        val twoRadius = radius * 2
        val path = Path()
        val cornerRect = RectF()
        cornerRect.set(0f, 0f, twoRadius, twoRadius)

        if (topLeft) {
            path.arcTo(cornerRect, 180f, 90f)
        } else {
            path.moveTo(0f, 0f)
        }

        if (topRight) {
            cornerRect.offsetTo(bounds.width() - twoRadius, 0f)
            path.arcTo(cornerRect, 270f, 90f)
        } else {
            path.lineTo(bounds.width(), 0f)
        }

        if (bottomRight) {
            cornerRect.offsetTo(bounds.width() - twoRadius, bounds.height() - twoRadius)
            path.arcTo(cornerRect, 0f, 90f)
        } else {
            path.lineTo(bounds.width(), bounds.height())
        }

        if (bottomLeft) {
            cornerRect.offsetTo(0f, bounds.height() - twoRadius)
            path.arcTo(cornerRect, 90f, 90f)
        } else {
            path.lineTo(0f, bounds.height())
        }

        path.close()
        return path
    }

    @Synchronized
    override fun onDraw(canvas: Canvas) {
        //get the corner radius for the radio button background
        val radius = context.resources.getDimension(R.dimen.size_8dp)
        //set how much area this button should fill
        buttonArea.set(0f, 0f, this.width.toFloat(), this.height.toFloat())
        //get the path in which to draw the radio button
        val path = getPath(
            buttonArea, radius,
            true, Integer.valueOf(100) == progressValue, Integer.valueOf(100) == progressValue, true
        )

        //Intersect the current clip with the specified path.
        canvas.clipPath(path)
        //now draw the background of the radio button inside the clipped canvas
        canvas.drawRoundRect(buttonArea, radius, radius, backgroundPaint!!)

        //getting the relevant dimensions for this radio button
        val leftPadding = paddingLeft
        val rightPadding = paddingRight
        val width = width /*- rightPadding - leftPadding*/
        val height = height /*- getPaddingBottom() - getPaddingTop()*/

        //setting the progress according to the vote percentage
        if (progressValue != null) {
            val progress = progressValue!! / 100f * width
            canvas.drawRect(0f, 0f, progress, height.toFloat(), progressPaint!!)


            //setting the percentage text
            val progressText = String.format(Locale.ENGLISH, "%d%%", progressValue)
            mTextPaint!!.getTextBounds(progressText, 0, progressText.length, bounds)
            mTextPaint!!.typeface = Typeface.DEFAULT_BOLD
            //if the button shows the opinion the user has voted on, change the progress text color
            if (isCurrentVote)
                mTextPaint!!.color = ContextCompat.getColor(context, R.color.thank_you_percentage_text_color)
            canvas.drawText(progressText, (width - bounds.width()).toFloat(), height / 2f + bounds.height() / 4f, mTextPaint!!)
        }
        //if the button is the current user vote, set a stroke around the button and change
        //text color
        if (isCurrentVote) {
            //setting the text color
            setTextColor(ContextCompat.getColor(context, R.color.thank_you_percentage_text_color))
            //            backgroundPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            //changing the style of the strokePaint object
            strokePaint!!.color = ContextCompat.getColor(context, R.color.thank_you_percentage_text_color)
            strokePaint!!.isAntiAlias = true
            strokePaint!!.style = Paint.Style.STROKE
            strokePaint!!.strokeWidth = 6f
            strokePaint!!.setShadowLayer(6f, 5f, 5f, ContextCompat.getColor(context, android.R.color.white))
            //            strokePaint.setStrokeJoin(Paint.Join.ROUND);
            strokePaint!!.strokeCap = Paint.Cap.ROUND
            //drawing the stroke around the button
            canvas.drawRoundRect(buttonArea, radius, radius, strokePaint!!)
        }

        //finally, draw the whole view
        super.onDraw(canvas)
    }
}