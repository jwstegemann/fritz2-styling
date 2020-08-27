class ResponsiveValue<T>(val sm: T, val md: T = sm, val lg: T = md, val xl: T = lg)

interface Theme {
    val breakPoints: ResponsiveValue<Property>

    val mediaQueryMd: String
    val mediaQueryLg: String
    val mediaQueryXl: String

    val space: ResponsiveValue<Property>

}

object DefaultTheme : Theme {
    override val breakPoints = ResponsiveValue("", "38em", "52em", "66em")

    override val mediaQueryMd: String = "@media screen and (min-width: ${breakPoints.md})"
    override val mediaQueryLg: String = "@media screen and (min-width: ${breakPoints.lg})"
    override val mediaQueryXl: String = "@media screen and (min-width: ${breakPoints.xl})"

    override val space = ResponsiveValue("0.2em", lg = "0.4em")
}
