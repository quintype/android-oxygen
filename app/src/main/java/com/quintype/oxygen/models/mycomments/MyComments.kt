package com.quintype.oxygen.models.mycomments

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.author.Author
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import kotlinx.android.parcel.WriteWith

@Parcelize
data class MyComments(
    @SerializedName("data")
    var data: ArrayList<CommentData>? = null
) : Parcelable

@Parcelize
data class CommentData(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("attributes")
    var attributes: Attributes? = null
) : Parcelable

@Parcelize
data class Attributes(
    @SerializedName("body")
    var body: Body? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("page")
    var page: Page? = null,
    @SerializedName("toxicity")
    var toxicity: Double? = null,
    @SerializedName("created_at")
    var createdAt: String? = null,
    @SerializedName("edited_at")
    var editedAt: String? = null,
    @SerializedName("parent_comment")
    var parentComment: ParentComment? = null
) : Parcelable

@Parcelize
data class Body(
    @SerializedName("ops")
    var ops: List<Op>? = null
) : Parcelable

@Parcelize
data class Page(
    @SerializedName("hero_image_url")
    var heroImageUrl: String? = null,
    @SerializedName("headline")
    var headline: String? = null,
    @SerializedName("url")
    var url: String? = null
) : Parcelable

@Parcelize
data class ParentComment(
    @SerializedName("body")
    var body: ParentBody? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("author")
    var author: Author? = null
) : Parcelable

@Parcelize
data class ParentBody(
    @SerializedName("ops")
    var ops: List<ParentOp>? = null
) : Parcelable

@Parcelize
data class Op(
    @SerializedName("attributes")
    var attributes: OpAttributes? = null,
    @SerializedName("insert")
    var insert: @RawValue Any? = null
) : Parcelable

@Parcelize
data class OpAttributes(
    @SerializedName("mention")
    var mention: Mention? = null,
    @SerializedName("link")
    var link: String? = null
) : Parcelable

@Parcelize
data class OpInsert(
    @SerializedName("mentions")
    var mentions: String? = null
) : Parcelable

@Parcelize
data class ParentOp(
    @SerializedName("insert")
    var insert: @RawValue Any? = null
) : Parcelable

@Parcelize
data class Mention(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("slug")
    var slug: String? = null,
    @SerializedName("avatar")
    var avatar: String? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("target")
    var target: String? = null,
    @SerializedName("end-point")
    var endPoint: String? = null,
    @SerializedName("class")
    var class_: String? = null
) : Parcelable