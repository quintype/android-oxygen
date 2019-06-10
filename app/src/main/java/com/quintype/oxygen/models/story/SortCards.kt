package com.quintype.oxygen.models.story

import java.util.*

/**
 * Created TemplateCollectionWithRx by rakshith on 9/11/18.
 */

class SortCards : Comparator<Card> {
    override fun compare(card1: Card, card2: Card): Int {
        return (card1.cardAddedAt - card1.cardAddedAt).toInt()// Comparing cardAddedAt value
    }
}