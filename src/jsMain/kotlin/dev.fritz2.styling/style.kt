package dev.fritz2.styling

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.w3c.dom.HTMLStyleElement
import org.w3c.dom.css.CSSStyleSheet
import stylis.compile
import stylis.middleware
import stylis.serialize
import stylis.stringify
import kotlin.browser.document

internal object Styling {
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
    ".$name { $css }".let {
        console.log("***")
        console.log("    $it")
        serialize(compile(it), Styling.middleware)
        console.log("###")
    }
    return name
}

fun style(css: String, prefix: String = "s"): StyleClass {
    val hash = hash.v3(css)
    return "$prefix-${generateAlphabeticName(hash)}".also {
        if (!Styling.rules.contains(hash)) staticStyle(it, css)
        Styling.rules.add(hash)
    }
}

fun <T : Theme> T.style(
    sm: T.() -> String = { "" },
    md: T.() -> String = { "" },
    lg: T.() -> String = { "" },
    xl: T.() -> String = { "" },
    prefix: String = "s"
): StyleClass {
    val combinedCss = StringBuilder(this.sm())
    this.md().also { if (it.isNotEmpty()) combinedCss.append(this.mediaQueryMd, "{", it, "}") }
    this.lg().also { if (it.isNotEmpty()) combinedCss.append(this.mediaQueryLg, "{", it, "}") }
    this.xl().also { if (it.isNotEmpty()) combinedCss.append(this.mediaQueryXl, "{", it, "}") }

    return combinedCss.toString().let {
        if (it.isNotEmpty()) style(it, prefix)
        else it
    }
}

inline fun <T> StyleClass.whenever(upstream: Flow<T>, crossinline mapper: suspend (T) -> Boolean): Flow<String> =
    upstream.map { value ->
        if (mapper(value)) this else ""
    }


