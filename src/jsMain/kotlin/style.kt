import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.w3c.dom.HTMLStyleElement
import org.w3c.dom.css.CSSStyleSheet
import stylis.compile
import stylis.middleware
import stylis.serialize
import stylis.stringify
import kotlin.browser.document
import kotlin.math.absoluteValue

object Styling {
    private var counter = 0

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
                //FIXME: append!
                if (this != null) sheet.insertRule(this as String, counter++)
            }
        undefined
    }

    val middleware = middleware(arrayOf(::stringify, addRuleMiddleware))
}

typealias StyleClass = String

fun style(css: String, prefix: String = "css-"): StyleClass =
    "$prefix${generateAlphabeticName(css.hashCode().absoluteValue)}".also { className ->
        serialize(compile(" .$className { $css } "), Styling.middleware)
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

    return combinedCss.toString().let { css ->
        console.log("+++ css: $css")

        "$prefix${generateAlphabeticName(css.hashCode().absoluteValue)}".also { className ->
            serialize(compile(" .$className { $css } "), Styling.middleware)
        }
    }
}

inline fun <T> StyleClass.whenever(upstream: Flow<T>, crossinline mapper: suspend (T) -> Boolean): Flow<String> =
    upstream.map { value ->
        if (mapper(value)) this else ""
    }


