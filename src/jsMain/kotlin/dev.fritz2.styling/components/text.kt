package dev.fritz2.styling.components

import dev.fritz2.dom.html.P
import dev.fritz2.styling.Theme
import dev.fritz2.styling.ThemedContext
import dev.fritz2.styling.params.BasicStyleParams
import dev.fritz2.styling.params.Style
import dev.fritz2.styling.params.use


inline fun <T : Theme> ThemedContext<T>.text(
    styles: Style<BasicStyleParams, T> = {},
    crossinline init: P.() -> Any = {}
): P {

    return p(theme().use(styles, "text")) {
        init()
    }

}
