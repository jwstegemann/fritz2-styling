package dev.fritz2.styling.components

import dev.fritz2.dom.html.A
import dev.fritz2.styling.Theme
import dev.fritz2.styling.ThemedContext
import dev.fritz2.styling.params.BasicStyleParams
import dev.fritz2.styling.params.Style
import dev.fritz2.styling.params.use
import dev.fritz2.styling.staticStyle

val link = staticStyle(
    "link",
    """
    transition: all 0.15s ease-out;
    cursor: pointer;
    textDecoration: none;
    outline: none;
    color: inherit;
    
    &:hover {
        textDecoration: underline;    
    }

    &:focus {
        boxShadow: outline;
    }
    
    &:disabled {
        opacity: 0.4;
        cursor: not-allowed;
        textDecoration: none;
    }
"""
)

inline fun <T : Theme> ThemedContext<T>.link(
    styles: Style<BasicStyleParams, T> = {},
    crossinline init: A.() -> Any
): A {

    return renderContext.a("$link ${theme.use(styles, "link")}") {
        init()
    }

}
