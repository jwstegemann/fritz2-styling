package dev.fritz2.styling.params

import dev.fritz2.styling.Colors
import dev.fritz2.styling.Property
import dev.fritz2.styling.theme

internal const val colorKey = "color: "
internal const val bgColorKey = "background-color: "
internal const val opacityKey = "opacity: "

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
     * bgColor
     */
    fun bgColor(value: Colors.() -> Property) = property(bgColorKey, theme().colors, value)

    fun bgColor(
        sm: (Colors.() -> Property)? = null,
        md: (Colors.() -> Property)? = null,
        lg: (Colors.() -> Property)? = null,
        xl: (Colors.() -> Property)? = null
    ) =
        property(bgColorKey, theme().colors, sm, md, lg, xl)


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

    /*
    fun color(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "color: $it;" }

    fun bgColor(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "background-color: $it;" }

    fun opacity(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "opacity: $it;" }

     */
}