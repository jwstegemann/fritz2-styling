import dev.fritz2.dom.html.A
import dev.fritz2.dom.html.HtmlElements
import dev.fritz2.dom.html.render
import dev.fritz2.dom.mount
import org.w3c.dom.HTMLStyleElement
import org.w3c.dom.css.CSSStyleSheet
import org.w3c.dom.css.StyleSheet
import org.w3c.dom.css.get
import stylis.compile
import stylis.serialize
import stylis.stringify
import kotlin.browser.document

/*
inline fun <T> styled(crossinline factory: (String?, String?, T.() -> Unit) -> T): (String?, String?, T.() -> Unit) -> T = { baseClass, id, content ->
    console.log("bin da")
    factory(baseClass, id, content)
}


operator fun <A, B, R, FUN : (A, B) -> R> FUN.invoke(partial: A) = { other: B -> invoke(partial, other) }
operator fun <A, B, C, R, FUN : (A, B, C) -> R> FUN.invoke(partial: A) = { b: B, c: C -> invoke(partial, b, c) }
operator fun <A, B, C, D, R, FUN : (A, B, C, D) -> R> FUN.invoke(partial: A) = { b: B, c: C, d: D -> invoke(partial, b, c, d) }
operator fun <A, B, C, D, E, R, FUN : (A, B, C, D, E) -> R> FUN.invoke(partial: A) = { b: B, c: C, d: D, e: E -> invoke(partial, b, c, d, e) }
operator fun <A, B, C, D, E, F, R, FUN : (A, B, C, D, E, F) -> R> FUN.invoke(partial: A) = { b: B, c: C, d: D, e: E, f: F -> invoke(partial, b, c, d, e, f) }


val add = { a: Int, b: Int, c: Int, d: Int, e: Int -> a + b + c + d + e}
*/

typealias Property = Pair<String, String>
typealias Rule = List<Property>

object Styling {
    val sheet  by lazy {
        val style = document.createElement("style") as HTMLStyleElement;
        // WebKit hack
        style.appendChild(document.createTextNode(""));
        document.head!!.appendChild(style)
        style.sheet!! as CSSStyleSheet
    }

    fun addRule(className: String, rule: Rule) {
        val properties = rule.joinToString(separator = ";") { "${it.first}: ${it.second}" }
        sheet.insertRule(".$className { $properties }",0)
    }

    fun addRule(className: String, rule: String) {
        sheet.insertRule(".$className { $rule }",0)
    }
}





fun HtmlElements.button(): A {
    return a {}
}

//import {compile, serialize, stringify} from 'stylis'

fun main() {

    val x = serialize(compile( // language=CSS
        """
        .test {
            border: 2px solid red;
            background-color: yellow;
            color: red;
        }
        
    """.trimIndent()), ::stringify)

    println("result: ${x}")


    //Styling.addRule("myTest", x)

    render {
        p("myTest") {
            text("Hello World!")
        }
    }.mount("target")
}