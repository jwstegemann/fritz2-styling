package dev.fritz2.styling.params

import dev.fritz2.styling.Property

interface Space : StyleParams {
    fun margin(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "margin: $it;" }

    fun marginTop(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "margin-top: $it;" }

    fun marginRight(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "margin-right: $it;" }

    fun marginBottom(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "margin-bottom: $it;" }

    fun marginLeft(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "margin-left: $it;" }

    fun marginHorizontal(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "margin-left: $it; margin-right: $it;" }

    fun marginVertical(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "margin-top: $it; margin-bottom: $it;" }

    fun padding(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "padding: $it;" }

    fun paddingTop(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "padding-top: $it;" }

    fun paddingRight(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "padding-right: $it;" }

    fun paddingBottom(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "padding-bottom: $it;" }

    fun paddingLeft(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "padding-left: $it;" }

    fun paddingHorizontal(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "padding-left: $it; padding-right: $it;" }

    fun paddingVertical(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "padding-top: $it; padding-bottom: $it;" }


}
