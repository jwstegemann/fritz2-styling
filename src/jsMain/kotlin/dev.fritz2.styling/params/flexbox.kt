package dev.fritz2.styling.params

import dev.fritz2.styling.Property
import kotlinx.coroutines.ExperimentalCoroutinesApi

typealias DirectionProperty = Property

object DirectionValues : PropertyValues {
    override val key = "flex-direction: "

    const val row: DirectionProperty = "row"
    const val column: DirectionProperty = "column"
    const val rowReverse: DirectionProperty = "row-reverse"
    const val columnReverse: DirectionProperty = "column-reverse"
}

typealias WrapProperty = Property

object WrapValues : PropertyValues {
    override val key = "flex-wrap: "

    const val wrap: WrapProperty = "wrap"
    const val nowrap: WrapProperty = "nowrap"
    const val wrapReverse: WrapProperty = "wrap-reverse"
}

@ExperimentalCoroutinesApi
interface Flexbox : StyleParams, Alignment {

    fun direction(value: DirectionValues.() -> DirectionProperty) =
        property(DirectionValues.key, DirectionValues.value(), smProperties)

    fun direction(
        sm: (DirectionValues.() -> DirectionProperty)? = null,
        md: (DirectionValues.() -> DirectionProperty)? = null,
        lg: (DirectionValues.() -> DirectionProperty)? = null,
        xl: (DirectionValues.() -> DirectionProperty)? = null
    ) {
        if (sm != null) property(DirectionValues.key, DirectionValues.sm(), smProperties)
        if (md != null) property(DirectionValues.key, DirectionValues.md(), mdProperties)
        if (lg != null) property(DirectionValues.key, DirectionValues.lg(), lgProperties)
        if (xl != null) property(DirectionValues.key, DirectionValues.xl(), xlProperties)
    }

    fun wrap(value: WrapValues.() -> WrapProperty) =
        property(WrapValues.key, WrapValues.value(), smProperties)

    fun wrap(
        sm: (WrapValues.() -> WrapProperty)? = null,
        md: (WrapValues.() -> WrapProperty)? = null,
        lg: (WrapValues.() -> WrapProperty)? = null,
        xl: (WrapValues.() -> WrapProperty)? = null
    ) {
        if (sm != null) property(WrapValues.key, WrapValues.sm(), smProperties)
        if (md != null) property(WrapValues.key, WrapValues.md(), mdProperties)
        if (lg != null) property(WrapValues.key, WrapValues.lg(), lgProperties)
        if (xl != null) property(WrapValues.key, WrapValues.xl(), xlProperties)
    }

}