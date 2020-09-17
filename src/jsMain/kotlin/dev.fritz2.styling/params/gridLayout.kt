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
    alignment: Alignment,
    private val target: StringBuilder
) : StyleParams by styleParams, Alignment by alignment {
    fun columns(value: GridTemplateContext.() -> Property) = property("grid-template-columns: ", value(), target)
    fun rows(value: StyleParams.() -> Property) = property("grid-template-rows: ", value(), target)
    fun autoRows(value: () -> Property) = property("grid-auto-rows: ", value(), target)
    fun autoColumns(value: Property) = property("grid-auto-columns: ", value, target)
    fun autoFlow(value: AutoFlowStyles.() -> Property) = property(AutoFlowStyles.key, AutoFlowStyles.value(), target)
    fun areas(vararg rows: Property) = property(
        "grid-template-areas: ",
        rows.toList().joinToString("\n") { "\"$it\"" }, target
    )

    fun columnGap(value: ScaledValueProperty) = property("column-gap: ", theme().gridGap, value)
    fun rowGap(value: ScaledValueProperty) = property("row-gap: ", theme().gridGap, value)
    fun gap(value: ScaledValueProperty) {
        columnGap(value)
        rowGap(value)
    }

    fun repeat(count: Int, value: GridTemplateContext.() -> Property) = "repeat($count, ${value()})"
    fun repeat(count: Property, value: GridTemplateContext.() -> Property) = "repeat($count, ${value()})"

    val autoFit: Property = "auto-fit"
    val autoFill: Property = "auto-fill"
    fun fitContent(value: Property) = "fir-content($value)"

    val minContent: Property = "min-content"
    val maxContent: Property = "max-content"
    val auto: Property = "auto"
    fun minmax(min: Property, max: Property) = "minmax($min, $max)"
}

typealias AreaName = String

val AreaName.start: String
    get() = "$this-start"

val AreaName.end: String
    get() = "$this-end"

@ExperimentalCoroutinesApi
interface GridLayout : StyleParams {

    fun template(
        sm: (GridTemplateContext.() -> Unit)? = null,
        md: (GridTemplateContext.() -> Unit)? = null,
        lg: (GridTemplateContext.() -> Unit)? = null,
        xl: (GridTemplateContext.() -> Unit)? = null
    ) {
        if (sm != null) GridTemplateContext(this, AlignmentImpl(this, smProperties), smProperties).sm()
        if (md != null) GridTemplateContext(this, AlignmentImpl(this, mdProperties), mdProperties).md()
        if (lg != null) GridTemplateContext(this, AlignmentImpl(this, lgProperties), lgProperties).lg()
        if (xl != null) GridTemplateContext(this, AlignmentImpl(this, xlProperties), xlProperties).xl()
    }

}
