package com.quintype.oxygen.utils.widgets

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import com.facebook.common.util.UriUtil
import com.facebook.network.connectionclass.ConnectionQuality
import com.vikatanapp.vikatan.preferences.LocalPreferenceManager
import com.vikatanapp.vikatan.ui.main.activities.MagazinePreviewActivity
import com.vikatanapp.vikatan.utils.*
import java.io.File
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.util.*

/**
 * An object instance of ImageLoader.
 */
class ImageLoader(cdnHostUrl: String) {
    var LogTag = ImageLoader::class.java.simpleName!!

    companion object {
        private var mImageLoaderInstance: ImageLoader? = null

        @Synchronized
        fun getInstance(): ImageLoader {
            if (mImageLoaderInstance == null)
                mImageLoaderInstance = ImageLoader("")

            return mImageLoaderInstance as ImageLoader
        }
    }

    internal var width = -1f
    internal var height = -1f
    private var imageConfig: ImageConfig
    private var resolution = -1f
    internal var useImgixCrop = false
    private val mCDNHostUrl = cdnHostUrl

    init {
        this.imageConfig = ImageConfig(mCDNHostUrl)
    }

    /**
     * Set the width of the image to be fetched.
     *
     * @param width as int
     */
    fun width(width: Int): ImageLoader {
        this.width = width.toFloat()
        return this
    }

    /**
     * Set the height of the image to be fetched.
     *
     * @param height as int
     */
    fun height(height: Int): ImageLoader {
        this.height = height.toFloat()
        return this
    }

    /**
     * Resolution of the image to be fetched.
     *
     * @param resolution is a float value 0f to 1f where 0f is no resolution and 1f is the
     * original resolution.
     * 0.7f - low resolution.
     * 0.8f - normal resolution.
     * 0.9f - high resolution.
     * 1f - original resolution.
     */
    fun quality(resolution: Float): ImageLoader {
        if (resolution < 0 || resolution > 1) {
            throw IllegalArgumentException("Resolution should be between 0 and 1. 0.7f Normal" + " res and 1f Original Res ")
        }
        this.resolution = resolution
        imageConfig.setImageQuality(resolution)
        return this
    }

    /**
     * Crop image from imgix tools
     *
     * @param useImgixCrop if true use imgix crop tools false otherwise.
     */
    fun useImgixCrop(useImgixCrop: Boolean): ImageLoader {
        this.useImgixCrop = useImgixCrop
        return this
    }

    /**
     * Method to auto set the resolution of the image to be fetched from the server based on the
     * bandwidth of the user's device.
     *
     *
     * See [com.facebook.network.connectionclass.ConnectionQuality]
     */
    fun autoQuality(): ImageLoader {
        imageConfig.setAutoImageQuality()
        return this
    }

    /**
     * image-s3-key path exists in storage then load image from storage or else call getImageUrl by passing [ImageSourceDataModel]
     */
    fun getImageUrl(mContext: Context, source: ImageSourceDataModel): Uri {
        val imageS3Key = source.imageS3Key
        if (imageS3Key != null) {
            val imageName = imageS3Key.substring(imageS3Key.lastIndexOf('/') + 1)
            val pathForHiddenFile: String = File(imageS3Key).parent + "/.$imageName".replace(".jpg", "")

            return if (MagazinePreviewActivity.mSelectedMagazineFolder != null && File(LocalPreferenceManager.newInstance(mContext).getStorageOption().getMagazinesRoot() + MagazinePreviewActivity.mSelectedMagazineFolder + pathForHiddenFile).exists()) {
                UriUtil.getUriForFile(File(LocalPreferenceManager.newInstance(mContext).getStorageOption().getMagazinesRoot() + MagazinePreviewActivity.mSelectedMagazineFolder + pathForHiddenFile))
            } else {
                Uri.parse(getImageUrl(source))
            }
        }
        return Uri.parse(getImageUrl(source))
    }

    /**
     * Process and get the image url from [ImageSourceDataModel] based on the client preferences.
     *
     * @param source [ImageSourceDataModel]
     * @return [String] Image url
     */
    fun getImageUrl(source: ImageSourceDataModel): String {
        if (width <= -1 && height <= -1) {
            throw IllegalArgumentException("Width or Height should be set.")
        }

        if (width == 0f || height == 0f) {
            throw IllegalArgumentException("Width or Height should be > 0")
        }

        if (resolution == -1f) {
            imageConfig.setAutoImageQuality()
        }

        var url = ImageConfig.NO_IMAGE_URL

        when {
            width > 0 && height > 0 -> url = imageConfig.buildImageUrlWH(
                source.imageS3Key, source.avatarUrl, width.toInt(), height.toInt(),
                source.imageS3Key, source.isGifImage
            )
            width > 0 -> url = imageConfig.buildImageUrlFitWidth(
                source.imageS3Key, source.avatarUrl, width.toInt(), source.imageS3Key, source.isGifImage
            )
            height > 0 -> url = imageConfig.buildImageUrlFitHeight(
                source.imageS3Key, source.avatarUrl, height.toInt(), source.imageS3Key, source.isGifImage
            )
        }

        val focuspointResolver = FocusPointResolver(url, source.imageMetaData)
        if (useImgixCrop && source.imageMetaData != null && source.imageMetaData?.height!! > 0 &&
            source.imageMetaData?.width!! > 0 && source.imageMetaData?.focusPoints != null &&
            source.imageMetaData?.focusPoints?.size == 2
        ) {
            url = if (width > 0 && height > 0) {
                val cropDimension = intArrayOf(width.toInt(), height.toInt())
                focuspointResolver.path(cropDimension)
            } else {
                val cropDimension = intArrayOf(defaultImageWidth, defaultImageHeight)
                focuspointResolver.path(cropDimension)
            }
        }
        //logdExt("The Image Url is $url")
        return url
    }

    fun isTypeImageGif(imageS3Key: String): Boolean {
        return if (!TextUtils.isEmpty(imageS3Key))
            imageS3Key.endsWith(".gif")
        else
            false
    }

    /**
     * An object instance referencing ImageConfig
     * used to process the image url based on the quality and resolution required.
     *
     * base url of the server to fetch the images from.
     */
    internal class ImageConfig(cdnHostUrl: String) {
        private var LogTag = ImageConfig::class.java.simpleName
        //Max quality of the images. Best for mobile.
        private var maxQuality = 50f
        private var imageQuality = Math.round(NORMAL_RES * maxQuality)
        private val mCDNHostUrl = cdnHostUrl

        fun setImageQuality(resolution: Float): ImageConfig {
            var resolution = resolution
            if (resolution < 0) {
                resolution = 0f
            } else if (resolution > 1f) {
                resolution = 1f
            }
            this.imageQuality = Math.round(resolution * maxQuality)
            return this
        }

        /**
         * Auto set image quality of the image based on the bandwidth of the user's device.
         *
         *
         * See [com.facebook.network.connectionclass.ConnectionClassManager]
         */
        fun setAutoImageQuality(): ImageConfig {
            when (Utilities().connectionQuality()) {
                ConnectionQuality.POOR -> this.imageQuality = Math.round(LOW_RES * maxQuality)
                ConnectionQuality.MODERATE -> this.imageQuality = Math.round(LOW_RES * maxQuality)
                ConnectionQuality.GOOD -> this.imageQuality = Math.round(HIGH_RES * maxQuality)
                ConnectionQuality.EXCELLENT -> this.imageQuality = Math.round(ORIGINAL_RES * maxQuality)
                ConnectionQuality.UNKNOWN -> this.imageQuality = Math.round(NORMAL_RES * maxQuality)
            }
            return this
        }

        /**
         * Build Image url to fit the width provided.
         *
         * @param imageKey     Key of the image. Appended to the base url
         * @param width        width of the image
         * @param emptyMessage empty log message
         * @param isGif        true if the image is "gif" else false
         * @return [String] Image url
         */
        fun buildImageUrlFitWidth(imageKey: String?, avatarUrl: String?, width: Int, emptyMessage: String?, isGif: Boolean): String {
            try {
                if (imageKey != null) {
                    return if (isGif) {
                        String.format(
                            Locale.ENGLISH, URL_WIDTH_IMAGE_FORMAT_NO_GIF, mCDNHostUrl,
                            URLEncoder.encode(imageKey, UTF_8), Math.min(
                                width.toDouble(),
                                GIF_MAX_WIDTH
                            ).toInt()
                        )
                    } else {
                        String.format(
                            Locale.ENGLISH, URL_WIDTH_IMAGE_FORMAT, mCDNHostUrl,
                            URLEncoder.encode(imageKey, UTF_8), width, imageQuality
                        )
                    }
                } else if (!TextUtils.isEmpty(avatarUrl)) {
                    return avatarUrl as String
                } else {
                    logdExt("Hero image key is null for story $emptyMessage")
                    return NO_IMAGE_URL
                }
            } catch (e: UnsupportedEncodingException) {
                logdExt("Image url building failed for $imageKey is $e")
                return NO_IMAGE_URL
            }

        }

        /**
         * Build Image url to fit the height provided.
         *
         * @param imageKey     Key of the image. Appended to the base url
         * @param height       height of the image
         * @param emptyMessage empty log message
         * @param isGif        true if the image is "gif" else false
         * @return [String] Image url
         */
        fun buildImageUrlFitHeight(imageKey: String?, avatarUrl: String?, height: Int, emptyMessage: String?, isGif: Boolean): String {
            try {
                if (imageKey != null) {
                    return if (isGif) {
                        String.format(
                            Locale.ENGLISH, URL_HEIGHT_IMAGE_FORMAT_NO_GIF,
                            mCDNHostUrl,
                            URLEncoder.encode(imageKey, UTF_8), Math.min(
                                height.toDouble(),
                                GIF_MAX_WIDTH
                            ).toInt()
                        )
                    } else {
                        String.format(
                            Locale.ENGLISH, URL_HEIGHT_IMAGE_FORMAT, mCDNHostUrl,
                            URLEncoder.encode(imageKey, UTF_8), height, imageQuality
                        )
                    }
                } else if (!TextUtils.isEmpty(avatarUrl)) {
                    return avatarUrl as String
                } else {
                    logdExt("Hero image key is null for story $emptyMessage")
                    return NO_IMAGE_URL
                }
            } catch (e: UnsupportedEncodingException) {
                logeExt("Image url building failed for$imageKey is $e")
                return NO_IMAGE_URL
            }

        }

        /**
         * Build Image url to match both width and height provided
         *
         * @param imageKey     Key of the image. Appended to the base url
         * @param width        width of the image
         * @param height       height of the image
         * @param emptyMessage empty log message
         * @param isGif        true if the image is "gif" else false
         * @return [String] Image url
         */
        fun buildImageUrlWH(imageKey: String?, avatarUrl: String?, width: Int, height: Int, emptyMessage: String?, isGif: Boolean): String {
            try {
                if (imageKey != null) {
                    return if (isGif) {
                        String.format(
                            Locale.ENGLISH, URL_WH_IMAGE_FORMAT_NO_GIF, mCDNHostUrl,
                            URLEncoder.encode(imageKey, UTF_8), Math.min(
                                width.toDouble(),
                                GIF_MAX_WIDTH
                            ).toInt(), Math.min(height.toDouble(), GIF_MAX_WIDTH).toInt()
                        )
                    } else {
                        String.format(
                            Locale.ENGLISH, URL_WH_IMAGE_FORMAT, mCDNHostUrl,
                            URLEncoder.encode(imageKey, UTF_8), width, height, imageQuality
                        )
                    }
                } else if (!TextUtils.isEmpty(avatarUrl)) {
                    return avatarUrl as String
                } else {
                    logdExt("Hero image key is null for story  $emptyMessage")
                    return NO_IMAGE_URL
                }
            } catch (e: UnsupportedEncodingException) {
                logdExt("Image url building failed for $imageKey is $e")
                return NO_IMAGE_URL
            }

        }

        companion object {
            const val NO_IMAGE_URL = "qs://noImage"

            //Image resolutions.
            private const val ORIGINAL_RES = 1f
            private const val HIGH_RES = 0.9f
            private const val NORMAL_RES = 0.8f
            private const val LOW_RES = 0.7f
            private const val UTF_8 = "UTF-8"

            //Image url formats
            private const val URL_WIDTH_IMAGE_FORMAT = "%s%s?w=%d&q=%d&fm=webp"
            private const val URL_HEIGHT_IMAGE_FORMAT = "%s%s?h=%d&q=%d&fm=webp"
            private const val URL_WIDTH_IMAGE_FORMAT_NO_GIF = "%s%s?w=%d&q=40"
            private const val URL_HEIGHT_IMAGE_FORMAT_NO_GIF = "%s%s?h=%d&q=40"
            private const val URL_WH_IMAGE_FORMAT = "%s%s?w=%d&h=%d&q=%d&fm=webp&fit=crop"
            private const val URL_WH_IMAGE_FORMAT_NO_GIF = "%s%s?w=%d&h=%d&fit=crop&fm=gif&q=40"
            private const val GIF_MAX_WIDTH = 200.0 //400 max pixels
        }

    }
}
