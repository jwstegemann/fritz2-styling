package dev.fritz2.styling.params

import dev.fritz2.styling.Property
import dev.fritz2.styling.ZIndices
import dev.fritz2.styling.theme
import kotlinx.coroutines.ExperimentalCoroutinesApi

class TextAlign() {
/*    val top: Align
    val left: Align
    val bottom: Align
    val right: Align
    val center: Align
    val justify: Align
*/
}

@ExperimentalCoroutinesApi
interface Position : StyleParams {

    /*
     * z-index
     */
    fun zIndex(value: ZIndicesProperty) = property(ZIndices.key, theme().zIndices, value)

    fun zIndex(
        sm: ZIndicesProperty? = null,
        md: ZIndicesProperty? = null,
        lg: ZIndicesProperty? = null,
        xl: ZIndicesProperty? = null
    ) =
        property(ZIndices.key, theme().zIndices, sm, md, lg, xl)

    /*
    fun position(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "position: $it;" }

    fun zIndex(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "z-index: $it;" }
        */

    /*
    fun top(
        sm: Property? = null,
        md: Property? = null,
        lg: Property? = null,
        xl: Property? = null
    ) =
        property("top: ", Property, sm, md, lg, xl) { "top: $it;" }

    fun right(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "right: $it;" }

    fun bottom(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "bottom: $it;" }

    fun left(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "left: $it;" }

     */

}