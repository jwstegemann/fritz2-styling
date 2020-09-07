package dev.fritz2.styling.params

import dev.fritz2.styling.StyleClass
import dev.fritz2.styling.Theme
import dev.fritz2.styling.style
import dev.fritz2.styling.theme
import kotlinx.coroutines.ExperimentalCoroutinesApi

interface StyleParams {
    val smProperties: StringBuilder
    val mdProperties: StringBuilder
    val lgProperties: StringBuilder
    val xlProperties: StringBuilder
}

inline fun <T> StyleParams.property(
    sm: T? = null,
    md: T? = null,
    lg: T? = null,
    xl: T? = null,
    crossinline entry: (T) -> String
) {
    if (sm != null) smProperties.append(entry(sm))
    if (md != null) mdProperties.append(entry(md))
    if (lg != null) lgProperties.append(entry(lg))
    if (xl != null) xlProperties.append(entry(xl))
}

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
