import dev.fritz2.binding.RootStore
import dev.fritz2.binding.const
import dev.fritz2.binding.handledBy
import dev.fritz2.dom.mount
import dev.fritz2.dom.selectedIndex
import dev.fritz2.styling.*
import dev.fritz2.styling.components.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map

val themes = listOf(
    ("small Fonts") to DefaultTheme(),
    ("large Fonts") to Default2()
)

@ExperimentalCoroutinesApi
fun main() {
    val themeStore = object : RootStore<Int>(0) {
        val selectTheme = handle<Int> { _, index ->
            currentTheme.value = themes[index].second
            index
        }
    }

    themeStore.data.map {
        console.log("rendering with $it")

        render { theme: ExtendedTheme ->
            div {
                sel {
                    value = themeStore.data.map { i -> themes[i].first }
                    themes.forEach {
                        option { +it.first }
                    }

                    changes.selectedIndex() handledBy themeStore.selectTheme
                }
                box({
                    boxShadow { tiny }
                    margins(md = { top { large } })
//                    margin { large }
                    padding { small }
//                    border("1px solid lightgrey")
                    //backgroundSize(theme.test.a) // access custom value added by specific theme, for colors, etc.
//                    display(md = "flex")
                }) {
                    box({ /* flexShrink("0") */ }) {
                        image({
                            boxShadow { larger }
//                            borderRadius(theme.radii[1])
//                            width(md = "10rem")
                        }) {
                            src = const("https://bit.ly/2jYM25F")
                            alt = const("Woman paying for a purchase")
                        }
                    }
                    box({ marginTop { small }; marginLeft(md = { normal }) }) {
                        text({
                            fontWeight { semiBold }
                            textTransform { uppercase }
                            fontSize { smaller }
                            letterSpacing { large }
                            textShadow { small }
                            color { info }
                        }) { +"Marketing" }
                        link({
                            marginTop { tiny }
//                            display("block")
                            fontSize { normal }
                            lineHeight { normal }
                            fontWeight { bold }
                        }) {
                            href = const("#")
                            +"Finding customers for your new business"
                        }
                        text({ marginTop { smaller }; color { dark } }) {
                            +"Getting a new business off the ground is a lot of hard work. Here are five ideas you can use to find your first customers."
                        }
                    }
                }
            }
        }
//        }
    }.mount("target")
}

