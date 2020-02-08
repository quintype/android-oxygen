package com.quintype.oxygen.database.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.quintype.oxygen.TABLE_SEARCH

@Entity(tableName = TABLE_SEARCH)
data class SearchEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "search_term") var searchTerm: String
)