package com.quintype.oxygen.models

import com.google.gson.Gson
import com.quintype.oxygen.models.collection.CollectionItem
import com.quintype.oxygen.models.collection.CollectionResponse


/**
 *  This model includes the innerCollectionItem and respective response for those items(Making an API call to get the response for innerItem)
 */
open class InnerCollectionItemModel(
    var mCollectionItem: CollectionItem,
    var mCollectionInnerResponse: CollectionResponse?
) {

    /**
     * duplicate the object
     */
    fun clone(): InnerCollectionItemModel {
        val stringProject = Gson().toJson(this, InnerCollectionItemModel::class.java)
        return Gson().fromJson<InnerCollectionItemModel>(stringProject, InnerCollectionItemModel::class.java)
    }
}