package dev.fritz2.styling.params

import dev.fritz2.styling.theme
import kotlinx.coroutines.ExperimentalCoroutinesApi

internal const val marginKey = "margin: "
internal const val marginTopKey = "margin-top: "
internal const val marginRightKey = "margin-right: "
internal const val marginBottomKey = "margin-bottom: "
internal const val marginLeftKey = "margin-left: "

internal const val paddingKey = "padding: "
internal const val paddingTopKey = "padding-top: "
internal const val paddingRightKey = "padding-right: "
internal const val paddingBottomKey = "padding-bottom: "
internal const val paddingLeftKey = "padding-left: "


class Margins(val styleParams: StyleParams, private val target: StringBuilder) : StyleParams by styleParams {
    fun top(value: ScaledValueProperty) = property(marginTopKey, theme().space, value, target)
    fun left(value: ScaledValueProperty) = property(marginLeftKey, theme().space, value, target)
    fun bottom(value: ScaledValueProperty) = property(marginBottomKey, theme().space, value, target)
    fun right(value: ScaledValueProperty) = property(marginRightKey, theme().space, value, target)
}

@ExperimentalCoroutinesApi
interface Space : StyleParams {
    /*
     * margin
     */
    fun margin(value: ScaledValueProperty) = property(marginKey, theme().space, value)

    fun margin(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) =
        property(marginKey, theme().space, sm, md, lg, xl)

    /*
     * margins
     */
    fun margins(value: Margins.() -> Unit) {
        Margins(this, smProperties).value()
    }

    fun margins(
        sm: (Margins.() -> Unit)? = null,
        md: (Margins.() -> Unit)? = null,
        lg: (Margins.() -> Unit)? = null,
        xl: (Margins.() -> Unit)? = null
    ) {
        if (sm != null) Margins(this, smProperties).sm()
        if (md != null) Margins(this, mdProperties).md()
        if (lg != null) Margins(this, lgProperties).lg()
        if (xl != null) Margins(this, xlProperties).xl()
    }

    /*
     * marginTop
     */
    fun marginTop(value: ScaledValueProperty) = property(marginTopKey, theme().space, value)

    fun marginTop(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) =
        property(marginTopKey, theme().space, sm, md, lg, xl)

    /*
     * marginRight
     */
    fun marginRight(value: ScaledValueProperty) = property(marginRightKey, theme().space, value)

    fun marginRight(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) =
        property(marginRightKey, theme().space, sm, md, lg, xl)

    /*
     * marginBottom
     */
    fun marginBottom(value: ScaledValueProperty) = property(marginBottomKey, theme().space, value)

    fun marginBottom(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) =
        property(marginBottomKey, theme().space, sm, md, lg, xl)

    /*
     * marginLeft
     */
    fun marginLeft(value: ScaledValueProperty) = property(marginLeftKey, theme().space, value)

    fun marginLeft(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) =
        property(marginLeftKey, theme().space, sm, md, lg, xl)

    /*
     * marginHorizontal
     */
    fun marginHorizontal(value: ScaledValueProperty) {
        property(marginLeftKey, theme().space, value)
        property(marginRightKey, theme().space, value)
    }

    fun marginHorizontal(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) {
        property(marginLeftKey, theme().space, sm, md, lg, xl)
        property(marginRightKey, theme().space, sm, md, lg, xl)
    }

    /*
     * marginVertical
     */
    fun marginVertical(value: ScaledValueProperty) {
        property(marginTopKey, theme().space, value)
        property(marginBottomKey, theme().space, value)
    }

    fun marginVertical(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) {
        property(marginTopKey, theme().space, sm, md, lg, xl)
        property(marginBottomKey, theme().space, sm, md, lg, xl)
    }

    /*
     * padding
     */
    fun padding(value: ScaledValueProperty) = property(paddingKey, theme().space, value)

    fun padding(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) =
        property(paddingKey, theme().space, sm, md, lg, xl)

    /*
     * paddingTop
     */
    fun paddingTop(value: ScaledValueProperty) = property(paddingTopKey, theme().space, value)

    fun paddingTop(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) =
        property(paddingTopKey, theme().space, sm, md, lg, xl)

    /*
     * paddingRight
     */
    fun paddingRight(value: ScaledValueProperty) = property(paddingRightKey, theme().space, value)

    fun paddingRight(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) =
        property(paddingRightKey, theme().space, sm, md, lg, xl)

    /*
     * paddingBottom
     */
    fun paddingBottom(value: ScaledValueProperty) = property(paddingBottomKey, theme().space, value)

    fun paddingBottom(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) =
        property(paddingBottomKey, theme().space, sm, md, lg, xl)

    /*
     * paddingLeft
     */
    fun paddingLeft(value: ScaledValueProperty) = property(paddingLeftKey, theme().space, value)

    fun paddingLeft(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) =
        property(paddingLeftKey, theme().space, sm, md, lg, xl)

    /*
     * paddingHorizontal
     */
    fun paddingHorizontal(value: ScaledValueProperty) {
        property(paddingLeftKey, theme().space, value)
        property(paddingRightKey, theme().space, value)
    }

    fun paddingHorizontal(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) {
        property(paddingLeftKey, theme().space, sm, md, lg, xl)
        property(paddingRightKey, theme().space, sm, md, lg, xl)
    }

    /*
     * paddingVertical
     */
    fun paddingVertical(value: ScaledValueProperty) {
        property(paddingTopKey, theme().space, value)
        property(paddingBottomKey, theme().space, value)
    }

    fun paddingVertical(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) {
        property(paddingTopKey, theme().space, sm, md, lg, xl)
        property(paddingBottomKey, theme().space, sm, md, lg, xl)
    }
}
