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

interface Alignment : StyleParams {
    /*
     * justifyContent
     */

    fun justifyContent(value: JustifyContentValues.() -> Property) =
        property(JustifyContentValues.key, JustifyContentValues.value(), smProperties)

    fun justifyContent(
        sm: (JustifyContentValues.() -> Property)? = null,
        md: (JustifyContentValues.() -> Property)? = null,
        lg: (JustifyContentValues.() -> Property)? = null,
        xl: (JustifyContentValues.() -> Property)? = null
    ) {
        if (sm != null) property(JustifyContentValues.key, JustifyContentValues.sm(), smProperties)
        if (md != null) property(JustifyContentValues.key, JustifyContentValues.md(), mdProperties)
        if (lg != null) property(JustifyContentValues.key, JustifyContentValues.lg(), lgProperties)
        if (xl != null) property(JustifyContentValues.key, JustifyContentValues.xl(), xlProperties)
    }

    /*
     * alignItems
     */

    fun alignItems(value: AlignItemsValues.() -> Property) =
        property(AlignItemsValues.key, AlignItemsValues.value(), smProperties)

    fun alignItems(
        sm: (AlignItemsValues.() -> Property)? = null,
        md: (AlignItemsValues.() -> Property)? = null,
        lg: (AlignItemsValues.() -> Property)? = null,
        xl: (AlignItemsValues.() -> Property)? = null
    ) {
        if (sm != null) property(AlignItemsValues.key, AlignItemsValues.sm(), smProperties)
        if (md != null) property(AlignItemsValues.key, AlignItemsValues.md(), mdProperties)
        if (lg != null) property(AlignItemsValues.key, AlignItemsValues.lg(), lgProperties)
        if (xl != null) property(AlignItemsValues.key, AlignItemsValues.xl(), xlProperties)
    }

    /*
     * alignContent
     */

    fun alignContent(value: AlignContentValues.() -> Property) =
        property(AlignContentValues.key, AlignContentValues.value(), smProperties)

    fun alignContent(
        sm: (AlignContentValues.() -> Property)? = null,
        md: (AlignContentValues.() -> Property)? = null,
        lg: (AlignContentValues.() -> Property)? = null,
        xl: (AlignContentValues.() -> Property)? = null
    ) {
        if (sm != null) property(AlignContentValues.key, AlignContentValues.sm(), smProperties)
        if (md != null) property(AlignContentValues.key, AlignContentValues.md(), mdProperties)
        if (lg != null) property(AlignContentValues.key, AlignContentValues.lg(), lgProperties)
        if (xl != null) property(AlignContentValues.key, AlignContentValues.xl(), xlProperties)
    }
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