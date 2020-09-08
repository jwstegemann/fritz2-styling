package dev.fritz2.styling

import dev.fritz2.dom.Tag
import dev.fritz2.dom.html.HtmlElements
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import org.w3c.dom.Element

typealias Property = String

class ResponsiveValue<T>(val sm: T, val md: T = sm, val lg: T = md, val xl: T = lg)

class ScaledValue<T>(
    val normal: T,
    val small: T = normal,
    val smaller: T = small,
    val tiny: T = smaller,
    val large: T = normal,
    val larger: T = large,
    val huge: T = larger,
    val none: T = tiny,
    val full: T = huge
)

interface Fonts {
    val body: Property
    val heading: Property
    val mono: Property
}

interface Theme {
    val breakPoints: ResponsiveValue<Property>

    val mediaQueryMd: String
    val mediaQueryLg: String
    val mediaQueryXl: String

    val space: ScaledValue<Property>
    val fontSizes: ScaledValue<Property>
    val colors: List<Property>
    val fonts: Fonts
    val lineHeights: ScaledValue<Property>
    val letterSpacings: ScaledValue<Property>
    val sizes: List<Property>
    val borders: List<Property>
    val borderWidths: List<Property>
    val borderStyles: List<Property>
    val radii: List<Property>
    val shadows: List<Property>
    val zIndices: List<Property>

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

    override val colors: List<Property> = listOf()

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

    override val shadows: List<Property> = listOf()
    override val zIndices: List<Property> = listOf()
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
