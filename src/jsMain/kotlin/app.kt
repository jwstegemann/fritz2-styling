import dev.fritz2.dom.html.render
import dev.fritz2.dom.mount
import dev.fritz2.styling.components.box


fun main() {
    render {
        box({
            margin("10px")
            color("red")
            size("50%", lg = "25%")
            border("1px solid")
            borderColor("black")
        }) {
            +"Bin da!!!"
        }
    }.mount("target")
}

