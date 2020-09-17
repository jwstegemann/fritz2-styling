package dev.fritz2.styling.params

import dev.fritz2.styling.Property
import dev.fritz2.styling.asKey

/*
 * Central Alignment Contexts, inspired by https://drafts.csswg.org/css-align-3/
 */

object JustifyContentValues : PropertyValues {
    override val key = "justify-content".asKey

    const val start: Property = "start"
    const val end: Property = "end"
    const val flexStart: Property = "flex-start"
    const val flexEnd: Property = "flex-end"
    const val center: Property = "center"
    const val spaceBetween: Property = "space-between"
    const val spaceAround: Property = "space-around"
    const val spaceEvenly: Property = "space-evenly"
}

object AlignItemsValues : PropertyValues {
    override val key = "align-items".asKey

    const val start: Property = "start"
    const val end: Property = "end"
    const val flexStart: Property = "flex-start"
    const val flexEnd: Property = "flex-end"
    const val selfStart: Property = "self-start"
    const val selfEnd: Property = "self-end"
    const val center: Property = "center"
    const val stretch: Property = "stretch"
    const val baseline: Property = "baseline"
}

object AlignContentValues : PropertyValues {
    override val key = "align-content".asKey

    const val start: Property = "start"
    const val end: Property = "end"
    const val flexStart: Property = "flex-start"
    const val flexEnd: Property = "flex-end"
    const val center: Property = "center"
    const val spaceBetween: Property = "space-between"
    const val spaceAround: Property = "space-around"
    const val spaceEvenly: Property = "space-evenly"
}

interface Alignment {
    fun justifyContent(value: JustifyContentValues.() -> Property)
    fun alignItems(value: AlignItemsValues.() -> Property)
    fun alignContent(value: AlignContentValues.() -> Property)
}

class AlignmentImpl(
    styleParam: StyleParams,
    private val target: StringBuilder
) : Alignment, StyleParams by styleParam {
    override fun justifyContent(value: JustifyContentValues.() -> Property) =
        property(JustifyContentValues.key, JustifyContentValues.value(), target)

    override fun alignItems(value: AlignItemsValues.() -> Property) =
        property(AlignItemsValues.key, AlignItemsValues.value(), target)

    override fun alignContent(value: AlignContentValues.() -> Property) =
        property(AlignContentValues.key, AlignContentValues.value(), target)
}

object SelfAlignItemsValues : PropertyValues {
    override val key = "align-self".asKey

    const val start: Property = "start"
    const val end: Property = "end"
    const val flexStart: Property = "flex-start"
    const val flexEnd: Property = "flex-end"
    const val center: Property = "center"
    const val stretch: Property = "stretch"
    const val baseline: Property = "baseline"
}

interface SelfAlignment {
    fun alignSelf(value: SelfAlignItemsValues.() -> Property)
}

class SelfAlignmentImpl(
    styleParam: StyleParams,
    private val target: StringBuilder
) : SelfAlignment, StyleParams by styleParam {
    override fun alignSelf(value: SelfAlignItemsValues.() -> Property) =
        property(SelfAlignItemsValues.key, SelfAlignItemsValues.value(), target)
}