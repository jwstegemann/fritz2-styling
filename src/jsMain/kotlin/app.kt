import dev.fritz2.binding.RootStore
import dev.fritz2.binding.handledBy
import dev.fritz2.binding.storeOf
import dev.fritz2.binding.watch
import dev.fritz2.dom.html.A
import dev.fritz2.dom.html.HtmlElements
import dev.fritz2.dom.html.render
import dev.fritz2.dom.mount
import kotlinx.coroutines.flow.Flow
import kotlin.browser.window

val colors = listOf("green", "red", "blue", "yellow", "orange")

val hidden = staticStyle(
    "hidden", // language=CSS prefix=".dummy {" suffix="}"
    """
        display: none !important;
    """
)

val btn = staticStyle(
    "btn", // language=CSS prefix=".dummy {" suffix="}"
    """
            display: block;
            border: 1px solid black;
            color: aqua;
            margin: 10px;
            padding: 5px;
            width: 100px; 
    """
)

inline fun HtmlElements.myButton(styling: StyleClass? = null, crossinline init: HtmlElements.(Flow<String>) -> Any): A {
//inline fun HtmlElements.myButton(styling: Style<Color> = {}, crossinline init: HtmlElements.(Flow<String>) -> Any): A {

    val context = storeOf(1).watch()

    val msgs = context.handleAndOffer<String> { model ->
        offer("you clicked $model times")
        model + 1
    }

    return a("$btn $styling") {
        className = hidden.whenever(context.data) { it > 5 }
        clicks handledBy msgs
        init(msgs)
        +"ClickMe!"
    }
}


fun main() {
    val model = object : RootStore<String>("") {
        val showMessage = handle<String> { _, msg ->
            window.alert(msg)
            msg
        }
    }

    model.data.watch()

    render {
        div {
            (0..4).forEach { bg ->
                /*
                 * style params
                 */
//                myButton({
//                    bgColor(colors[bg], lg = "white")
//                }) { msgs ->
//                    msgs handledBy model.showMessage
//                }
//            }

                /*
                 * pure css
                 */
                myButton(
                    DefaultTheme.style(
                        // language=CSS prefix=".dummy {" suffix="}"
                        { "background-color: ${colors[bg]};" }, // access variables from scope
                        // language=CSS prefix=".dummy {" suffix="}"
                        lg = { "margin: ${space.lg};" }, // access value from theme
                        prefix = "myStyle"
                    )
                ) { msgs ->
                    msgs handledBy model.showMessage
                }
            }
        }
    }.mount("target")
}

