package com.quintype.oxygen.models

import com.quintype.oxygen.models.collection.AssociatedMetadata
import com.quintype.oxygen.models.collection.BreakingNewsModel
import com.quintype.oxygen.models.collection.Metadata
import com.quintype.oxygen.models.story.Story

/**
 * Created TemplateCollectionWithRx by rakshith on 7/23/18.
 */

class BulkTableModel(
    var slug: String?,
    var story: Story?,
    var outerCollectionName: String?,
    var mSummary: String?,
    var mOuterCollectionAssociatedMetadata: AssociatedMetadata?,
    var outerCollectionTemplate: String?,
    var outerCollectionInnerSlug: String?,
    var outerCollectionInnerItem: ArrayList<InnerCollectionItemModel>?,
    var breakingNewsResponse: BreakingNewsModel?,
    var mMetaData: Metadata,
    var mTotalItemCount: Int?
)