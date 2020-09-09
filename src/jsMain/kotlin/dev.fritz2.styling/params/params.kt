package dev.fritz2.styling.params

import dev.fritz2.styling.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

interface StyleParams {
    val smProperties: StringBuilder
    val mdProperties: StringBuilder
    val lgProperties: StringBuilder
    val xlProperties: StringBuilder
}

const val cssDelimiter = ";"

fun <T> StyleParams.property(
    key: String,
    sm: T,
    target: StringBuilder = smProperties
) {
    target.append(key, sm, cssDelimiter)
}

fun <T> StyleParams.property(
    key: String,
    sm: T? = null,
    md: T? = null,
    lg: T? = null,
    xl: T? = null
) {
    if (sm != null) property(key, sm, smProperties)
    if (md != null) property(key, sm, mdProperties)
    if (lg != null) property(key, sm, lgProperties)
    if (xl != null) property(key, sm, xlProperties)
}

/*
 * scaled properties
 */
typealias ScaledValueProperty = ScaledValue<Property>.() -> Property
typealias WeightedValueProperty = WeightedValue<Property>.() -> Property

inline fun <T> StyleParams.property(key: String, base: T, sm: T.() -> Property, target: StringBuilder = smProperties) =
    property(key, base.sm())

fun <T> StyleParams.property(
    key: String,
    base: T,
    sm: (T.() -> Property)? = null,
    md: (T.() -> Property)? = null,
    lg: (T.() -> Property)? = null,
    xl: (T.() -> Property)? = null
) =
    property(key,
        sm?.let { it(base) },
        md?.let { it(base) },
        lg?.let { it(base) },
        xl?.let { it(base) }
    )

/*
 * enum based properties
 */
interface PropertyValues {
    val key: String
}

inline fun <T : PropertyValues> StyleParams.property(
    base: T, sm: T.() -> Property
) = property(base.key, sm(base))

fun <T : PropertyValues> StyleParams.property(
    base: T,
    sm: (T.() -> Property)? = null,
    md: (T.() -> Property)? = null,
    lg: (T.() -> Property)? = null,
    xl: (T.() -> Property)? = null
) =
    property(
        base.key,
        sm?.let { sm(base) },
        md?.let { md(base) },
        lg?.let { lg(base) },
        xl?.let { xl(base) },
    )


class StyleParamsImpl<X : Theme>(private val theme: X) : Background, Border, Color, Flexbox, GridLayout, Layout,
    Position,
    Shadow,
    Space, Typo {
    override val smProperties = StringBuilder()
    override val mdProperties = StringBuilder()
    override val lgProperties = StringBuilder()
    override val xlProperties = StringBuilder()

    fun toCss(): String {
        if (mdProperties.isNotEmpty()) smProperties.append(theme.mediaQueryMd, "{", mdProperties, "}")
        if (lgProperties.isNotEmpty()) smProperties.append(theme.mediaQueryLg, "{", lgProperties, "}")
        if (xlProperties.isNotEmpty()) smProperties.append(theme.mediaQueryXl, "{", xlProperties, "}")

        return smProperties.toString()
        /*.also {
        println("******")
        println(it)
        println("*******")
    }*/
    }
}

typealias Style<T> = T.() -> Unit

interface BasicStyleParams : Space, Color, Border, Typo, Background, Position, Shadow, Layout

interface FlexStyleParams : BasicStyleParams, Flexbox

interface GridStyleParams : BasicStyleParams, GridLayout

@ExperimentalCoroutinesApi
inline fun <T : StyleParams> use(styling: Style<T>, prefix: String = "s"): StyleClass =
    StyleParamsImpl(theme()).let { base ->
        (base.unsafeCast<T>()).styling()
        base.toCss().let {
            if (it.isNotEmpty()) style(it, prefix)
            else it
        }
    }

@ExperimentalCoroutinesApi
inline fun <T : StyleParams, U : StyleParams> use(
    styling: Style<T>,
    moreStyling: Style<U>,
    prefix: String = "s"
): StyleClass =
    StyleParamsImpl(theme()).let { base ->
        (base.unsafeCast<T>()).styling()
        (base.unsafeCast<U>()).moreStyling()
        base.toCss().let {
            if (it.isNotEmpty()) style(it, prefix)
            else it
        }
    }


@ExperimentalCoroutinesApi
inline fun <T : StyleParams, U : StyleParams, V : StyleParams> use(
    styling: Style<T>,
    moreStyling: Style<U>,
    evenMoreStyling: Style<V>,
    prefix: String = "s"
): StyleClass =
    StyleParamsImpl(theme()).let { base ->
        (base.unsafeCast<T>()).styling()
        (base.unsafeCast<U>()).moreStyling()
        (base.unsafeCast<V>()).evenMoreStyling()
        base.toCss().let {
            if (it.isNotEmpty()) style(it, prefix)
            else it
        }
    }
