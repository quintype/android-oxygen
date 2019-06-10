package com.quintype.oxygen.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class Comment protected constructor(parcel: Parcel) : Parcelable {

    @SerializedName("id")
    private val id: Long = parcel.readLong()
    @SerializedName("upvotes")
    private val upVotes: Long = parcel.readLong()
    @SerializedName("downvotes")
    private val downVotes: Long = parcel.readLong()
    @SerializedName("text")
    private val text: String = parcel.readString()
    @SerializedName("member-id")
    private val memberId: Long = parcel.readLong()
    @SerializedName("member-name")
    private val memberName: String = parcel.readString()
    @SerializedName("created-at")
    private val createdAt: Long = parcel.readLong()

    fun id(): Long {
        return id
    }

    fun upVotes(): Long {
        return upVotes
    }

    fun downVotes(): Long {
        return downVotes
    }

    fun text(): String {
        return text
    }

    fun memberId(): Long {
        return memberId
    }

    fun memberName(): String {
        return memberName
    }

    fun createdAt(): Long {
        return createdAt
    }

    override fun toString(): String {
        return "Comment{" +
                "id=" + id +
                ", upVotes=" + upVotes +
                ", downVotes=" + downVotes +
                ", text='" + text + '\''.toString() +
                ", memberId=" + memberId +
                ", memberName='" + memberName + '\''.toString() +
                ", createdAt=" + createdAt +
                '}'.toString()
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(this.id)
        dest.writeLong(this.upVotes)
        dest.writeLong(this.downVotes)
        dest.writeString(this.text)
        dest.writeLong(this.memberId)
        dest.writeString(this.memberName)
        dest.writeLong(this.createdAt)
    }

    companion object CREATOR : Parcelable.Creator<Comment> {
        override fun createFromParcel(parcel: Parcel): Comment {
            return Comment(parcel)
        }

        override fun newArray(size: Int): Array<Comment?> {
            return arrayOfNulls(size)
        }
    }
}