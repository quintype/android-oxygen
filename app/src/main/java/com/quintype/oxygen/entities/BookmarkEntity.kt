package com.quintype.oxygen.database.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.quintype.oxygen.TABLE_BOOKMARK
import com.quintype.oxygen.entities.DateConverter
import com.quintype.oxygen.models.author.Author
import com.quintype.oxygen.models.story.Alternative
import com.quintype.oxygen.models.story.ImageMetaData
import com.quintype.oxygen.models.story.Story
import com.quintype.oxygen.models.story.StoryMetaData
import java.util.*

@Entity(tableName = TABLE_BOOKMARK)
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") var storyId: String = "",//story id
    @ColumnInfo(name = "slug") var slug: String? = null,//story slug
    @ColumnInfo(name = "url") var storyUrl: String? = null,
    @ColumnInfo(name = "headline") var headline: String? = null,
    @ColumnInfo(name = "subheadline") var subHeadline: String? = null,
    @ColumnInfo(name = "alternative") var alternative: String? = null,
    @ColumnInfo(name = "metadata") var metadata: String? = null,
    @ColumnInfo(name = "hero-image-s3-key") var heroImageS3Key: String? = null,
    @ColumnInfo(name = "hero-image-metadata") var heroImageMetadata: String? = null,
    @ColumnInfo(name = "published-at") var publishedAt: String? = null,
    @ColumnInfo(name = "last-published-at") var lastPublishedAt: String? = null,
    @ColumnInfo(name = "story-template") var storyTemplate: String? = null,
    @ColumnInfo(name = "authors") var authors: String? = null,
    @ColumnInfo(name = "author-name") var authorName: String? = null,
    @ColumnInfo(name = "author-id") var authorId: String? = null,

    //fields which are not available from story object
    @TypeConverters(DateConverter::class)
    @ColumnInfo(name = "bookmarked_time") var bookMarkedTime: Date? = null,
    @ColumnInfo(name = "is_sync") var isSync: Boolean = false//is sync with server or not.
)

//bookmark metadata
data class BulkBookmarkMetadata(
    @SerializedName("id") var storyId: String = "",//story id
    @SerializedName("slug") var slug: String? = null,//story slug
    @SerializedName("url") var storyUrl: String? = null,
    @SerializedName("headline") var headline: String? = null,
    @SerializedName("subheadline") var subHeadline: String? = null,
    @SerializedName("alternative") var alternative: Alternative? = null,
    @SerializedName("metadata") var metadata: StoryMetaData? = null,
    @SerializedName("hero-image-s3-key") var heroImageS3Key: String? = null,
    @SerializedName("hero-image-metadata") var heroImageMetadata: ImageMetaData? = null,
    @SerializedName("published-at") var publishedAt: Long? = null,
    @SerializedName("last-published-at") var lastPublishedAt: Long? = null,
    @SerializedName("story-template") var storyTemplate: String? = null,
    @SerializedName("authors") var authors: List<Author>? = emptyList(),
    @SerializedName("author-name") var authorName: String? = null,
    @SerializedName("author-id") var authorId: String? = null,
    @SerializedName("bookmarked_time") var bookMarkedTime: Long? = null
)

/**
 * function used to get the bookmark entity from the story object
 */
fun Story.getBookmarkEntity(): BookmarkEntity? {
    val bookmarkEntity = BookmarkEntity()
    bookmarkEntity.storyId = id!!
    bookmarkEntity.subHeadline = subHeadLine
    bookmarkEntity.headline = headline
    bookmarkEntity.slug = slug
    bookmarkEntity.alternative = alternative?.toJSONString()
    bookmarkEntity.metadata = storyMetaData?.toJSONString()
    bookmarkEntity.heroImageMetadata = heroImageMeta?.toJSONString()
    bookmarkEntity.heroImageS3Key = heroImageS3Key
    bookmarkEntity.authors = authors?.toJSONString()
    bookmarkEntity.authorId = authorId
    bookmarkEntity.authorName = authorName
    bookmarkEntity.storyTemplate = template
    bookmarkEntity.storyUrl = storyUrl
    bookmarkEntity.publishedAt = publishedAt.toString()
    bookmarkEntity.lastPublishedAt = lastPublishedAt.toString()
    bookmarkEntity.bookMarkedTime = Date()//created time

    return bookmarkEntity
}


/**
 * function used to get the bookmark entity from the story object
 */
fun BulkBookmarkMetadata.getBookmarkEntity(): BookmarkEntity? {
    val bookmarkEntity = BookmarkEntity()
    bookmarkEntity.storyId = storyId
    bookmarkEntity.subHeadline = subHeadline
    bookmarkEntity.headline = headline
    bookmarkEntity.slug = slug
    bookmarkEntity.alternative = alternative?.toJSONString()
    bookmarkEntity.metadata = metadata?.toJSONString()
    bookmarkEntity.heroImageMetadata = heroImageMetadata?.toJSONString()
    bookmarkEntity.heroImageS3Key = heroImageS3Key
    bookmarkEntity.authors = authors?.toJSONString()
    bookmarkEntity.authorId = authorId
    bookmarkEntity.authorName = authorName
    bookmarkEntity.storyTemplate = storyTemplate
    bookmarkEntity.storyUrl = storyUrl
    bookmarkEntity.publishedAt = publishedAt.toString()
    bookmarkEntity.lastPublishedAt = lastPublishedAt.toString()
    return bookmarkEntity
}

/**
 * this is sending the metadata format to bulk bookmark API.
 */
fun BookmarkEntity.getBulkBookmarkMetadata(): BulkBookmarkMetadata {
    val bulkBookmarkMetadata = BulkBookmarkMetadata()
    bulkBookmarkMetadata.authorId = authorId
    bulkBookmarkMetadata.alternative = alternative?.run { Gson().fromJson(this, Alternative::class.java) }
    bulkBookmarkMetadata.heroImageMetadata = heroImageMetadata?.run { Gson().fromJson(this, ImageMetaData::class.java) }
    bulkBookmarkMetadata.metadata = metadata?.run { Gson().fromJson(this, StoryMetaData::class.java) }
    bulkBookmarkMetadata.authors = authors?.run {
        val type = object : TypeToken<List<Author>>() {}.type
        Gson().fromJson(this, type)
    }
    bulkBookmarkMetadata.heroImageS3Key = heroImageS3Key
    bulkBookmarkMetadata.authorName = authorName
    bulkBookmarkMetadata.publishedAt = publishedAt?.run {
        try {
            toLong()
        } catch (ex: Exception) {
            null
        }
    }
    bulkBookmarkMetadata.lastPublishedAt = lastPublishedAt?.run {
        try {
            toLong()
        } catch (ex: Exception) {
            null
        }
    }
    bulkBookmarkMetadata.storyId = storyId
    bulkBookmarkMetadata.slug = slug
    bulkBookmarkMetadata.storyTemplate = storyTemplate
    bulkBookmarkMetadata.headline = headline
    bulkBookmarkMetadata.subHeadline = subHeadline
    bulkBookmarkMetadata.storyUrl = storyUrl
    return bulkBookmarkMetadata
}

/**
 * function to convert object to string
 */
fun Any.toJSONString(): String? {
    try {
        return Gson().toJson(this)
    } catch (ex: Exception) {
        ex.printStackTrace()
        return null
    }
}
