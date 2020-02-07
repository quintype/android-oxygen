package com.quintype.oxygen.services.latest

import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.models.polltype.IntentionRequest
import com.quintype.oxygen.models.polltype.PollResponse
import com.quintype.oxygen.models.polltype.VoteRequest
import io.reactivex.Single
import retrofit2.http.*

interface IPolltypeService {
    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("/p/api/polls/" + "{" + OxygenConstants.QUERY_PARAM_POLL_ID + "}")
    fun getPollResponse(@Path(OxygenConstants.QUERY_PARAM_POLL_ID) PollId: String): Single<PollResponse>

    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @POST("/p/api/polls/" + "{" + OxygenConstants.QUERY_PARAM_POLL_ID + "}" + "/intentions")
    fun postPollIntention(
        @Path(OxygenConstants.QUERY_PARAM_POLL_ID) pollId: String,
        @Body intentionRequest: IntentionRequest
    ): Single<PollResponse>

    @Headers(OxygenConstants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @POST("/p/api/polls/" + "{" + OxygenConstants.QUERY_PARAM_POLL_ID + "}" + "/votes")
    fun postPollVote(
        @Path(OxygenConstants.QUERY_PARAM_POLL_ID) pollId: String,
        @Body voteRequest: VoteRequest
    ): Single<PollResponse>
}