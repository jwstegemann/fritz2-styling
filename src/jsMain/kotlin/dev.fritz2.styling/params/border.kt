package dev.fritz2.styling.params

import dev.fritz2.styling.Colors
import dev.fritz2.styling.Property
import dev.fritz2.styling.Thickness
import dev.fritz2.styling.theme
import kotlinx.coroutines.ExperimentalCoroutinesApi

object BorderStyleValues : PropertyValues {
    override val key = "border-style: "

    const val none: Property = "none"
    const val hidden: Property = "hidden"
    const val dotted: Property = "dotted"
    const val dashed: Property = "dashed"
    const val solid: Property = "solid"
    const val double: Property = "double"
    const val groove: Property = "groove"
    const val ridge: Property = "ridge"
    const val inset: Property = "inset"
    const val outset: Property = "outset"
    const val initial: Property = "initial"
    const val inherit: Property = "inherit"
}


const val borderWidthKey = "border-width: "
const val borderStyleKey = "border-style: "
const val borderColorKey = "border-color: "

const val borderTopWidthKey = "border-top-width: "
const val borderTopStyleKey = "border-top-style: "
const val borderTopColorKey = "border-top-color: "

const val borderRightWidthKey = "border-right-width: "
const val borderRightStyleKey = "border-right-style: "
const val borderRightColorKey = "border-right-color: "

const val borderBottomWidthKey = "border-bottom-width: "
const val borderBottomStyleKey = "border-bottom-style: "
const val borderBottomColorKey = "border-bottom-color: "

const val borderLeftWidthKey = "border-left-width: "
const val borderLeftStyleKey = "border-left-style: "
const val borderLeftColorKey = "border-left-color: "

const val borderRadiusKey = "border-radius: "
const val borderTopLeftRadiusKey = "border-top-left-radius: "
const val borderTopRightRadiusKey = "border-top-right-radius: "
const val borderBottomRightRadiusKey = "border-bottom-right-radius: "
const val borderBottomLeftRadiusKey = "border-bottom-left-radius: "

@ExperimentalCoroutinesApi
class BorderContext(
    private val widthKey: String,
    private val styleKey: String,
    private val colorKey: String,
    val styleParams: StyleParams,
    private val target: StringBuilder
) : StyleParams by styleParams {
    fun width(value: Thickness<Property>.() -> Property) = property(widthKey, theme().borderWidths, value, target)

    fun style(value: BorderStyleValues.() -> Property) =
        property(styleKey, BorderStyleValues.value(), smProperties)

    fun color(value: Colors.() -> Property) = property(colorKey, theme().colors, value, target)
}

@ExperimentalCoroutinesApi
class BordersContext(
    val styleParams: StyleParams,
    private val target: StringBuilder
) : StyleParams by styleParams {
    fun top(value: BorderContext.() -> Unit) =
        BorderContext(borderTopWidthKey, borderTopStyleKey, borderTopColorKey, this, target).value()

    fun right(value: BorderContext.() -> Unit) =
        BorderContext(borderRightWidthKey, borderRightStyleKey, borderRightColorKey, this, target).value()

    fun bottom(value: BorderContext.() -> Unit) =
        BorderContext(borderBottomWidthKey, borderBottomStyleKey, borderBottomColorKey, this, target).value()

    fun left(value: BorderContext.() -> Unit) =
        BorderContext(borderLeftWidthKey, borderLeftStyleKey, borderLeftColorKey, this, target).value()
}

//FIXME: inline funs?
@ExperimentalCoroutinesApi
class RadiiContext(
    val styleParams: StyleParams,
    private val target: StringBuilder
) : StyleParams by styleParams {
    fun topLeft(value: ScaledValueProperty) = property(borderTopLeftRadiusKey, theme().radii, value, target)
    fun topRight(value: ScaledValueProperty) = property(borderTopRightRadiusKey, theme().radii, value, target)
    fun bottomRight(value: ScaledValueProperty) = property(borderBottomRightRadiusKey, theme().radii, value, target)
    fun bottomLeft(value: ScaledValueProperty) = property(borderBottomLeftRadiusKey, theme().radii, value, target)
}


interface Border : StyleParams {
    /*
     * border
     */
    fun border(value: BorderContext.() -> Unit) =
        BorderContext(borderWidthKey, borderStyleKey, borderColorKey, this, smProperties).value()

    fun border(
        sm: (BorderContext.() -> Unit)? = null,
        md: (BorderContext.() -> Unit)? = null,
        lg: (BorderContext.() -> Unit)? = null,
        xl: (BorderContext.() -> Unit)? = null
    ) {
        if (sm != null) BorderContext(borderWidthKey, borderStyleKey, borderColorKey, this, smProperties).sm()
        if (md != null) BorderContext(borderWidthKey, borderStyleKey, borderColorKey, this, mdProperties).md()
        if (lg != null) BorderContext(borderWidthKey, borderStyleKey, borderColorKey, this, lgProperties).lg()
        if (xl != null) BorderContext(borderWidthKey, borderStyleKey, borderColorKey, this, xlProperties).xl()
    }

    /*
     * borders
     */
    fun borders(value: BordersContext.() -> Unit) = BordersContext(this, smProperties).value()

    fun borders(
        sm: (BordersContext.() -> Unit)? = null,
        md: (BordersContext.() -> Unit)? = null,
        lg: (BordersContext.() -> Unit)? = null,
        xl: (BordersContext.() -> Unit)? = null
    ) {
        if (sm != null) BordersContext(this, smProperties).sm()
        if (md != null) BordersContext(this, mdProperties).md()
        if (lg != null) BordersContext(this, lgProperties).lg()
        if (xl != null) BordersContext(this, xlProperties).xl()
    }

    /*
     * radius
     */
    fun radius(value: ScaledValueProperty) =
        property(borderRadiusKey, theme().radii, value, smProperties)

    fun radius(
        sm: (ScaledValueProperty)? = null,
        md: (ScaledValueProperty)? = null,
        lg: (ScaledValueProperty)? = null,
        xl: (ScaledValueProperty)? = null
    ) {
        if (sm != null) property(borderRadiusKey, theme().radii, sm, smProperties)
        if (md != null) property(borderRadiusKey, theme().radii, md, mdProperties)
        if (lg != null) property(borderRadiusKey, theme().radii, lg, lgProperties)
        if (xl != null) property(borderRadiusKey, theme().radii, xl, xlProperties)
    }

    /*
     * radii
     */
    fun radii(value: RadiiContext.() -> Unit) = RadiiContext(this, smProperties).value()

    fun radii(
        sm: (RadiiContext.() -> Unit)? = null,
        md: (RadiiContext.() -> Unit)? = null,
        lg: (RadiiContext.() -> Unit)? = null,
        xl: (RadiiContext.() -> Unit)? = null
    ) {
        if (sm != null) RadiiContext(this, smProperties).sm()
        if (md != null) RadiiContext(this, mdProperties).md()
        if (lg != null) RadiiContext(this, lgProperties).lg()
        if (xl != null) RadiiContext(this, xlProperties).xl()
    }


/*
    fun borderRadius(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-radius: $it;" }

    fun borderTopLeftRadius(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-top-left-radius: $it;" }

    fun borderTopRightRadius(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-top-right-radius: $it;" }


    fun borderBottomLeftRadius(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-bottom-left-radius: $it;" }

    fun borderBottomRightRadius(
        sm: Property? = null,
        md: Property? = null,
        lg: Property? = null,
        xl: Property? = null
    ) = property(sm, md, lg, xl) { "border-bottom-right-radius: $it;" }

     */

}