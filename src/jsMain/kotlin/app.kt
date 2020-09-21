import dev.fritz2.dom.html.render
import dev.fritz2.styling.*
import dev.fritz2.styling.components.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import dev.fritz2.routing.router
import dev.fritz2.binding.RootStore
import dev.fritz2.binding.const
import dev.fritz2.binding.handledBy
import dev.fritz2.dom.mount
import dev.fritz2.dom.selectedIndex
import dev.fritz2.dom.html.*
import dev.fritz2.styling.params.*
import kotlinx.coroutines.flow.*

val themes = listOf(
        ("small Fonts") to DefaultTheme(),
        ("large Fonts") to Default2()
)

@ExperimentalCoroutinesApi
fun main() {

    val router = router("")

    render { theme: ExtendedTheme ->
        section {
            flex({
                height { "60px" }
                wrap { nowrap }
                direction { row }
                justifyContent { spaceEvenly }
                alignItems { center }
            }) {
                link({
                    flex {
                        //grow { "2" }
                        order { "1" }
                        alignSelf { flexStart }
                    }
                }) {
                    href = const("#")
                    +"flex"
                }
                link {
                    href = const("#grid")
                    +"grid"
                }
            }
            router.render { site ->
                when (site) {
                    "grid" -> gridDemo()
                    else -> flexDemo()
                }
            }.bind()
        }
    }.mount("target")
}

@ExperimentalCoroutinesApi
fun HtmlElements.flexDemo(): Div {

    val themeStore = object : RootStore<Int>(0) {
        val selectTheme = handle<Int> { _, index ->
            currentTheme.value = themes[index].second
            index
        }
    }

    return div {
        themeStore.data.map {
            div {
                sel {
                    value = themeStore.data.map { i -> themes[i].first }
                    themes.forEach {
                        option { +it.first }
                    }

                    changes.selectedIndex() handledBy themeStore.selectTheme
                }
                flex({
//                    boxShadow { tiny }
                    margins(md = { top { large } })
//                    margin { large }
//                    padding { small }
//                    border("1px solid lightgrey")
                    //backgroundSize(theme.test.a) // access custom value added by specific theme, for colors, etc.
                    direction(sm = { column }, md = { row })
                }) {
                    box({ /* flexShrink("0") */
                        margins { left { small } }

                        //position { relative }
                        zIndex { layer(1) }
                    }) {
                        image({
                            boxShadow { larger }
//                            borderRadius(theme.radii[1])
                            width { small }
                        }) {
                            src = const("https://bit.ly/2jYM25F")
                            alt = const("Woman paying for a purchase")
                        }
                    }
                    //val header = gridTemplate()
                    box({
                        position {
                            top { small }
                            relative
                        }
                        zIndex { base }
                        //position { absolute }
                        //bottom("3em")
                        width { "300px" }
                        margins(
                                {
                                    top { small }
                                    bottom { large }
                                },
                                md = { left { normal } }
                        )
                        //top("-3em")
                    }) {
                        text({
                            //gridArea {  header }
                            fontWeight { semiBold }
                            textTransform { uppercase }
                            fontSize { smaller }
                            letterSpacing { large }
                            textShadow { small }
                            color { info }
                        }) { +"Marketing" }
                        link({
                            margins { top { tiny } }
//                            display("block")
                            fontSize { normal }
                            lineHeight { normal }
                            fontWeight { bold }
                        }) {
                            href = const("#")
                            +"Finding customers for your new business"
                        }
                        text({
                            margins { top { smaller } }
                            color { dark }
                        }) {
                            +"Getting a new business off the ground is a lot of hard work. Here are five ideas you can use to find your first customers."
                        }
                    }
                }
            }
        }.bind()
    }
}

@ExperimentalCoroutinesApi
fun HtmlElements.gridDemo(): Div {
    // example from https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Grid_Layout/Layout_using_Named_Grid_Lines
    return div {
        val grid = object {
            val HEADER: AreaName = "header"
            val SIDEBAR: AreaName = "sidebar"
            val CONTENT: AreaName = "content"
            val FOOTER: AreaName = "footer"
        }
        grid({
            fontSize { normal }
            columns {
                repeat(9) { "9fr" }
                //repeat(autoFill) { "9fr" }
            }

            autoRows {
                minmax("100px", auto)
                //minmax(minContent, auto)
                //minmax("100px", auto)
            }

            areas(
                    sm = {
                        with(grid) {
                            row(HEADER, HEADER, HEADER, HEADER, HEADER, HEADER, HEADER, HEADER, HEADER)
                            row(SIDEBAR, SIDEBAR, SIDEBAR, SIDEBAR, SIDEBAR, SIDEBAR, SIDEBAR, SIDEBAR, SIDEBAR)
                            row(CONTENT, CONTENT, CONTENT, CONTENT, CONTENT, CONTENT, CONTENT, CONTENT, CONTENT)
                            row(FOOTER, FOOTER, FOOTER, FOOTER, FOOTER, FOOTER, FOOTER, FOOTER, FOOTER)
                        }
                    },
                    md = {
                        with(grid) {
                            row(HEADER, HEADER, HEADER, HEADER, HEADER, HEADER, HEADER, HEADER, HEADER)
                            row(SIDEBAR, SIDEBAR, SIDEBAR, CONTENT, CONTENT, CONTENT, CONTENT, CONTENT, CONTENT)
                            row(FOOTER, FOOTER, FOOTER, FOOTER, FOOTER, FOOTER, FOOTER, FOOTER, FOOTER)
                        }
                    }
            )
            gap { large }
            //autoFlow { dense }
            //raw("place-items: stretch flex-end;")
            //justifyContent { spaceEvenly }
            //alignItems { start }
        }) {
            box({
                grid { area { grid.HEADER } }
                //bgColor { "green" }
                background {
                    color { "lime" }
                }
            }) {
                text { +"Header" }
            }
            box({
                grid { area { grid.SIDEBAR } }
                bgColor { "yellow" }
            }) {
                text { +"Sidebar" }
            }
            box({
                grid(sm = { area { grid.CONTENT } })
                background(
                        sm = {
                            image { "https://via.placeholder.com/150/?text=Klein" }
                            repeat { repeat }
                            positions {
                                horizontal { center }
                                vertical { center }
                            }
                            /*
                            sizes {
                                horizontal { "100%" }
                                vertical { "80%" }
                            }
                             */
                        },
                        md = {
                            image { "https://via.placeholder.com/300/A9A9A9?text=Gross" }
                            repeat { repeat }
                            positions {
                                horizontal { center }
                                vertical { center }
                            }
                        }
                )
            }) {
                text { +"Content" }
            }
            box({
                grid { area { grid.FOOTER } }
                bgColor { "lime" }
            }) {
                text { +"Footer" }
            }
            box({
                margin { normal }
                grid(
                        sm = {
                            row {
                                start { grid.HEADER.start }
                                //end { grid.FOOTER.end }
                                //end { span(grid.SIDEBAR) }
                                end { grid.CONTENT.end }
                            }
                            column {
                                start { grid.CONTENT.start }
                                end { grid.CONTENT.end }
                            }
                        },
                        md = {
                            row {
                                start { grid.HEADER.start }
                                end { span(2) }
                            }
                            column {
                                start { grid.CONTENT.start }
                                end { grid.CONTENT.end }
                            }
                        }
                )
                //bgColor { "rgba(255, 0, 0, 0.5)" }
                background {
                    //blendMode { darken }
                    color { rgba(255, 0, 0, 0.5) }
                }
            }) {
                text { +"Overlay" }
            }
        }
    }
}
