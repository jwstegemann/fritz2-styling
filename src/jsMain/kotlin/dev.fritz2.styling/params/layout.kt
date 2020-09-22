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

typealias DisplayProperty = Property

object DisplayValues : PropertyValues {
    override val key = "display: "

    const val none: DisplayProperty = "none"
    const val inline: DisplayProperty = "inline"
    const val block: DisplayProperty = "block"
    const val contents: DisplayProperty = "contents"
    const val listItem: DisplayProperty = "list-item"
    const val inlineBlock: DisplayProperty = "inline-block"
    const val inlineTable: DisplayProperty = "inline-table"
    const val table: DisplayProperty = "table"
    const val tableCell: DisplayProperty = "table-cell"
    const val tableColumn: DisplayProperty = "table-column"
    const val tableColumnGroup: DisplayProperty = "table-column-group"
    const val tableFooterGroup: DisplayProperty = "table-footer-group"
    const val tableHeaderGroup: DisplayProperty = "table-header-group"
    const val tableRow: DisplayProperty = "table-row"
    const val tableRowGroup: DisplayProperty = "table-row-group"
    const val flex: DisplayProperty = "flex"
    const val inlineFlex: DisplayProperty = "inline-flex"
    const val grid: DisplayProperty = "grid"
    const val inlineGrid: DisplayProperty = "inline-grid"
    const val ruby: DisplayProperty = "ruby"
    const val rubyBase: DisplayProperty = "ruby-base"
    const val rubyText: DisplayProperty = "ruby-text"
    const val rubyBaseContainer: DisplayProperty = "ruby-base-container"
    const val rubyTextContainer: DisplayProperty = "ruby-text-container"
    const val runIn: DisplayProperty = "run-in"
    const val inherit: DisplayProperty = "inherit"
    const val initial: DisplayProperty = "initial"
    const val unset: DisplayProperty = "unset"
}

typealias OverflowProperty = Property

open class OverflowBaseValues(override val key: String) : PropertyValues {

    val visible: OverflowProperty = "visible"
    val hidden: OverflowProperty = "hidden"
    val scroll: OverflowProperty = "scroll"
    val auto: OverflowProperty = "auto"
    val inherit: OverflowProperty = "inherit"
}

object OverflowValues : OverflowBaseValues("overflow: ")

object OverflowXValues : OverflowBaseValues("overflow-x: ") {
    const val clip: OverflowProperty = "clip"
    const val initial: OverflowProperty = "initial"
    const val unset: OverflowProperty = "unset"
}

object OverflowYValues : OverflowBaseValues("overflow-y: ") {
    const val clip: OverflowProperty = "clip"
    const val initial: OverflowProperty = "initial"
    const val unset: OverflowProperty = "unset"
}

typealias VerticalAlignProperty = Property

object VerticalAlignValues : PropertyValues {
    override val key = "vertical-align: "

    const val baseline: VerticalAlignProperty = "baseline"
    const val sub: VerticalAlignProperty = "sub"
    const val `super`: VerticalAlignProperty = "super"
    const val textTop: VerticalAlignProperty = "textTop"
    const val textBottom: VerticalAlignProperty = "textBottom"
    const val middle: VerticalAlignProperty = "middle"
    const val top: VerticalAlignProperty = "top"
    const val bottom: VerticalAlignProperty = "bottom"
    const val inherit: VerticalAlignProperty = "inherit"
    const val initial: VerticalAlignProperty = "initial"
    const val unset: VerticalAlignProperty = "unset"
}

@ExperimentalCoroutinesApi
class GridContext(
    styleParams: StyleParams,
    selfAlignment: SelfAlignment,
    private val target: StringBuilder
) : StyleParams by styleParams, SelfAlignment by selfAlignment {
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

typealias FlexBasisProperty = Property

object FlexBasisValues : PropertyValues {
    override val key = "flex-basis: "

    const val auto: FlexBasisProperty = "auto"
    const val fill: FlexBasisProperty = "fill"
    const val maxContent: FlexBasisProperty = "max-content"
    const val minContent: FlexBasisProperty = "min-content"
    const val fitContent: FlexBasisProperty = "fit-content"
    const val content: FlexBasisProperty = "content"
    const val inherit: FlexBasisProperty = "inherit"
    const val initial: FlexBasisProperty = "initial"
    const val unset: FlexBasisProperty = "unset"
}

@ExperimentalCoroutinesApi
class FlexItemContext(
    styleParams: StyleParams,
    selfAlignment: SelfAlignment,
    private val target: StringBuilder
) : StyleParams by styleParams, SelfAlignment by selfAlignment {
    fun order(value: () -> Property) = property("order: ", value(), target)
    fun grow(value: () -> Property) = property("flex-grow: ", value(), target)
    fun shrink(value: () -> Property) = property("flex-shrink: ", value(), target)
    fun basis(value: FlexBasisValues.() -> FlexBasisProperty) =
            property(FlexBasisValues.key, FlexBasisValues.value(), target)
    fun basis(value: () -> Property) = property(FlexBasisValues.key, value(), target)
}

@ExperimentalCoroutinesApi
interface Layout : StyleParams {

    /*
     * This function passes raw CSS code into the underlying model without modification.
     * This could be useful for corner cases where our abstractions fail, new CSS features
     * we have not yet implemented or simply for toying around with CSS code from external sources.
     */
    fun css(value: Property) = smProperties.append(value)

    fun css(
        sm: Property? = null,
        md: Property? = null,
        lg: Property? = null,
        xl: Property? = null
    ) {
        if (sm != null) smProperties.append(sm)
        if (md != null) mdProperties.append(sm)
        if (lg != null) lgProperties.append(sm)
        if (xl != null) xlProperties.append(sm)
    }


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
    fun display(value: DisplayValues.() -> DisplayProperty) = property(DisplayValues, value)

    fun display(
            sm: (DisplayValues.() -> DisplayProperty)? = null,
            md: (DisplayValues.() -> DisplayProperty)? = null,
            lg: (DisplayValues.() -> DisplayProperty)? = null,
            xl: (DisplayValues.() -> DisplayProperty)? = null
    ) =
        property(DisplayValues, sm, md, lg, xl)

    /*
     * vertical-align
     */
    fun verticalAlign(value: VerticalAlignValues.() -> VerticalAlignProperty) =
            property(VerticalAlignValues, value)

    fun verticalAlign(
            sm: (VerticalAlignValues.() -> VerticalAlignProperty)? = null,
            md: (VerticalAlignValues.() -> VerticalAlignProperty)? = null,
            lg: (VerticalAlignValues.() -> VerticalAlignProperty)? = null,
            xl: (VerticalAlignValues.() -> VerticalAlignProperty)? = null
    ) =
        property(VerticalAlignValues, sm, md, lg, xl)

    /*
     * overflow
     */
    fun overflow(value: OverflowValues.() -> OverflowProperty) = property(OverflowValues, value)

    fun overflow(
            sm: (OverflowValues.() -> OverflowProperty)? = null,
            md: (OverflowValues.() -> OverflowProperty)? = null,
            lg: (OverflowValues.() -> OverflowProperty)? = null,
            xl: (OverflowValues.() -> OverflowProperty)? = null
    ) =
        property(OverflowValues, sm, md, lg, xl)

    /*
     * overflow-x
     */
    fun overflowX(value: OverflowXValues.() -> OverflowProperty) = property(OverflowXValues, value)

    fun overflowX(
            sm: (OverflowXValues.() -> OverflowProperty)? = null,
            md: (OverflowXValues.() -> OverflowProperty)? = null,
            lg: (OverflowXValues.() -> OverflowProperty)? = null,
            xl: (OverflowXValues.() -> OverflowProperty)? = null
    ) =
        property(OverflowXValues, sm, md, lg, xl)

    /*
     * overflow-y
     */
    fun overflowY(value: OverflowYValues.() -> OverflowProperty) = property(OverflowYValues, value)

    fun overflowY(
            sm: (OverflowYValues.() -> OverflowProperty)? = null,
            md: (OverflowYValues.() -> OverflowProperty)? = null,
            lg: (OverflowYValues.() -> OverflowProperty)? = null,
            xl: (OverflowYValues.() -> OverflowProperty)? = null
    ) =
        property(OverflowYValues, sm, md, lg, xl)

    /*
     * grid-area, grid-column, grid-row
     */
    fun grid(value: GridContext.() -> Unit) =
        GridContext(this, SelfAlignmentImpl(this, smProperties), smProperties).value()

    fun grid(
        sm: (GridContext.() -> Unit)? = null,
        md: (GridContext.() -> Unit)? = null,
        lg: (GridContext.() -> Unit)? = null,
        xl: (GridContext.() -> Unit)? = null
    ) {
        if (sm != null) GridContext(this, SelfAlignmentImpl(this, smProperties), smProperties).sm()
        if (md != null) GridContext(this, SelfAlignmentImpl(this, mdProperties), mdProperties).md()
        if (lg != null) GridContext(this, SelfAlignmentImpl(this, lgProperties), lgProperties).lg()
        if (xl != null) GridContext(this, SelfAlignmentImpl(this, xlProperties), xlProperties).xl()
    }

    /*
     * flex Item properties: order, basis, grow, shrink, align-self
     */
    fun flex(value: FlexItemContext.() -> Unit) =
        FlexItemContext(this, SelfAlignmentImpl(this, smProperties), smProperties).value()

    fun flex(
        sm: (FlexItemContext.() -> Unit)? = null,
        md: (FlexItemContext.() -> Unit)? = null,
        lg: (FlexItemContext.() -> Unit)? = null,
        xl: (FlexItemContext.() -> Unit)? = null
    ) {
        if (sm != null) FlexItemContext(this, SelfAlignmentImpl(this, smProperties), smProperties).sm()
        if (md != null) FlexItemContext(this, SelfAlignmentImpl(this, mdProperties), mdProperties).md()
        if (lg != null) FlexItemContext(this, SelfAlignmentImpl(this, lgProperties), lgProperties).lg()
        if (xl != null) FlexItemContext(this, SelfAlignmentImpl(this, xlProperties), xlProperties).xl()
    }
}