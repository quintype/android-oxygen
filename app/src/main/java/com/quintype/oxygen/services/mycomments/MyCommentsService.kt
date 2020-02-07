package com.quintype.oxygen.services.mycomments

import com.quintype.oxygen.OxygenConstants
import com.quintype.oxygen.services.RetrofitApiClient

class MyCommentsService {
    companion object {
        var myCommentsServiceApi = RetrofitApiClient.getRetrofitApiClient(OxygenConstants.getMetypeBaseURL()).create(MyCommentsServiceAPI::class.java)
//                var myCommentsServiceApi = RetrofitApiClient.getRetrofitApiClient("http://demo7854945.mockable.io/").create(MyCommentsServiceAPI::class.java)
        var myCommentsServiceInstance: MyCommentsService? = null

        @Synchronized
        fun getInstance(): MyCommentsService {
            if (null == myCommentsServiceInstance) {
                myCommentsServiceInstance = MyCommentsService()
            }
            return myCommentsServiceInstance!!
        }
    }

    @Synchronized
    fun getMyComments(accountId: String?, jwt: String?, mPageNumber: Int) =
        myCommentsServiceApi.getMyComments(
            OxygenConstants.getMetypeBaseURL(true),
            accountId,
            jwt,
            mPageNumber,
            OxygenConstants.COMMENTS_LIMIT
        )
}