interface StyleParams {
    val addSm: (String) -> Boolean
    val addMd: (String) -> Boolean
    val addLg: (String) -> Boolean
    val addXl: (String) -> Boolean
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


interface Space : StyleParams {
    fun margin(sm: String? = null, md: String? = null, lg: String? = null, xl: String? = null) =
        property(sm, md, lg, xl) { "margin: $it" }
}

interface Color : StyleParams {
    fun bgColor(sm: String? = null, md: String? = null, lg: String? = null, xl: String? = null) =
        property(sm, md, lg, xl) { "background-color: $it" }
}


class BaseStyleParams : Space, Color {
    private val sm = mutableListOf<String>()
    private val md = mutableListOf<String>()
    private val lg = mutableListOf<String>()
    private val xl = mutableListOf<String>()

    override val addSm: (String) -> Boolean = sm::add
    override val addMd: (String) -> Boolean = md::add
    override val addLg: (String) -> Boolean = lg::add
    override val addXl: (String) -> Boolean = xl::add

    fun toCss(): String {
        val css = StringBuilder()
        css.append(sm.joinToString(";")).append("; ")

        //TODO: extract breakpoints to theme
        if (md.isNotEmpty()) css.append("@media screen and (min-width: 30em) { ${md.joinToString(";")}; } ")
        if (lg.isNotEmpty()) css.append("@media screen and (min-width: 42em) { ${lg.joinToString(";")}; } ")
        if (xl.isNotEmpty()) css.append("@media screen and (min-width: 68em) { ${xl.joinToString(";")}; } ")

        return css.toString()
    }
}


inline fun <T : StyleParams> use(styling: Style<T>, prefix: String = "css-"): StyleClass {
    val x = BaseStyleParams()
    (x.unsafeCast<T>()).styling()
    return style(x.toCss(), prefix)
}

/*
inline fun <reified T : StyleParams, reified A : StyleParams> use(a: Style<T>, b: Style<T>, prefix: String = "css-"): StyleClass {
    val x = BaseStyleParams()
    (x as T).a()
    (x as T).b()
    return style(x.toCss(), prefix)
}

 */

typealias Style<T> = T.() -> Unit
