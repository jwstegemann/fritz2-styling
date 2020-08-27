import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.w3c.dom.HTMLStyleElement
import org.w3c.dom.css.CSSStyleSheet
import stylis.compile
import stylis.middleware
import stylis.serialize
import stylis.stringify
import kotlin.browser.document

object Styling {
    private var counter = 0

    val rules = mutableSetOf<Int>()

    private val sheet by lazy {
        val style = document.createElement("style") as HTMLStyleElement
        // WebKit hack
        style.appendChild(document.createTextNode(""))
        document.head!!.appendChild(style)
        style.sheet!! as CSSStyleSheet
    }

    private val addRuleMiddleware: (dynamic) -> dynamic = { value ->
        if (value.root == null)
            with(value["return"]) {
                if (this != null) sheet.insertRule(this as String, counter++)
            }
        undefined
    }

    val middleware = middleware(arrayOf(::stringify, addRuleMiddleware))
}

typealias StyleClass = String

fun staticStyle(name: String, css: String): StyleClass {
    serialize(compile(".$name { $css }"), Styling.middleware)
    return name
}

fun style(css: String, prefix: String = "css-"): StyleClass {
    val hash = hash.v3(css)
    return "$prefix${generateAlphabeticName(hash)}".also {
        if (Styling.rules.contains(hash)) staticStyle(it, css)
    }
}

fun style(
    sm: String? = null,
    md: String? = null,
    lg: String? = null,
    xl: String? = null,
    prefix: String = "css-"
): StyleClass {
    val combinedCss = StringBuilder()
    if (sm != null) combinedCss.append(sm)
    if (md != null) combinedCss.append(" @media screen and (min-width: 30em) { $md }")
    if (lg != null) combinedCss.append(" @media screen and (min-width: 48em) { $lg }")
    if (xl != null) combinedCss.append(" @media screen and (min-width: 64em) { $xl }")

    return style(combinedCss.toString(), prefix)
}

inline fun <T> StyleClass.whenever(upstream: Flow<T>, crossinline mapper: suspend (T) -> Boolean): Flow<String> =
    upstream.map { value ->
        if (mapper(value)) this else ""
    }


