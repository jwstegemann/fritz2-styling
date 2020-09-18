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
interface Flexbox : StyleParams, Alignment {

    fun direction(value: DirectionValues.() -> Property) =
        property(DirectionValues.key, DirectionValues.value(), smProperties)

    fun direction(
        sm: (DirectionValues.() -> Property)? = null,
        md: (DirectionValues.() -> Property)? = null,
        lg: (DirectionValues.() -> Property)? = null,
        xl: (DirectionValues.() -> Property)? = null
    ) {
        if (sm != null) property(DirectionValues.key, DirectionValues.sm(), smProperties)
        if (md != null) property(DirectionValues.key, DirectionValues.md(), mdProperties)
        if (lg != null) property(DirectionValues.key, DirectionValues.lg(), lgProperties)
        if (xl != null) property(DirectionValues.key, DirectionValues.xl(), xlProperties)
    }

    fun wrap(value: WrapValues.() -> Property) =
        property(WrapValues.key, WrapValues.value(), smProperties)

    fun wrap(
        sm: (WrapValues.() -> Property)? = null,
        md: (WrapValues.() -> Property)? = null,
        lg: (WrapValues.() -> Property)? = null,
        xl: (WrapValues.() -> Property)? = null
    ) {
        if (sm != null) property(WrapValues.key, WrapValues.sm(), smProperties)
        if (md != null) property(WrapValues.key, WrapValues.md(), mdProperties)
        if (lg != null) property(WrapValues.key, WrapValues.lg(), lgProperties)
        if (xl != null) property(WrapValues.key, WrapValues.xl(), xlProperties)
    }

}