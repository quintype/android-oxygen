package com.quintype.oxygen.utils.widgets

import com.quintype.oxygen.models.story.ImageMetaData
import java.util.*

class FocusPointResolver(private val slug: String, private val imageMetadata: ImageMetaData?) {
    fun path(aspectRatio: IntArray): String {
        var rectPath = ""
        return if (this.imageMetadata != null && imageMetadata.focusPoints != null && imageMetadata.focusPoints?.size == 2) {
            rectPath = imageBounds(aspectRatio, imageMetadata.focusPoints!!)
            String.format("%s&rect=%s", slug, rectPath)
        } else {
            //slug
            rectPath = imageBounds(aspectRatio, null)
            String.format("%s&rect=%s", slug, rectPath)
        }
    }

    private fun imageBounds(cropDimension: IntArray, focusPoint: IntArray?): String {
        val cropWidth = cropDimension[0]
        val cropHeight = cropDimension[1]
        var focusPointXValue = 0
        var focusPointYValue = 0
        if (focusPoint != null) {
            focusPointXValue = focusPoint[0] - (cropWidth / 2)
            focusPointYValue = focusPoint[1] - (cropHeight / 2)
        }

        return String.format(Locale.ENGLISH, "$focusPointXValue,$focusPointYValue,$cropWidth,$cropHeight")
    }
}