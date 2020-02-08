package com.quintype.oxygen.database.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.quintype.oxygen.TABLE_NOTIFICATION

@Entity(tableName = TABLE_NOTIFICATION)
class NotificationEntity() {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
    @ColumnInfo(name = "notification_time")
    var mNotificationTime: Long? = 0
    @ColumnInfo(name = "notification_title")
    var mNotificationTitle: String? = null
    @ColumnInfo(name = "notification_text")
    var mNotificationText: String? = null
    @ColumnInfo(name = "notification_type")
    var mNotificationType: String? = null
    @ColumnInfo(name = "story_id")
    var storyID: String? = null
    @ColumnInfo(name = "story_slug")
    var storySlug: String? = null
    @ColumnInfo(name = "hero_image_url")
    var storyHeroImage: String? = null
    @ColumnInfo(name = "hero_image_s3_key")
    var storyHeroImageS3Key: String? = null

    constructor(
        notificationTime: Long?,
        notification_title: String?,
        notification_text: String?,
        notification_type: String?,
        storyID: String?,
        storySlug: String?,
        storyHeroImage: String?,
        storyHeroImageS3Key: String?
    ) : this() {
        this.mNotificationTime = notificationTime
        this.mNotificationTitle = notification_title
        this.mNotificationText = notification_text
        this.mNotificationType = notification_type
        this.storyID = storyID
        this.storySlug = storySlug
        this.storyHeroImage = storyHeroImage
        this.storyHeroImageS3Key = storyHeroImageS3Key
    }
}