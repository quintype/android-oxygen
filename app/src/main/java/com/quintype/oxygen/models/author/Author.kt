package com.quintype.oxygen.models.author

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 8/31/18.
 */

class Author : Parcelable {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("publisher-id")
    var publisherId: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("twitter-handle")
    var twitterHandle: String? = null
    @SerializedName("bio")
    var bio: String? = null
    @SerializedName("avatar-url")
    var avatarUrl: String? = null
    @SerializedName("avatar-s3-key")
    var avatarS3Key: String? = null
    @SerializedName("slug")
    var slug: String? = null
    @SerializedName("email")
    var email: String? = null

    @SerializedName("can-login")
    var canLogin: Boolean = false
    @SerializedName("communication-email")
    var communicationEmail: String? = null
    @SerializedName("created-at")
    var createdAt: String? = null
    @SerializedName("first-name")
    var firstName: String? = null
    @SerializedName("last-name")
    var lastName: String? = null
    @SerializedName("updated-at")
    var updatedAt: String? = null
    @SerializedName("contributor-role")
    var contributorRole: ContributorRoleModel? = null

    /*TODO :I didn't see any usage of this builder, so not updating this with new values. */
    constructor(builder: Builder) {
        id = builder.id
        publisherId = builder.publisherId
        name = builder.name
        twitterHandle = builder.twitterHandle
        bio = builder.bio
        avatarUrl = builder.avatarUrl
        avatarS3Key = builder.avatarS3Key
        slug = builder.slug
        email = builder.email
        contributorRole = builder.contributorRole
    }

    override fun toString(): String {
        return "Author{" +
                "name='" + name + '\''.toString() +
                ", id='" + id + '\''.toString() +
                ", twitterHandle=" + twitterHandle +
                ", bio=" + bio +
                ", avatarUrl=" + avatarUrl +
                ", avatarS3Key=" + avatarS3Key +
                ", slug=" + slug +
                ", email='" + email + '\''.toString() +
                ", publisherId='" + publisherId + '\''.toString() +
                '}'.toString()
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.id)
        dest.writeString(this.publisherId)
        dest.writeString(this.name)
        dest.writeString(this.twitterHandle)
        dest.writeString(this.bio)
        dest.writeString(this.avatarUrl)
        dest.writeString(this.avatarS3Key)
        dest.writeString(this.slug)
        dest.writeString(this.email)
        dest.writeParcelable(this.contributorRole, flags)
        dest.writeByte(if (canLogin) 1 else 0)
        dest.writeString(this.communicationEmail)
        dest.writeString(this.createdAt)
        dest.writeString(this.firstName)
        dest.writeString(this.lastName)
        dest.writeString(this.updatedAt)
    }

    private constructor(parcel: Parcel) {
        this.id = parcel.readString()
        this.publisherId = parcel.readString()
        this.name = parcel.readString()
        this.twitterHandle = parcel.readString()
        this.bio = parcel.readString()
        this.avatarUrl = parcel.readString()
        this.avatarS3Key = parcel.readString()
        this.slug = parcel.readString()
        this.email = parcel.readString()
        this.contributorRole = parcel.readParcelable(ContributorRoleModel::class.java.classLoader)
        this.canLogin = parcel.readByte() != 0.toByte()
        this.communicationEmail = parcel.readString()
        this.createdAt = parcel.readString()
        this.firstName = parcel.readString()
        this.lastName = parcel.readString()
        this.updatedAt = parcel.readString()
    }


    class Builder {
        var id: String? = null
        var publisherId: String? = null
        var name: String? = null
        var twitterHandle: String? = null
        var bio: String? = null
        var avatarUrl: String? = null
        var avatarS3Key: String? = null
        var slug: String? = null
        var email: String? = null
        var contributorRole: ContributorRoleModel? = null

        fun contributorRole(`val`: ContributorRoleModel): Builder {
            contributorRole = `val`
            return this
        }

        fun id(`val`: String): Builder {
            id = `val`
            return this
        }

        fun publisherId(`val`: String): Builder {
            publisherId = `val`
            return this
        }

        fun name(`val`: String): Builder {
            name = `val`
            return this
        }

        fun twitterHandle(`val`: String): Builder {
            twitterHandle = `val`
            return this
        }

        fun bio(`val`: String): Builder {
            bio = `val`
            return this
        }

        fun avatarUrl(`val`: String): Builder {
            avatarUrl = `val`
            return this
        }

        fun avatarS3Key(`val`: String): Builder {
            avatarS3Key = `val`
            return this
        }

        fun slug(`val`: String): Builder {
            slug = `val`
            return this
        }

        fun email(`val`: String): Builder {
            email = `val`
            return this
        }

        fun build(): Author {
            return Author(this)
        }
    }

    companion object CREATOR : Parcelable.Creator<Author> {
        override fun createFromParcel(parcel: Parcel): Author {
            return Author(parcel)
        }

        override fun newArray(size: Int): Array<Author?> {
            return arrayOfNulls(size)
        }
    }
}