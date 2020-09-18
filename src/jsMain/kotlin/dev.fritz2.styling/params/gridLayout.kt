package dev.fritz2.styling.params

import dev.fritz2.styling.Property
import dev.fritz2.styling.theme
import kotlinx.coroutines.ExperimentalCoroutinesApi

object AutoFlowStyles : PropertyValues {
    override val key = "grid-auto-flow: "

    const val column: Property = "column"
    const val row: Property = "row"
    const val dense: Property = "dense"
    const val columnDense: Property = "column dense"
    const val rowDense: Property = "row dense"
    const val inherit: Property = "inherit"
    const val initial: Property = "initial"
    const val unset: Property = "unset"
}

@ExperimentalCoroutinesApi
class GridTemplateContext(
    styleParams: StyleParams,
    private val target: StringBuilder
) : StyleParams by styleParams {

    val autoFit: Property = "auto-fit"
    val autoFill: Property = "auto-fill"
    val minContent: Property = "min-content"
    val maxContent: Property = "max-content"
    val auto: Property = "auto"

    fun repeat(count: Int, value: GridTemplateContext.() -> Property) =
        "repeat($count, ${value()})"

    fun repeat(count: Property, value: GridTemplateContext.() -> Property) =
        "repeat($count, ${value()})"

    fun minmax(min: Property, max: Property) = "minmax($min, $max)"
}

@ExperimentalCoroutinesApi
class GridAreaContext(styleParams: StyleParams, val target: StringBuilder) : StyleParams by styleParams {
    fun row(vararg values: Property) = target.append("\"${values.toList().joinToString(" ")}\"\n")
}

typealias AreaName = String

val AreaName.start: String
    get() = "$this-start"

val AreaName.end: String
    get() = "$this-end"

val AreaName.middle: String
    get() = "$this-middle"

internal const val gridTemplateColumnsKey = "grid-template-columns: "
internal const val gridTemplateRowsKey = "grid-template-rows: "
internal const val gridTemplateAutoRowsKey = "grid-auto-rows: "
internal const val gridTemplateAutoColumnsKey = "grid-auto-columns: "
internal const val gridTemplateAreasKey = "grid-template-areas: "
internal const val rowGapKey = "row-gap: "
internal const val columnGapKey = "column-gap: "

@ExperimentalCoroutinesApi
interface GridLayout : StyleParams, Alignment {

    /*
     * columns
     */
    fun columns(value: GridTemplateContext.() -> Property) =
        property(gridTemplateColumnsKey, GridTemplateContext(this, smProperties).value(), smProperties)

    fun columns(
        sm: (GridTemplateContext.() -> Property)? = null,
        md: (GridTemplateContext.() -> Property)? = null,
        lg: (GridTemplateContext.() -> Property)? = null,
        xl: (GridTemplateContext.() -> Property)? = null
    ) {
        if (sm != null) property(gridTemplateColumnsKey, GridTemplateContext(this, smProperties).sm(), smProperties)
        if (md != null) property(gridTemplateColumnsKey, GridTemplateContext(this, mdProperties).md(), mdProperties)
        if (lg != null) property(gridTemplateColumnsKey, GridTemplateContext(this, lgProperties).lg(), lgProperties)
        if (xl != null) property(gridTemplateColumnsKey, GridTemplateContext(this, xlProperties).xl(), xlProperties)
    }

    /*
     * rows
    */
    fun rows(value: GridTemplateContext.() -> Property) =
        property(gridTemplateRowsKey, GridTemplateContext(this, smProperties).value(), smProperties)

    fun rows(
        sm: (GridTemplateContext.() -> Property)? = null,
        md: (GridTemplateContext.() -> Property)? = null,
        lg: (GridTemplateContext.() -> Property)? = null,
        xl: (GridTemplateContext.() -> Property)? = null
    ) {
        if (sm != null) property(gridTemplateRowsKey, GridTemplateContext(this, smProperties).sm(), smProperties)
        if (md != null) property(gridTemplateRowsKey, GridTemplateContext(this, mdProperties).md(), mdProperties)
        if (lg != null) property(gridTemplateRowsKey, GridTemplateContext(this, lgProperties).lg(), lgProperties)
        if (xl != null) property(gridTemplateRowsKey, GridTemplateContext(this, xlProperties).xl(), xlProperties)
    }

    /*
     * autoRows
    */
    fun autoRows(value: GridTemplateContext.() -> Property) =
        property(gridTemplateAutoRowsKey, GridTemplateContext(this, smProperties).value(), smProperties)

    fun autoRows(
        sm: (GridTemplateContext.() -> Property)? = null,
        md: (GridTemplateContext.() -> Property)? = null,
        lg: (GridTemplateContext.() -> Property)? = null,
        xl: (GridTemplateContext.() -> Property)? = null
    ) {
        if (sm != null) property(gridTemplateAutoRowsKey, GridTemplateContext(this, smProperties).sm(), smProperties)
        if (md != null) property(gridTemplateAutoRowsKey, GridTemplateContext(this, mdProperties).md(), mdProperties)
        if (lg != null) property(gridTemplateAutoRowsKey, GridTemplateContext(this, lgProperties).lg(), lgProperties)
        if (xl != null) property(gridTemplateAutoRowsKey, GridTemplateContext(this, xlProperties).xl(), xlProperties)
    }

    /*
     * autoColumns
    */
    fun autoColumns(value: GridTemplateContext.() -> Property) =
        property(gridTemplateAutoColumnsKey, GridTemplateContext(this, smProperties).value(), smProperties)

    fun autoColumns(
        sm: (GridTemplateContext.() -> Property)? = null,
        md: (GridTemplateContext.() -> Property)? = null,
        lg: (GridTemplateContext.() -> Property)? = null,
        xl: (GridTemplateContext.() -> Property)? = null
    ) {
        if (sm != null) property(gridTemplateAutoColumnsKey, GridTemplateContext(this, smProperties).sm(), smProperties)
        if (md != null) property(gridTemplateAutoColumnsKey, GridTemplateContext(this, mdProperties).md(), mdProperties)
        if (lg != null) property(gridTemplateAutoColumnsKey, GridTemplateContext(this, lgProperties).lg(), lgProperties)
        if (xl != null) property(gridTemplateAutoColumnsKey, GridTemplateContext(this, xlProperties).xl(), xlProperties)
    }

    /*
     * autoFlow
    */
    fun autoFlow(value: AutoFlowStyles.() -> Property) =
        property(AutoFlowStyles.key, AutoFlowStyles.value(), smProperties)

    fun autoFlow(
        sm: (AutoFlowStyles.() -> Property)? = null,
        md: (AutoFlowStyles.() -> Property)? = null,
        lg: (AutoFlowStyles.() -> Property)? = null,
        xl: (AutoFlowStyles.() -> Property)? = null
    ) {
        if (sm != null) property(AutoFlowStyles.key, AutoFlowStyles.sm(), smProperties)
        if (md != null) property(AutoFlowStyles.key, AutoFlowStyles.md(), mdProperties)
        if (lg != null) property(AutoFlowStyles.key, AutoFlowStyles.lg(), lgProperties)
        if (xl != null) property(AutoFlowStyles.key, AutoFlowStyles.xl(), xlProperties)
    }

    /*
     * areas
     */
    fun areas(value: GridAreaContext.() -> Unit) {
        val target = StringBuilder()
        GridAreaContext(this, target).value()
        property(gridTemplateAreasKey, "\n$target", smProperties)
    }

    fun areas(
        sm: (GridAreaContext.() -> Unit)? = null,
        md: (GridAreaContext.() -> Unit)? = null,
        lg: (GridAreaContext.() -> Unit)? = null,
        xl: (GridAreaContext.() -> Unit)? = null
    ) {
        if (sm != null) {
            val target = StringBuilder()
            GridAreaContext(this, target).sm()
            property(gridTemplateAreasKey, "\n$target", smProperties)
        }
        if (md != null) {
            val target = StringBuilder()
            GridAreaContext(this, target).md()
            property(gridTemplateAreasKey, "\n$target", mdProperties)
        }
        if (lg != null) {
            val target = StringBuilder()
            GridAreaContext(this, target).lg()
            property(gridTemplateAreasKey, "\n$target", lgProperties)
        }
        if (xl != null) {
            val target = StringBuilder()
            GridAreaContext(this, target).xl()
            property(gridTemplateAreasKey, "\n$target", xlProperties)
        }
    }

    /*
     * column-gap
     */
    fun columnGap(value: ScaledValueProperty) = property(columnGapKey, theme().gridGap, value)

    fun columnGap(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) =
        property(columnGapKey, theme().gridGap, sm, md, lg, xl)

    /*
     * row-gap
     */
    fun rowGap(value: ScaledValueProperty) = property(rowGapKey, theme().gridGap, value)

    fun rowGap(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) =
        property(rowGapKey, theme().gridGap, sm, md, lg, xl)

    /*
     * gap
     */
    fun gap(value: ScaledValueProperty) {
        columnGap(value)
        rowGap(value)
    }

    fun gap(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) {
        columnGap(sm, md, lg, xl)
        rowGap(sm, md, lg, xl)
    }

    // TODO: Find better scope; could be also used outside of grid,
    //  like ``width``, ``height`` and similar!
    fun fitContent(value: Property) = "fit-content($value)"

}

