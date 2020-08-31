package dev.fritz2.styling.components

import dev.fritz2.dom.html.Img
import dev.fritz2.styling.Theme
import dev.fritz2.styling.ThemedContext
import dev.fritz2.styling.params.BasicStyleParams
import dev.fritz2.styling.params.Style
import dev.fritz2.styling.params.use


inline fun <T : Theme> ThemedContext<T>.image(
    styles: Style<BasicStyleParams, T> = {},
    crossinline init: Img.() -> Unit
): Img {

    //FIXME: how to deal with attributes we want to add things to from component and init?
    return img(theme().use(styles, "img")) {
        init()
    }

}
