package dev.fritz2.styling.components

import dev.fritz2.dom.html.Div
import dev.fritz2.styling.Theme
import dev.fritz2.styling.ThemedContext
import dev.fritz2.styling.params.FlexStyleParams
import dev.fritz2.styling.params.Style
import dev.fritz2.styling.params.use


inline fun <T : Theme> ThemedContext<T>.box(
    styles: Style<FlexStyleParams, T> = {},
    crossinline init: ThemedContext<T>.() -> Any
): Div {

    return div(theme.use(styles, "box")) {
        ThemedContext(this, this@box.theme).init()
    }

}
