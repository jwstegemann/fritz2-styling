package dev.fritz2.styling

import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.HtmlElements
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import org.w3c.dom.Element

typealias Property = String

class ResponsiveValue<T : Property>(val sm: T, val md: T = sm, val lg: T = md, val xl: T = lg)

fun rgb(r: Int, g: Int, b: Int) = "rgb($r,$g,$b)"
fun rgba(r: Int, g: Int, b: Int, a: Double) = "rgb($r,$g,$b,$a)"
fun hsl(h: Int, s: Int, l: Int) = "hsl($h,$s%,$l%)"
fun hsla(h: Int, s: Int, l: Int, a: Double) = "hsl($h,$s%,$l%,$a)"

open class ScaledValue<T : Property>(
    val normal: T,
    val small: T = normal,
    val smaller: T = small,
    val tiny: T = smaller,
    val large: T = normal,
    val larger: T = large,
    val huge: T = larger,
    open val none: T = tiny,
    val full: T = huge
) {
    val initial: T = "initial".unsafeCast<T>()
    val inherit: T = "inherit".unsafeCast<T>()
    val auto: T = "auto".unsafeCast<T>()
}

class WeightedValue<T : Property>(
    val normal: T,
    val lighter: T = normal,
    val light: T = lighter,
    val stronger: T = normal,
    val strong: T = stronger,
    val none: T = light,
    val full: T = strong
) {
    val initial: T = "initial".unsafeCast<T>()
    val inherit: T = "inherit".unsafeCast<T>()
}

interface Fonts {
    val body: Property
    val heading: Property
    val mono: Property
}

//factory- methods for colors (rgb/a)

interface Colors {
    val primary: Property
    val secondary: Property
    val tertiary: Property
    val success: Property
    val danger: Property
    val warning: Property
    val info: Property
    val light: Property
    val dark: Property
    val disabled: Property
}

fun shadow(
    offsetHorizontal: String,
    offsetVertical: String = offsetHorizontal,
    blur: String? = null,
    spread: String? = null,
    color: String? = null,
    inset: Boolean = false
): Property = buildString {

    append(offsetHorizontal, " ", offsetVertical)
    if (blur != null) append(" ", blur)
    if (spread != null) append(" ", spread)
    if (color != null) append(" ", color)
    if (inset) append(" inset")
}

class Shadows(
    normal: Property,
    small: Property = normal,
    smaller: Property = small,
    tiny: Property = smaller,
    large: Property = normal,
    larger: Property = large,
    huge: Property = larger,
    full: Property = large
) : ScaledValue<Property>(normal, small, smaller, tiny, large, larger, huge, full = full) {

    override val none: Property = "none"
}

interface Theme {
    val breakPoints: ResponsiveValue<Property>

    val mediaQueryMd: String
    val mediaQueryLg: String
    val mediaQueryXl: String

    val space: ScaledValue<Property>
    val fontSizes: ScaledValue<Property>
    val colors: Colors
    val fonts: Fonts
    val lineHeights: ScaledValue<Property>
    val letterSpacings: ScaledValue<Property>
    val sizes: List<Property>
    val borders: List<Property>
    val borderWidths: List<Property>
    val borderStyles: List<Property>
    val radii: List<Property>
    val shadows: Shadows
    val zIndices: List<Property>
    val opacities: WeightedValue<Property>

}

interface ExtendedTheme : Theme {
    interface MyProp {
        val a: Property
        val b: Property
    }

    val test: MyProp
}


open class DefaultTheme : ExtendedTheme {
    final override val breakPoints = ResponsiveValue("30em", "48em", "62em", "80em")

    override val mediaQueryMd: String = "@media screen and (min-width: ${breakPoints.md})"
    override val mediaQueryLg: String = "@media screen and (min-width: ${breakPoints.lg})"
    override val mediaQueryXl: String = "@media screen and (min-width: ${breakPoints.xl})"

    override val space = ScaledValue(
        none = "0",
        tiny = "0.25rem",
        smaller = "0.5rem",
        small = "0.75rem",
        normal = "1rem",
        large = "1.25rem",
        larger = "1.5rem",
        huge = "2rem",
        full = "2.5rem"
    )

    override val fontSizes = ScaledValue(
        smaller = "0.75rem",
        small = "0.875rem",
        normal = "1rem",
        large = "1.125rem",
        larger = "1.5rem",
        huge = "2.25rem"
    )

    override val colors = object : Colors {
        override val primary = "#007bff"
        override val secondary = "#6c757d"
        override val tertiary = "#6c757d"
        override val success = "#28a745"
        override val danger = "#dc3545"
        override val warning = "#ffc107"
        override val info = "#17a2b8"
        override val light = "#f8f9fa"
        override val dark = "#343a40"
        override val disabled = "#6c757d"
    }

    override val fonts = object : Fonts {
        override val body =
            """-apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol" """
        override val heading =
            """-apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol" """
        override val mono = """SFMono-Regular,Menlo,Monaco,Consolas,"Liberation Mono","Courier New",monospace"""
    }

    override val lineHeights = ScaledValue(
        normal = "normal",
        tiny = "1",
        small = "1.25",
        smaller = "1.375",
        large = "1.5",
        larger = "1.625",
        huge = "2"
    )

    override val letterSpacings = ScaledValue(
        smaller = "-0.05em",
        small = "-0.025em",
        normal = "0",
        large = "0.025em",
        larger = "0.05em",
        huge = "0.1em"
    )

    override val sizes: List<Property> = listOf()

    override val borders: List<Property> = listOf("0", "1px solid", "2px solid", "4px solid")

    override val borderWidths: List<Property> = listOf()
    override val borderStyles: List<Property> = listOf()

    override val radii: List<Property> = listOf(
        "0.125rem",
        "0.25rem",
        "0.5rem",
        "9999px"
    )

    override val test = object : ExtendedTheme.MyProp {
        override val a: Property = space.normal
        override val b: Property = "b"
    }

    override val shadows = Shadows(
        smaller = shadow("0", "1px", "2px", color = "rgba(0, 0, 0, 0.05)"),
        small = shadow("0", "1px", "3px", "0", color = "rgba(0, 0, 0, 0.1)") + ", " + shadow(
            "0",
            "1px",
            "2px",
            "0",
            color = "rgba(0, 0, 0, 0.06)"
        ),
        normal = shadow("0", "4px", "6px", "-1px", "rgba(0, 0, 0, 0.1)") + ", " + shadow(
            "0",
            "2px",
            "4px",
            "-1px",
            "rgba(0, 0, 0, 0.06)"
        ),
        large = shadow("0", "10px", "15px", "-3px", "rgba(0, 0, 0, 0.1)") + ", " + shadow(
            "0",
            "4px",
            "6px",
            "-2px",
            "rgba(0, 0, 0, 0.05)"
        ),
        larger = shadow("0", "20px", "25px", "-5px", "rgba(0, 0, 0, 0.1)") + ", " + shadow(
            "0",
            "10px",
            "10px",
            "-5px",
            "rgba(0, 0, 0, 0.04)"
        ),
        huge = shadow("0", "25px", "50px", "-12px", "rgba(0, 0, 0, 0.25)"),
        tiny = shadow("0", spread = "3px", color = "rgba(66, 153, 225, 0.6)"),
        full = shadow("0", "2px", "4px", color = "rgba(0,0,0,0.06)", inset = true)
    )

    override val zIndices: List<Property> = listOf()

    override val opacities = WeightedValue(
        normal = "0.5"
    )
}

class Default2 : DefaultTheme() {
    override val fontSizes = ScaledValue<Property>(
        smaller = "1.125rem",
        small = "1.25rem",
        normal = "1.5rem",
        large = "1.875rem",
        larger = "2.25rem",
        huge = "3rem"
    )
}


val currentTheme = MutableStateFlow<Theme>(DefaultTheme())

@ExperimentalCoroutinesApi
fun theme(): Theme = currentTheme.value

@ExperimentalCoroutinesApi
fun <T : Theme> theme(): Theme = currentTheme.value.unsafeCast<T>()

@ExperimentalCoroutinesApi
inline fun <E : Element, T : Theme> render(crossinline content: HtmlElements.(T) -> Tag<E>): Tag<E> =
    dev.fritz2.dom.html.render {
        content(currentTheme.value.unsafeCast<T>())
    }
