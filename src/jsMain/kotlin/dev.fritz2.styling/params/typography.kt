package dev.fritz2.styling.params

import dev.fritz2.styling.Property
import dev.fritz2.styling.ScaledValue
import dev.fritz2.styling.theme

typealias FontStyle = Property
typealias FontWeight = Property

class FontWeights {
    val bold: FontWeight = "bold"
    val bolder: FontWeight = "bolder"
    val lighter: FontWeight = "lighter"
}

class FontStyles {
    val italic: FontStyle = "italic"
    val oblique: FontStyle = "oblique"
}


typealias  TextTransform = String

object TextTransforms {
    const val none = "text-transform: none;"
    const val capitalize = "text-transform: capitalize;"
    const val uppercase = "text-transform: uppercase;"
    const val lowercase = "text-transform: lowercase;"
}

interface Typo : StyleParams {

    fun fontFamily(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "font-family: $it;" }

    fun fontSize(value: ScaledValue<Property>.() -> Property) {
        smProperties.append("font-size: ", theme().fontSizes.value(), ";")
    }

    fun fontSize(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "font-size: $it;" }

    fun fontWeight(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "font-weight: $it;" }

    fun lineHeight(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "line-height: $it;" }

    fun letterSpacing(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "letter-spacing: $it;" }

    fun textAlign(sm: Position? = null, md: Position? = null, lg: Position? = null, xl: Position? = null) =
        property(sm, md, lg, xl) { "text-align: $it;" }

    fun textTransform(value: TextTransforms.() -> TextTransform) {
        smProperties.append(TextTransforms.value())
    }

    fun textTransform(
        sm: (TextTransforms.() -> TextTransform)? = null,
        md: (TextTransforms.() -> TextTransform)? = null,
        lg: (TextTransforms.() -> TextTransform)? = null,
        xl: (TextTransforms.() -> TextTransform)? = null
    ) {
        if (sm != null) smProperties.append(TextTransforms.sm())
        if (md != null) mdProperties.append(TextTransforms.md())
        if (lg != null) lgProperties.append(TextTransforms.lg())
        if (xl != null) xlProperties.append(TextTransforms.xl())
    }

    fun fontStyle(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "font-style: $it;" }
}