package dev.fritz2.styling.params

import dev.fritz2.styling.Property
import dev.fritz2.styling.ZIndices
import dev.fritz2.styling.theme
import kotlinx.coroutines.ExperimentalCoroutinesApi

internal const val positionKey = "position: "

@ExperimentalCoroutinesApi
class PositionContext(
    val styleParams: StyleParams,
    private val target: StringBuilder
) : StyleParams by styleParams {
    fun top(value: ScaledValueProperty) = property("top: ", theme().space, value, target)
    fun left(value: ScaledValueProperty) = property("left: ", theme().space, value, target)
    fun bottom(value: ScaledValueProperty) = property("bottom: ", theme().space, value, target)
    fun right(value: ScaledValueProperty) = property("right", theme().space, value, target)

    fun vertical(value: ScaledValueProperty) {
        property("top: ", theme().space, value, target)
        property("bottom: ", theme().space, value, target)
    }

    fun horizontal(value: ScaledValueProperty) {
        property("left: ", theme().space, value, target)
        property("right: ", theme().space, value, target)
    }

    val static: Property = "static"
    val relative: Property = "relative"
    val absolute: Property = "absolute"
    val sticky: Property = "sticky "
    val fixed: Property = "fixed"
}

@ExperimentalCoroutinesApi
interface Position : StyleParams {

    /*
     * z-index
     */
    fun zIndex(value: ZIndicesProperty) = property(ZIndices.key, theme().zIndices, value)

    fun zIndex(
        sm: ZIndicesProperty? = null,
        md: ZIndicesProperty? = null,
        lg: ZIndicesProperty? = null,
        xl: ZIndicesProperty? = null
    ) =
        property(ZIndices.key, theme().zIndices, sm, md, lg, xl)

    /*
     * position
     */
    fun position(value: PositionContext.() -> Property) =
        property(positionKey, PositionContext(this, smProperties).value())

    fun position(
        sm: (PositionContext.() -> Property)? = null,
        md: (PositionContext.() -> Property)? = null,
        lg: (PositionContext.() -> Property)? = null,
        xl: (PositionContext.() -> Property)? = null
    ) {
        if (sm != null) property(positionKey, PositionContext(this, smProperties).sm())
        if (md != null) property(positionKey, PositionContext(this, mdProperties).md())
        if (lg != null) property(positionKey, PositionContext(this, lgProperties).lg())
        if (xl != null) property(positionKey, PositionContext(this, xlProperties).xl())
    }

}