package dev.fritz2.styling.params

import dev.fritz2.styling.Property

object BackgroundRepeats : PropertyValues {
    override val key = "background-repeat: "

    const val repeat: Property = "repeat"
    const val repeatX: Property = "repeat-x"
    const val repeatY: Property = "repeat-y"
    const val noRepeat: Property = "no-repeat"
    const val space: Property = "space"
    const val round: Property = "round"
    const val initial: Property = "initial"
    const val inherit: Property = "inherit"
}

object BackgroundPositions : PropertyValues {
    override val key = "background-position: "

}

interface Background : StyleParams {
    /*
        fun background(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
            property(sm, md, lg, xl) { "background: $it;" }

        fun backgroundImage(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
            property(sm, md, lg, xl) { "background-image: $it;" }

        fun backgroundSize(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
            property(sm, md, lg, xl) { "background-size: $it;" }

        fun backgroundPosition(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
            property(sm, md, lg, xl) { "background-position: ${it};" }

    */
    /*
    * backgroundRepeat
    */
    fun backgroundRepeat(value: BackgroundRepeats.() -> Property) = property(BackgroundRepeats, value)

    fun backgroundRepeat(
        sm: (BackgroundRepeats.() -> Property)? = null,
        md: (BackgroundRepeats.() -> Property)? = null,
        lg: (BackgroundRepeats.() -> Property)? = null,
        xl: (BackgroundRepeats.() -> Property)? = null,
    ) =
        property(BackgroundRepeats, sm, md, lg, xl)
}
