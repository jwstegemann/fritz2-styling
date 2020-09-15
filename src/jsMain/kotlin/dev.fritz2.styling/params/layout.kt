package dev.fritz2.styling.params

import dev.fritz2.styling.Property
import dev.fritz2.styling.theme
import kotlinx.coroutines.ExperimentalCoroutinesApi

internal const val widthKey = "width: "
internal const val heightKey = "height: "
internal const val minWidthKey = "min-width: "
internal const val maxWidthKey = "max-width: "
internal const val minHeightKey = "min-height: "
internal const val maxHeightKey = "max-height: "

object DisplayStyles : PropertyValues {
    override val key = "display: "

    const val none: Property = "none"
    const val inline: Property = "inline"
    const val block: Property = "block"
    const val contents: Property = "contents"
    const val listItem: Property = "list-item"
    const val inlineBlock: Property = "inline-block"
    const val inlineTable: Property = "inline-table"
    const val table: Property = "table"
    const val tableCell: Property = "table-cell"
    const val tableColumn: Property = "table-column"
    const val tableColumnGroup: Property = "table-column-group"
    const val tableFooterGroup: Property = "table-footer-group"
    const val tableHeaderGroup: Property = "table-header-group"
    const val tableRow: Property = "table-row"
    const val tableRowGroup: Property = "table-row-group"
    const val flex: Property = "flex"
    const val inlineFlex: Property = "inline-flex"
    const val grid: Property = "grid"
    const val inlineGrid: Property = "inline-grid"
    const val ruby: Property = "ruby"
    const val rubyBase: Property = "ruby-base"
    const val rubyText: Property = "ruby-text"
    const val rubyBaseContainer: Property = "ruby-base-container"
    const val rubyTextContainer: Property = "ruby-text-container"
    const val runIn: Property = "run-in"
    const val inherit: Property = "inherit"
    const val initial: Property = "initial"
    const val unset: Property = "unset"
}

open class OverflowBaseStyles(override val key: String) : PropertyValues {

    val visible: Property = "visible"
    val hidden: Property = "hidden"
    val scroll: Property = "scroll"
    val auto: Property = "auto"
    val inherit: Property = "inherit"
}

object OverflowStyles : OverflowBaseStyles("overflow: ")

object OverflowXStyles : OverflowBaseStyles("overflow-x: ") {
    const val clip: Property = "clip"
    const val initial: Property = "initial"
    const val unset: Property = "unset"
}

object OverflowYStyles : OverflowBaseStyles("overflow-y: ") {
    const val clip: Property = "clip"
    const val initial: Property = "initial"
    const val unset: Property = "unset"
}

object VerticalAlignStyles : PropertyValues {
    override val key = "vertical-align: "

    const val baseline: Property = "baseline"
    const val sub: Property = "sub"
    const val `super`: Property = "super"
    const val textTop: Property = "textTop"
    const val textBottom: Property = "textBottom"
    const val middle: Property = "middle"
    const val top: Property = "top"
    const val bottom: Property = "bottom"
    const val inherit: Property = "inherit"
    const val initial: Property = "initial"
    const val unset: Property = "unset"
}

@ExperimentalCoroutinesApi
class GridContext(
    styleParams: StyleParams,
    private val target: StringBuilder
) : StyleParams by styleParams {
    fun area(value: () -> Property) = property("grid-area: ", value(), target)

    fun column(value: GridRowColumnContext.() -> Unit) {
        GridRowColumnContext("column", this, target).value()
    }

    fun row(value: GridRowColumnContext.() -> Unit) {
        GridRowColumnContext("row", this, target).value()
    }
}


@ExperimentalCoroutinesApi
class GridRowColumnContext(
    private val keyFragment: String,
    styleParams: StyleParams,
    private val target: StringBuilder
) : StyleParams by styleParams {
    fun start(value: () -> Property) = property("grid-$keyFragment-start: ", value(), target)
    fun end(value: () -> Property) = property("grid-$keyFragment-end: ", value(), target)

    fun span(value: Int) = "span $value"
    fun span(value: String) = "span $value"
}


@ExperimentalCoroutinesApi
interface Layout : StyleParams {

    /*
     * size: convenient combination of width and height
     */
    fun size(value: SizesProperty) {
        width(value)
        height(value)
    }

    fun size(
        sm: SizesProperty? = null,
        md: SizesProperty? = null,
        lg: SizesProperty? = null,
        xl: SizesProperty? = null
    ) {
        width(sm, md, lg, xl)
        height(sm, md, lg, xl)
    }

    /*
     * width
     */
    fun width(value: SizesProperty) = property(widthKey, theme().sizes, value)

    fun width(
        sm: SizesProperty? = null,
        md: SizesProperty? = null,
        lg: SizesProperty? = null,
        xl: SizesProperty? = null
    ) =
        property(widthKey, theme().sizes, sm, md, lg, xl)

    /*
     * height
     */
    fun height(value: SizesProperty) = property(heightKey, theme().sizes, value)

    fun height(
        sm: SizesProperty? = null,
        md: SizesProperty? = null,
        lg: SizesProperty? = null,
        xl: SizesProperty? = null
    ) =
        property(heightKey, theme().sizes, sm, md, lg, xl)

    /*
     * min-width
     */
    fun minWidth(value: SizesProperty) = property(minWidthKey, theme().sizes, value)

    fun minWidth(
        sm: SizesProperty? = null,
        md: SizesProperty? = null,
        lg: SizesProperty? = null,
        xl: SizesProperty? = null
    ) =
        property(minWidthKey, theme().sizes, sm, md, lg, xl)

    /*
     * max-width
     */
    fun maxWidth(value: SizesProperty) = property(maxWidthKey, theme().sizes, value)

    fun maxWidth(
        sm: SizesProperty? = null,
        md: SizesProperty? = null,
        lg: SizesProperty? = null,
        xl: SizesProperty? = null
    ) =
        property(maxWidthKey, theme().sizes, sm, md, lg, xl)

    /*
     * min-height
     */
    fun minHeight(value: SizesProperty) = property(minHeightKey, theme().sizes, value)

    fun minHeight(
        sm: SizesProperty? = null,
        md: SizesProperty? = null,
        lg: SizesProperty? = null,
        xl: SizesProperty? = null
    ) =
        property(minHeightKey, theme().sizes, sm, md, lg, xl)

    /*
     * max-height
     */
    fun maxHeight(value: SizesProperty) = property(maxHeightKey, theme().sizes, value)

    fun maxHeight(
        sm: SizesProperty? = null,
        md: SizesProperty? = null,
        lg: SizesProperty? = null,
        xl: SizesProperty? = null
    ) =
        property(maxHeightKey, theme().sizes, sm, md, lg, xl)

    /*
     * display
     */
    fun display(value: DisplayStyles.() -> Property) = property(DisplayStyles, value)

    fun display(
        sm: (DisplayStyles.() -> Property)? = null,
        md: (DisplayStyles.() -> Property)? = null,
        lg: (DisplayStyles.() -> Property)? = null,
        xl: (DisplayStyles.() -> Property)? = null
    ) =
        property(DisplayStyles, sm, md, lg, xl)

    /*
     * vertical-align
     */
    fun verticalAlign(value: VerticalAlignStyles.() -> Property) = property(VerticalAlignStyles, value)

    fun verticalAlign(
        sm: (VerticalAlignStyles.() -> Property)? = null,
        md: (VerticalAlignStyles.() -> Property)? = null,
        lg: (VerticalAlignStyles.() -> Property)? = null,
        xl: (VerticalAlignStyles.() -> Property)? = null
    ) =
        property(VerticalAlignStyles, sm, md, lg, xl)

    /*
     * overflow
     */
    fun overflow(value: OverflowStyles.() -> Property) = property(OverflowStyles, value)

    fun overflow(
        sm: (OverflowStyles.() -> Property)? = null,
        md: (OverflowStyles.() -> Property)? = null,
        lg: (OverflowStyles.() -> Property)? = null,
        xl: (OverflowStyles.() -> Property)? = null
    ) =
        property(OverflowStyles, sm, md, lg, xl)

    /*
     * overflow-x
     */
    fun overflowX(value: OverflowXStyles.() -> Property) = property(OverflowXStyles, value)

    fun overflowX(
        sm: (OverflowXStyles.() -> Property)? = null,
        md: (OverflowXStyles.() -> Property)? = null,
        lg: (OverflowXStyles.() -> Property)? = null,
        xl: (OverflowXStyles.() -> Property)? = null
    ) =
        property(OverflowXStyles, sm, md, lg, xl)

    /*
     * overflow-y
     */
    fun overflowY(value: OverflowYStyles.() -> Property) = property(OverflowYStyles, value)

    fun overflowY(
        sm: (OverflowYStyles.() -> Property)? = null,
        md: (OverflowYStyles.() -> Property)? = null,
        lg: (OverflowYStyles.() -> Property)? = null,
        xl: (OverflowYStyles.() -> Property)? = null
    ) =
        property(OverflowYStyles, sm, md, lg, xl)

    /*
     * grid-area, grid-column, grid-row
     */
    fun grid(
        sm: (GridContext.() -> Unit)? = null,
        md: (GridContext.() -> Unit)? = null,
        lg: (GridContext.() -> Unit)? = null,
        xl: (GridContext.() -> Unit)? = null
    ) {
        if (sm != null) GridContext(this, smProperties).sm()
        if (md != null) GridContext(this, mdProperties).md()
        if (lg != null) GridContext(this, lgProperties).lg()
        if (xl != null) GridContext(this, xlProperties).xl()
    }

}