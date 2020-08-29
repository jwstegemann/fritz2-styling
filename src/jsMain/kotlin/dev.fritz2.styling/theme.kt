package dev.fritz2.styling

typealias Property = String

class ResponsiveValue<T>(val sm: T, val md: T = sm, val lg: T = md, val xl: T = lg)

interface Theme {
    val breakPoints: ResponsiveValue<Property>

    val mediaQueryMd: String
    val mediaQueryLg: String
    val mediaQueryXl: String


    //TODO: use symbols to access entries, make it expandable
    val space: List<Property>
    val fontSizes: List<Property>
    val colors: List<Property>
    val fonts: List<Property>
    val fontWeights: List<Property>
    val lineHeights: List<Property>
    val letterSpacings: List<Property>
    val sizes: List<Property>
    val borders: List<Property>
    val borderWidths: List<Property>
    val borderStyles: List<Property>
    val radii: List<Property>
    val shadows: List<Property>
    val zIndices: List<Property>

}

object Default : Theme {
    override val breakPoints = ResponsiveValue("30em", "48em", "62em", "80em")

    override val mediaQueryMd: String = "@media screen and (min-width: ${breakPoints.md})"
    override val mediaQueryLg: String = "@media screen and (min-width: ${breakPoints.lg})"
    override val mediaQueryXl: String = "@media screen and (min-width: ${breakPoints.xl})"

    override val space: List<Property> = listOf(
        "0",
        "0.25rem",
        "0.5rem",
        "0.75rem",
        "1rem",
        "1.25rem",
        "1.5rem",
        "2rem",
        "2.5rem",
        "3rem",
        "4rem",
        "5rem",
        "6rem",
        "8rem",
        "10rem",
        "12rem",
        "14rem",
        "16rem"
    )

    override val fontSizes: List<Property> = listOf(
        "0.75rem",
        "0.875rem",
        "1rem",
        "1.125rem",
        "1.25rem",
        "1.5rem",
        "1.875rem",
        "2.25rem",
        "3rem",
        "4rem"
    )

    override val colors: List<Property> = listOf()

    override val fonts: List<Property> = listOf(
        """-apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol" """,
        """-apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol" """,
        """SFMono-Regular,Menlo,Monaco,Consolas,"Liberation Mono","Courier New",monospace"""
    )

    override val fontWeights: List<Property> = listOf()
    override val lineHeights: List<Property> = listOf()
    override val letterSpacings: List<Property> = listOf()
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

    override val shadows: List<Property> = listOf()
    override val zIndices: List<Property> = listOf()
}
