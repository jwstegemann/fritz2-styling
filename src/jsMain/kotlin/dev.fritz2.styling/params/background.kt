package dev.fritz2.styling.params

import dev.fritz2.styling.Property

interface Background : StyleParams {
    fun background(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "background: $it;" }

    fun backgroundImage(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "background-image: $it;" }

    fun backgroundSize(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "background-size: $it;" }

    fun backgroundPosition(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "background-position: $it;" }

    fun backgroundRepeat(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "background-repeat: $it;" }

}