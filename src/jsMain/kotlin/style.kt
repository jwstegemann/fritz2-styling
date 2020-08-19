import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.w3c.dom.HTMLStyleElement
import org.w3c.dom.css.CSSStyleSheet
import stylis.compile
import stylis.serialize
import stylis.stringify
import kotlin.browser.document
import kotlin.math.absoluteValue
import kotlin.reflect.KClass

interface StyleState {
    val css: String
}

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
    val sheet by lazy {
        val style = document.createElement("style") as HTMLStyleElement;
        // WebKit hack
        style.appendChild(document.createTextNode(""));
        document.head!!.appendChild(style)
        style.sheet!! as CSSStyleSheet
    }

    fun addRule(className: String, rule: Rule) {
        val properties = rule.joinToString(separator = ";") { "${it.first}: ${it.second}" }
        sheet.insertRule(".$className { $properties }", 0)
    }

    fun addRule(rule: String) {
        sheet.insertRule(rule, 0)
    }
}

fun style(css: String, prefix: String = "") =
    "$prefix${generateAlphabeticName(css.hashCode().absoluteValue)}".also { className ->
        Styling.addRule(serialize(compile(" .$className { $css } "), ::stringify))
    }

inline fun <reified T> style(enum: KClass<T>): Unit where T : Enum<T>, T : StyleState {
    enumValues<T>().forEach {
        val className = "${enum.simpleName}-${it.name}"
        Styling.addRule(serialize(compile(" .$className { ${it.css} } "), ::stringify))
    }
}

inline fun <reified T> Flow<T>.asClass(): Flow<String> where T : Enum<T>, T : StyleState =
    this.map { "${T::class.simpleName}-${it.toString()}" }

inline fun <reified T, X> T.whenever(
    upstream: Flow<X>,
    crossinline mapper: suspend (X) -> Boolean
): Flow<String> where T : Enum<T>, T : StyleState =
    upstream.map { value ->
        if (mapper(value)) "${T::class.simpleName}-$name" else ""
    }



