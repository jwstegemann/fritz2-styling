package dev.fritz2.styling.params

import dev.fritz2.styling.Property

interface GridLayout : StyleParams {
    fun gridGap(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "grid-gap: $it;" }

    fun gridRowGap(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "grid-row-gap: $it;" }

    fun gridColumnGap(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "grid-column-gap: $it;" }

    fun gridColumn(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "grid-column: $it;" }

    fun gridRow(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "grid-row: $it;" }

    fun gridArea(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "grid-area: $it;" }

    fun gridAutoFlow(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "grid-auto-flow: $it;" }

    fun gridAutoRows(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "grid-auto-rows: $it;" }

    fun gridAutoColumns(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "grid-auto-columns: $it;" }

    fun gridTemplateRows(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "grid-template-rows: $it;" }

    fun gridTemplateColumns(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "grid-template-columns: $it;" }

    fun gridTemplateAreas(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "grid-template-areas: $it;" }

}