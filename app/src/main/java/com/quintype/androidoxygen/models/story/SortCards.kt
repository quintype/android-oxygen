package com.quintype.androidoxygen.models.story

import com.quintype.androidoxygen.models.story.Card
import java.util.Comparator

/**
 * Created TemplateCollectionWithRx by rakshith on 9/11/18.
 */

class SortCards : Comparator<Card> {
    override fun compare(card1: Card, card2: Card): Int {
        return (card1.cardAddedAt - card1.cardAddedAt).toInt()// Comparing cardAddedAt value
    }
}