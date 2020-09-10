package dev.fritz2.styling.params

import dev.fritz2.styling.Property
import dev.fritz2.styling.ZIndices
import dev.fritz2.styling.theme
import kotlinx.coroutines.ExperimentalCoroutinesApi


internal const val topKey = "top: "
internal const val rightKey = "right: "
internal const val bottomKey = "bottom: "
internal const val leftKey = "left: "

object PositionStyles : OverflowBaseStyles("position: ") {
    const val static: Property = "static"
    const val relative: Property = "relative"
    const val absolute: Property = "absolute"
    const val sticky: Property = "sticky "
    const val fixed: Property = "fixed"
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
    fun position(value: PositionStyles.() -> Property) = property(PositionStyles, value)

    fun position(
        sm: (PositionStyles.() -> Property)? = null,
        md: (PositionStyles.() -> Property)? = null,
        lg: (PositionStyles.() -> Property)? = null,
        xl: (PositionStyles.() -> Property)? = null
    ) =
        property(PositionStyles, sm, md, lg, xl)

    /*
     * top
     */
    fun top(value: Property) = property(topKey, value)

    fun top(
        sm: Property? = null,
        md: Property? = null,
        lg: Property? = null,
        xl: Property? = null
    ) =
        property(topKey, sm, md, lg, xl)

    /*
     * right
     */
    fun right(value: Property) = property("right: ", value)

    fun right(
        sm: Property? = null,
        md: Property? = null,
        lg: Property? = null,
        xl: Property? = null
    ) =
        property(rightKey, sm, md, lg, xl)

    /*
     * bottom
     */
    fun bottom(value: Property) = property("bottom: ", value)

    fun bottom(
        sm: Property? = null,
        md: Property? = null,
        lg: Property? = null,
        xl: Property? = null
    ) =
        property(bottomKey, sm, md, lg, xl)

    /*
     * left
     */
    fun left(value: Property) = property("left: ", value)

    fun left(
        sm: Property? = null,
        md: Property? = null,
        lg: Property? = null,
        xl: Property? = null
    ) =
        property(leftKey, sm, md, lg, xl)

}