package dev.fritz2.styling.params

import dev.fritz2.styling.Property

interface Typo : StyleParams {
    fun fontFamily(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "font-family: $it;" }

    fun fontSize(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "font-size: $it;" }

    fun fontWeight(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "font-weight: $it;" }

    fun lineHeight(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "line-height: $it;" }

    fun letterSpacing(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "letter-spacing: $it;" }

    fun textAlign(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "text-align: $it;" }

    fun textTransform(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "text-transform: $it;" }

    fun fontStyle(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "font-style: $it;" }

}