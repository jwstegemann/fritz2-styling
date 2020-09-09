package dev.fritz2.styling.params

import dev.fritz2.styling.Fonts
import dev.fritz2.styling.Property
import dev.fritz2.styling.theme


object TextTransforms : PropertyValues {
    override val key = "text-transform: "

    const val none: Property = "none"
    const val capitalize: Property = "capitalize"
    const val uppercase: Property = "uppercase"
    const val lowercase: Property = "lowercase"
    const val initial: Property = "initial"
    const val inherit: Property = "inherit"
}

object FontStyles : PropertyValues {
    override val key = "font-style: "

    const val normal: Property = "normal"
    const val italic: Property = "italic"
    const val oblique: Property = "oblique"
    const val initial: Property = "initial"
    const val inherit: Property = "inherit"
}

object TextAligns : PropertyValues {
    override val key = "text-align: "

    const val left: Property = "left"
    const val right: Property = "right"
    const val center: Property = "center"
    const val justify: Property = "justify"
    const val initial: Property = "initial"
    const val inherit: Property = "inherit"
}

object FontWeights : PropertyValues {
    override val key = "font-weight: "

    const val normal: Property = "normal"
    const val bold: Property = "bold"
    const val medium: Property = "500"
    const val semiBold: Property = "600"
    const val bolder: Property = "bolder"
    const val lighter: Property = "lighter"
    const val initial: Property = "initial"
    const val inherit: Property = "inherit"
}

internal const val fontSizeKey = "font-size: "
internal const val letterSpacingKey = "letter-spacing: "
internal const val lineHeightKey = "line-height: "


//FIXME: make abstract class to allow inline?
interface Typo : StyleParams {

    /*
     * fontFamily
    */
    fun fontFamily(value: Fonts.() -> Property) = property(fontSizeKey, theme().fonts, value)

    fun fontFamily(
        sm: (Fonts.() -> Property)? = null,
        md: (Fonts.() -> Property)? = null,
        lg: (Fonts.() -> Property)? = null,
        xl: (Fonts.() -> Property)? = null
    ) =
        property(fontSizeKey, theme().fonts, sm, md, lg, xl)

    /*
     * font-size
     */
    fun fontSize(value: ScaledValueProperty) = property(fontSizeKey, theme().fontSizes, value)

    fun fontSize(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) =
        property(fontSizeKey, theme().fontSizes, sm, md, lg, xl)

    /*
     * font-weight
     */
    fun fontWeight(value: FontWeights.() -> Property) = property(FontWeights, value)

    fun fontWeight(
        sm: (FontWeights.() -> Property)? = null,
        md: (FontWeights.() -> Property)? = null,
        lg: (FontWeights.() -> Property)? = null,
        xl: (FontWeights.() -> Property)? = null,
    ) =
        property(FontWeights, sm, md, lg, xl)

    /*
     * line-height
     */
    fun lineHeight(value: ScaledValueProperty) = property(lineHeightKey, theme().lineHeights, value)

    fun lineHeight(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) =
        property(lineHeightKey, theme().lineHeights, sm, md, lg, xl)

    /*
     * letter-spacing
     */
    fun letterSpacing(value: ScaledValueProperty) = property(letterSpacingKey, theme().letterSpacings, value)

    fun letterSpacing(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) =
        property(letterSpacingKey, theme().letterSpacings, sm, md, lg, xl)

    /*
     * text-align
     */
    fun textAlign(value: TextAligns.() -> Property) = property(TextAligns, value)

    fun textAlign(
        sm: (TextAligns.() -> Property)? = null,
        md: (TextAligns.() -> Property)? = null,
        lg: (TextAligns.() -> Property)? = null,
        xl: (TextAligns.() -> Property)? = null,
    ) =
        property(TextAligns, sm, md, lg, xl)

    /*
     * text-transform
     */
    fun textTransform(value: TextTransforms.() -> Property) = property(TextTransforms, value)

    fun textTransform(
        sm: (TextTransforms.() -> Property)? = null,
        md: (TextTransforms.() -> Property)? = null,
        lg: (TextTransforms.() -> Property)? = null,
        xl: (TextTransforms.() -> Property)? = null,
    ) =
        property(TextTransforms, sm, md, lg, xl)

    /*
     * font-style
     */
    fun fontStyle(value: FontStyles.() -> Property) = property(FontStyles, value)

    fun fontStyle(
        sm: (FontStyles.() -> Property)? = null,
        md: (FontStyles.() -> Property)? = null,
        lg: (FontStyles.() -> Property)? = null,
        xl: (FontStyles.() -> Property)? = null,
    ) =
        property(FontStyles, sm, md, lg, xl)
}