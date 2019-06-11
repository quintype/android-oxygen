package com.quintype.oxygen.models.entities

import com.google.gson.annotations.SerializedName

/**
 * Magazine config API response.
 */
data class EntityInfoFromConfig(
    @SerializedName("magazines")
    val magazines: List<MagazinesItem>? = ArrayList()
)

data class MagazinesItem(
    @SerializedName("entity")
    val entity: Entity
)

data class Entity(
    @SerializedName("actual-price")
    val actualPrice: String = "",
    @SerializedName("code")
    val code: String = "",
    @SerializedName("updated-at")
    val updatedAt: Long = 0,
    @SerializedName("description")
    val description: String = "",
    @SerializedName("last-updated-by")
    val lastUpdatedBy: Int = 0,
    @SerializedName("type")
    val type: String = "",
    @SerializedName("discounted-price")
    val discountedPrice: String = "",
    @SerializedName("duration")
    val duration: String = "",
    @SerializedName("entity-type-id")
    val entityTypeId: Int = 0,
    @SerializedName("publisher-id")
    val publisherId: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("created-at")
    val createdAt: Long = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("slug")
    val slug: String = "",
    @SerializedName("created-by")
    val createdBy: Int = 0
)