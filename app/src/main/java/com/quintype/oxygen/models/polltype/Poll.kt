package com.quintype.oxygen.models.polltype

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.quintype.oxygen.models.story.HeroImage
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Poll(
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("opinions")
    var opinions: List<Opinion>? = null,
    @SerializedName("last-voted-at")
    var lastVotedAt: Long? = null,
    @SerializedName("locale")
    var locale: String? = null,
    @SerializedName("texts")
    var texts: PollTexts? = null,
    @SerializedName("voted-on")
    var votedOn: VotedOn? = null,
    @SerializedName("settings")
    var settings: PollSettings? = null,
    @SerializedName("hero-image")
    var heroImage: HeroImage? = null,
    @SerializedName("topic")
    var topic: String? = null,
    @SerializedName("organization")
    var organization: Organization? = null,
    @SerializedName("published-at")
    var publishedAt: Long? = null,
    @SerializedName("theme")
    var theme: PollTheme? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("id")
    var id: Long? = null,
    @SerializedName("confidence-chi-square")
    var confidenceChiSquare: Int? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("metadata")
    var metadata: PollMetadata? = null
) : Parcelable

@Parcelize
data class Opinion(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("text")
    var text: String? = null,
    @SerializedName("is-archived")
    var isArchived: Boolean? = null,
    @SerializedName("percentage-votes")
    var percentageVotes: Int? = 0
) : Parcelable

@Parcelize
data class PollTexts(
    @SerializedName("authorise-facebook-msg")
    var authoriseFacebookMsg: String? = null,
    @SerializedName("your-result-msg")
    var yourResultMsg: String? = null,
    @SerializedName("votes-across-polltype-msg")
    var votesAcrossPolltypeMsg: String? = null,
    @SerializedName("change-vote-msg")
    var changeVoteMsg: String? = null,
    @SerializedName("embed-button")
    var embedButton: String? = null,
    @SerializedName("share-the-poll-msg")
    var shareThePollMsg: String? = null,
    @SerializedName("vote-received-msg")
    var voteReceivedMsg: String? = null,
    @SerializedName("login-msg")
    var loginMsg: String? = null,
    @SerializedName("looking-across-polltype-msg")
    var lookingAcrossPolltypeMsg: String? = null,
    @SerializedName("thank-you-msg")
    var thankYouMsg: String? = null,
    @SerializedName("share-button")
    var shareButton: String? = null,
    @SerializedName("share-via")
    var shareVia: String? = null,
    @SerializedName("all-result-msg")
    var allResultMsg: String? = null,
    @SerializedName("vote-msg")
    var voteMsg: String? = null
) : Parcelable

@Parcelize
data class PollSettings(
    @SerializedName("change-vote?")
    var changeVote: Boolean? = null,
    @SerializedName("show-results")
    var showResults: String? = null,
    @SerializedName("anonymous-voting?")
    var anonymousVoting: Boolean? = null,
    @SerializedName("embeddable?")
    var embeddable: Boolean? = null,
    @SerializedName("authentication")
    var authentication: List<String>? = null,
    @SerializedName("show-default-hero-image-in-embed")
    var showDefaultHeroImageInEmbed: Boolean? = null
) : Parcelable

@Parcelize
data class Organization(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("quintype-publisher-id")
    var quintypePublisherId: Int? = null
) : Parcelable

@Parcelize
data class PollTheme(
    @SerializedName("colors")
    var colors: PollColors? = null
) : Parcelable

@Parcelize
data class PollColors(
    @SerializedName("brand-primary")
    var brandPrimary: String? = null,
    @SerializedName("brand-secondary")
    var brandSecondary: String? = null,
    @SerializedName("brand-lighter")
    var brandLighter: String? = null
) : Parcelable

@Parcelize
data class PollMetadata(
    @SerializedName("height")
    var height: Int? = 0,
    @SerializedName("width")
    var width: Int? = 0
) : Parcelable

@Parcelize
data class VotedOn(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("text")
    var text: String? = null,
    @SerializedName("percentage-votes")
    var percentageVotes: Int? = 0,
    @SerializedName("remaining-percentage")
    var remainingPercentage: Int? = 0
) : Parcelable