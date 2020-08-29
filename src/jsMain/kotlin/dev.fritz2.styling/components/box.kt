package dev.fritz2.styling.components

import dev.fritz2.dom.html.Div
import dev.fritz2.dom.html.HtmlElements
import dev.fritz2.styling.Default
import dev.fritz2.styling.params.FlexStyleParams
import dev.fritz2.styling.params.Style
import dev.fritz2.styling.params.use


inline fun HtmlElements.box(styles: Style<FlexStyleParams> = {}, crossinline init: Div.() -> Any): Div {

    return div(Default.use(styles)) {
        init()
    }

}
