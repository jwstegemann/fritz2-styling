package dev.fritz2.styling.params

import dev.fritz2.styling.Property

interface Shadow : StyleParams {
    fun textShadow(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "text-shadow: $it;" }

    fun boxShadow(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "box-shadow: $it;" }
}