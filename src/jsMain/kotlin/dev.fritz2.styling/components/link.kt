package dev.fritz2.styling.components

import dev.fritz2.dom.html.A
import dev.fritz2.dom.html.HtmlElements
import dev.fritz2.styling.params.BasicStyleParams
import dev.fritz2.styling.params.Style
import dev.fritz2.styling.params.use
import dev.fritz2.styling.staticStyle
import kotlinx.coroutines.ExperimentalCoroutinesApi

val link = staticStyle(
    "link",
    """
    transition: all 0.15s ease-out;
    cursor: pointer;
    text-decoration: none;
    outline: none;
    color: inherit;
    
    &:hover {
        text-decoration: underline;    
    }

    &:focus {
        box-shadow: outline;
    }
    
    &:disabled {
        opacity: 0.4;
        cursor: not-allowed;
        text-decoration: none;
    }
"""
)

@ExperimentalCoroutinesApi
inline fun HtmlElements.link(
    styles: Style<BasicStyleParams> = {},
    crossinline init: A.() -> Any
): A {

    return a("$link ${use(styles, "link")}") {
        init()
    }

}
