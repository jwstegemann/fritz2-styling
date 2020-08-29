package dev.fritz2.styling.params

import dev.fritz2.styling.Property

interface Border : StyleParams {
    fun border(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border: $it;" }

    fun borderWidth(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-width: $it;" }

    fun borderStyle(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-style: $it;" }

    fun borderColor(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-color: $it;" }

    fun borderRadius(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-radius: $it;" }

    fun borderTop(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-top: $it;" }

    fun borderTopWidth(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-top-width: $it;" }

    fun borderTopStyle(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-top-style: $it;" }

    fun borderTopColor(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-top-color: $it;" }

    fun borderTopLeftRadius(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-top-left-radius: $it;" }

    fun borderTopRightRadius(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-top-right-radius: $it;" }

    fun borderRight(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-right: $it;" }

    fun borderRightWidth(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-right-width: $it;" }

    fun borderRightStyle(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-right-style: $it;" }

    fun borderRightColor(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-right-color: $it;" }

    fun borderBottom(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-bottom: $it;" }

    fun borderBottomWidth(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-bottom-width: $it;" }

    fun borderBottomStyle(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-bottom-style: $it;" }

    fun borderBottomColor(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-bottom-color: $it;" }

    fun borderBottomLeftRadius(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-bottom-left-radius: $it;" }

    fun borderBottomRightRadius(
        sm: Property? = null,
        md: Property? = null,
        lg: Property? = null,
        xl: Property? = null
    ) = property(sm, md, lg, xl) { "border-bottom-right-radius: $it;" }

    fun borderLeft(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-left: $it;" }

    fun borderLeftWidth(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-left-width: $it;" }

    fun borderLeftStyle(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-left-style: $it;" }

    fun borderLeftColor(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-left-color: $it;" }

    fun borderVertical(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-left: $it; border-right: $it;" }

    fun borderHorizontal(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "border-top: $it; border-bottom: $it;" }

}