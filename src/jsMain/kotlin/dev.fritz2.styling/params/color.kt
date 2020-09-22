package dev.fritz2.styling.params

import dev.fritz2.styling.Colors
import dev.fritz2.styling.Property
import dev.fritz2.styling.theme
import kotlinx.coroutines.ExperimentalCoroutinesApi

internal const val colorKey = "color: "
internal const val opacityKey = "opacity: "

@ExperimentalCoroutinesApi
interface Color : StyleParams {
    /*
     * color
     */
    fun color(value: Colors.() -> Property) = property(colorKey, theme().colors, value)

    fun color(
        sm: (Colors.() -> Property)? = null,
        md: (Colors.() -> Property)? = null,
        lg: (Colors.() -> Property)? = null,
        xl: (Colors.() -> Property)? = null
    ) =
        property(colorKey, theme().colors, sm, md, lg, xl)


    /*
     * opacity
     */
    fun opacity(value: WeightedValueProperty) = property(opacityKey, theme().opacities, value)

    fun opacity(
        sm: WeightedValueProperty? = null,
        md: WeightedValueProperty? = null,
        lg: WeightedValueProperty? = null,
        xl: WeightedValueProperty? = null
    ) =
        property(opacityKey, theme().opacities, sm, md, lg, xl)

}