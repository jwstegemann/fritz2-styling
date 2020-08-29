package dev.fritz2.styling.params

import dev.fritz2.styling.StyleClass
import dev.fritz2.styling.Theme
import dev.fritz2.styling.style

interface StyleParams {
    val addSm: (String) -> StringBuilder
    val addMd: (String) -> StringBuilder
    val addLg: (String) -> StringBuilder
    val addXl: (String) -> StringBuilder
}

inline fun <T> StyleParams.property(
    sm: T? = null,
    md: T? = null,
    lg: T? = null,
    xl: T? = null,
    crossinline entry: (T) -> String
) {
    if (sm != null) addSm(entry(sm))
    if (md != null) addMd(entry(md))
    if (lg != null) addLg(entry(lg))
    if (xl != null) addXl(entry(xl))
}


class StyleParamsImpl(val theme: Theme) : Background, Border, Color, Flexbox, GridLayout, Layout, Position, Shadow,
    Space, Typo {
    private val sm = StringBuilder()
    private val md = StringBuilder()
    private val lg = StringBuilder()
    private val xl = StringBuilder()

    override val addSm: (String) -> StringBuilder = sm::append
    override val addMd: (String) -> StringBuilder = md::append
    override val addLg: (String) -> StringBuilder = lg::append
    override val addXl: (String) -> StringBuilder = xl::append

    fun toCss(): String {
        if (md.isNotEmpty()) sm.append(theme.mediaQueryMd, "{", md, "}")
        if (lg.isNotEmpty()) sm.append(theme.mediaQueryLg, "{", lg, "}")
        if (xl.isNotEmpty()) sm.append(theme.mediaQueryXl, "{", xl, "}")

        return sm.toString()
    }
}

typealias Style<T> = T.() -> Unit


interface BasicStyleParams : Space, Color, Border, Typo, Background, Position, Shadow, Layout

interface FlexStyleParams : BasicStyleParams, Flexbox

interface GridStyleParams : BasicStyleParams, GridLayout


inline fun <T : StyleParams> Theme.use(styling: Style<T>, prefix: String = "s"): StyleClass {
    val base = StyleParamsImpl(this)
    (base.unsafeCast<T>()).styling()
    return base.toCss().let {
        if (it.isNotEmpty()) style(it, prefix)
        else it
    }
}

/*
inline fun <reified T : StyleParams, reified A : StyleParams> use(a: Style<T>, b: Style<T>, prefix: String = "s-"): StyleClass {
    val x = BaseStyleParams()
    (x as T).a()
    (x as T).b()
    return style(x.toCss(), prefix)
}

 */

