package dev.fritz2.styling.params

import dev.fritz2.styling.Property

interface Color : StyleParams {
    fun color(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "color: $it;" }

    fun bgColor(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "background-color: $it;" }

    fun opacity(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "opacity: $it;" }
}