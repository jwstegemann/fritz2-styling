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

typealias Property = String

interface Space : StyleParams {
    fun margin(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "margin: $it;" }
}

interface Color : StyleParams {
    fun bgColor(sm: Property? = null, md: Property? = null, lg: Property? = null, xl: Property? = null) =
        property(sm, md, lg, xl) { "background-color: $it;" }
}


class StyleParamsImpl(val theme: Theme) : Space, Color {
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

