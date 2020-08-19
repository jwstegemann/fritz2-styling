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

enum class Visibility(override val css: String) : StyleState {
    visible( // language=CSS prefix=".dummy {" suffix="}"
        """
            display: block 
        """
    ),
    hidden( // language=CSS prefix=".dummy {" suffix="}"
        "display: none"
    )
}


fun HtmlElements.myButton(bg: String, init: HtmlElements.(Flow<String>) -> Any): A {
    val myStyle = style( // language=CSS prefix=".dummy {" suffix="}"
        """
            border: 1px solid black;
            background-color: $bg;
            color: white;
            display: block;
            margin: 10px;
            padding: 5px;
            width: 100px;
        """, "test"
    )

    val context = storeOf(1).watch()

    val msgs = context.handleAndOffer<String> { model ->
        offer("you clicked $model times")
        model + 1
    }

    return a(myStyle) {
        className = Visibility.hidden.whenever(context.data) { it > 5 }
        clicks handledBy msgs
        init(msgs)
        +"ClickMe!"
    }
}


fun main() {
    style(Visibility::class)

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
                myButton(colors[bg]) { msgs ->
                    msgs handledBy model.showMessage
                }
            }
        }
    }.mount("target")
}