package com.quintype.oxygen.models

import android.content.Context
import android.os.Parcelable
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class NavMenuGroup(
    var position: Int = 0,
    var isSelected: Boolean = false,
    var menuItem: NavMenu? = null,
    private var mSubsections: ArrayList<NavMenu> = ArrayList(),
    var dummyValue: String? = null,
    private var mImageResource: Int = -1
) : ParentListItem, Parcelable {

    fun addSubsection(subsection: NavMenu) {
        mSubsections.add(subsection)
    }

    fun getmImageResource(): Int {
        return mImageResource
    }

    fun setmImageResource(mImageResource: Int) {
        this.mImageResource = mImageResource
    }

    fun getName(context: Context): String {
        return menuItem?.title!!
    }

    override fun getChildItemList(): List<NavMenu>? {
        return mSubsections
    }

    override fun isInitiallyExpanded(): Boolean {
        return false
    }
}