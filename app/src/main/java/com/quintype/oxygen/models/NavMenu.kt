package com.quintype.oxygen.models

import android.os.Parcelable
import android.text.TextUtils
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.sections.Section
import com.quintype.oxygen.models.story.Tag
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NavMenu(
    @SerializedName("updated-at")
    val updatedAt: Long = 0,
    @SerializedName("tag-name")
    var tagName: String? = null,
    @SerializedName("publisher-id")
    val publisherId: String? = null,
    @SerializedName("item-id")
    val itemId: String = "",
    @SerializedName("rank")
    val rank: Long = 0,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("item-type")
    val type: String? = null,
    @SerializedName("section-slug")
    val sectionSlug: String? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("parent-id")
    val parentId: String? = null,
    @SerializedName("created-at")
    val createdAt: Long = 0,
    @SerializedName("section-name")
    var sectionName: String? = null,
    @SerializedName("url")
    val sectionUrl: String? = null,
    @SerializedName("data")
    val data: NavMenuData? = null,
    @SerializedName("collection-slug")
    val collectionSlug: String? = null,
    @SerializedName("tag-slug")
    var tagSlug: String? = null
) : Parcelable {

    /**
     * @return true if type is section, false otherwise
     */
    val isTypeSection: Boolean
        get() = type!!.equals(TYPE_SECTION, ignoreCase = true)

    /**
     * @return true if type is tag, false otherwise
     */
    val isTypeTag: Boolean
        get() = type!!.equals(TYPE_TAG, ignoreCase = true)

    /**
     * @return true if type is link, false otherwise
     */
    val isTypeLink: Boolean
        get() = type!!.equals(TYPE_LINK, ignoreCase = true)

    /**
     * @return true if type is link, false otherwise
     */
    val isTypePlaceholder: Boolean
        get() = type!!.equals(TYPE_PLACEHOLDER, ignoreCase = true)


    /**
     * @return display name to be used in UI
     */
    fun displayName(): String? {
        if (TextUtils.isEmpty(title)) {
            if (isTypeSection && !TextUtils.isEmpty(sectionName))
                return sectionName
            else if (isTypeTag && !TextUtils.isEmpty(tagName))
                return tagName
        }
        return title
    }

    /**
     * @return build a section instance
     */

    fun section(): Section? {
        return if (!TextUtils.isEmpty(sectionName)) {
            Section(sectionName!!)
        } else
            null
    }

    /**
     * @return an instance of tag
     */
    fun tag(): Tag? {
        return if (!TextUtils.isEmpty(tagName))
            Tag(tagName!!)
        else
            null
    }

    /**
     * update Title for the navMenu
     *
     * @param title
     */
    fun title(title: String) {
        this.title = title
    }

    /**
     * update id for the navMenu
     *
     * @param id
     */
    fun id(id: String) {
        this.id = id
    }

    /**
     * update section Name for the navMenu
     *
     * @param sectionName
     */
    fun sectionName(sectionName: String) {
        this.sectionName = sectionName
    }

    /**
     * update tag Name for the navMenu
     *
     * @param tagName
     */
    fun tagName(tagName: String) {
        this.tagName = tagName
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val TYPE_SECTION = "section"
        val TYPE_TAG = "tag"
        val TYPE_LINK = "link"
        val TYPE_PLACEHOLDER = "placeholder"
        private val homeType = "home"
    }
}
