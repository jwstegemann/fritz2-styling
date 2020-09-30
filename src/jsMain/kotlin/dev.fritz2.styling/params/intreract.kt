package dev.fritz2.styling.params

import dev.fritz2.styling.Property

typealias PointerEventsProperty = Property  // SelfAlignItemProperty = Property

object PointerEventsValues : PropertyValues {  // SelfAlignItemsValues : PropertyValues
    override val key = "pointer-events: "

    const val auto: Property = "auto"
    const val none: Property = "none"
    const val initial: Property = "initial"
    const val inherit: Property = "inherit"
    const val unset: Property = "unset"
}

interface Interact : StyleParams {
    fun pointerEvents(value: PointerEventsValues.() -> PointerEventsProperty) =
        property(PointerEventsValues.key, PointerEventsValues.value(), smProperties)
}

