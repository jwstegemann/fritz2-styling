package dev.fritz2.styling.params

import dev.fritz2.styling.Property

interface Flexbox : StyleParams {
    fun alignItems(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "align-items: $it;" }

    fun alignContent(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "align-content: $it;" }

    fun justifyItems(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "justify-items: $it;" }

    fun justifyContent(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "justify-content: $it;" }

    fun flexWrap(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "flex-wrap: $it;" }

    fun flexDirection(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "flex-direction: $it;" }

    fun flex(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "flex: $it;" }

    fun flexGrow(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "flex-grow: $it;" }

    fun flexShrink(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "flex-shrink: $it;" }

    fun flexBasis(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "flex-basis: $it;" }

    fun justifySelf(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "justify-self: $it;" }

    fun alignSelf(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "align-self: $it;" }

    fun order(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "order: $it;" }

}