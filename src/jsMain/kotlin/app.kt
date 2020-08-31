import dev.fritz2.binding.const
import dev.fritz2.dom.mount
import dev.fritz2.styling.Default
import dev.fritz2.styling.components.box
import dev.fritz2.styling.components.image
import dev.fritz2.styling.components.link
import dev.fritz2.styling.components.text
import dev.fritz2.styling.render

fun main() {
    render(Default) {
        box({
            margin(it.space[8])
            padding(it.space[4])
            border("1px solid lightgrey")
            // backgroundSize(it.test.a) // access custom value added by specific theme, for colors, etc.
            display(md = " flex")
        }) {
            box({ flexShrink("0") }) {
                image({
                    borderRadius(Default.radii[1])
                    width(md = "10rem")
                }) {
                    src = const("https://bit.ly/2jYM25F")
                    alt = const("Woman paying for a purchase")
                }
            }
            box({ marginTop(Default.space[4], md = "0"); marginLeft(md = Default.space[6]) }) {
                text({
                    fontWeight("bold")
                    textTransform("uppercase")
                    fontSize(Default.fontSizes[0])
                    letterSpacing("0.025em")
                    color("#2C7A7B")
                }) { +"Marketing" }
                link({
                    marginTop(Default.space[1])
                    display("block")
                    fontSize(Default.fontSizes[2])
                    lineHeight("normal")
                    fontWeight("semibold")
                }) {
                    href = const("#")
                    +"Finding customers for your new business"
                }
                text({ marginTop(Default.space[2]); color("#718096") }) {
                    +"Getting a new business off the ground is a lot of hard work. Here are five ideas you can use to find your first customers."
                }
            }
        }
    }.mount("target")
}

