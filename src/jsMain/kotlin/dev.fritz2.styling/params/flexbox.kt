package dev.fritz2.styling.params

import dev.fritz2.styling.Property
import kotlinx.coroutines.ExperimentalCoroutinesApi

object DirectionValues : PropertyValues {
    override val key = "flex-direction: "

    const val row: Property = "row"
    const val column: Property = "column"
    const val rowReverse: Property = "row-reverse"
    const val columnReverse: Property = "column-reverse"
}

object WrapValues : PropertyValues {
    override val key = "flex-wrap: "

    const val wrap: Property = "wrap"
    const val nowrap: Property = "nowrap"
    const val wrapReverse: Property = "wrap-reverse"
}

@ExperimentalCoroutinesApi
class FlexContainerContext(
    styleParams: StyleParams,
    alignment: Alignment,
    private val target: StringBuilder
) : StyleParams by styleParams, Alignment by alignment {
    fun direction(value: DirectionValues.() -> Property): Unit =
        property(DirectionValues.key, DirectionValues.value(), target)

    fun wrap(value: WrapValues.() -> Property): Unit = property(WrapValues.key, WrapValues.value(), target)
}

@ExperimentalCoroutinesApi
interface Flexbox : StyleParams {

    fun flexContainer(
        sm: (FlexContainerContext.() -> Unit)? = null,
        md: (FlexContainerContext.() -> Unit)? = null,
        lg: (FlexContainerContext.() -> Unit)? = null,
        xl: (FlexContainerContext.() -> Unit)? = null
    ) {
        if (sm != null) FlexContainerContext(this, AlignmentImpl(this, smProperties), smProperties).sm()
        if (md != null) FlexContainerContext(this, AlignmentImpl(this, mdProperties), mdProperties).md()
        if (lg != null) FlexContainerContext(this, AlignmentImpl(this, lgProperties), lgProperties).lg()
        if (xl != null) FlexContainerContext(this, AlignmentImpl(this, xlProperties), xlProperties).xl()
    }

}