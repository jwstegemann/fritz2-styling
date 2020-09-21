package dev.fritz2.styling.params

import dev.fritz2.dom.HtmlTagMarker
import dev.fritz2.styling.Property

typealias BackgroundRepeatProperty = Property

object BackgroundRepeats {
    const val repeat: BackgroundRepeatProperty = "repeat"
    const val repeatX: BackgroundRepeatProperty = "repeat-x"
    const val repeatY: BackgroundRepeatProperty = "repeat-y"
    const val noRepeat: BackgroundRepeatProperty = "no-repeat"
    const val space: BackgroundRepeatProperty = "space"
    const val round: BackgroundRepeatProperty = "round"
    const val initial: BackgroundRepeatProperty = "initial"
    const val inherit: BackgroundRepeatProperty = "inherit"
}

typealias BackgroundBlendModeProperty = Property

object BackgroundBlendModes {
    const val normal: BackgroundBlendModeProperty = "normal"
    const val multiply: BackgroundBlendModeProperty = "multiply"
    const val screen: BackgroundBlendModeProperty = "screen"
    const val overlay: BackgroundBlendModeProperty = "overlay"
    const val darken: BackgroundBlendModeProperty = "darken"
    const val lighten: BackgroundBlendModeProperty = "lighten"
    const val colorDodge: BackgroundBlendModeProperty = "color-dodge"
    const val colorBurn: BackgroundBlendModeProperty = "color-burn"
    const val hardLight: BackgroundBlendModeProperty = "hard-light "
    const val softLight: BackgroundBlendModeProperty = "soft-light"
    const val difference: BackgroundBlendModeProperty = "difference"
    const val exclusion: BackgroundBlendModeProperty = "exclusion"
    const val hue: BackgroundBlendModeProperty = "hue"
    const val saturation: BackgroundBlendModeProperty = "saturation"
    const val color: BackgroundBlendModeProperty = "color"
    const val luminosity: BackgroundBlendModeProperty = "luminosity"
}

typealias BackgroundPositionProperty = Property

object BackgroundPositions {
    const val left: BackgroundPositionProperty = "left"
    const val center: BackgroundPositionProperty = "center"
    const val right: BackgroundPositionProperty = "right"
    const val top: BackgroundPositionProperty = "top"
    const val bottom: BackgroundPositionProperty = "bottom"
}

class BackgroundPositionContext(
        styleParams: StyleParams,
        private val values: MutableList<Property>)
    : StyleParams by styleParams {
    fun horizontal(value: BackgroundPositions.() -> BackgroundPositionProperty) =
            values.add(BackgroundPositions.value())

    fun vertical(value: BackgroundPositions.() -> BackgroundPositionProperty) =
            values.add(BackgroundPositions.value())
}

typealias  BackgroundSizeProperty = Property

object BackgroundSizes {
    const val auto: BackgroundSizeProperty = "auto"
    const val cover: BackgroundSizeProperty = "cover"
    const val contain: BackgroundSizeProperty = "contain"
}

class BackgroundSizeContext(
        styleParams: StyleParams,
        private val values: MutableList<Property>)
    : StyleParams by styleParams {
    fun horizontal(value: BackgroundSizes.() -> BackgroundSizeProperty) =
            values.add(BackgroundSizes.value())

    fun vertical(value: BackgroundSizes.() -> BackgroundSizeProperty) =
            values.add(BackgroundSizes.value())
}

typealias BackgroundBoxProperty = Property

object BackgroundBoxValues {
    const val paddingBox: BackgroundBoxProperty = "padding-box"
    const val borderBox: BackgroundBoxProperty = "border-box"
    const val contentBox: BackgroundBoxProperty = "content-box"
}

typealias BackgroundAttachmentProperty = Property

object BackgroundAttachments {
    const val scroll: BackgroundAttachmentProperty = "scroll"
    const val fixed: BackgroundAttachmentProperty = "fixed"
    const val local: BackgroundAttachmentProperty = "local"
    const val inherit: BackgroundAttachmentProperty = "inherit"
}

internal const val backgroundBlendModeKey = "background-blend-mode: "
internal const val backgroundImageKey = "background-image: "
internal const val backgroundPositionKey = "background-position: "
internal const val backgroundSizeKey = "background-size: "
internal const val backgroundRepeatKey = "background-repeat: "
internal const val backgroundOriginKey = "background-origin: "
internal const val backgroundClipKey = "background-clip: "
internal const val backgroundAttachmentKey = "background-attachment: "
internal const val backgroundColorKey = "background-color: "

@StyleParamsMarker
@HtmlTagMarker
class BackgroundContext(
        styleParams: StyleParams,
        private val target: StringBuilder
) : StyleParams by styleParams {

    fun blendMode(value: BackgroundBlendModes.() -> BackgroundBlendModeProperty) =
            property(backgroundBlendModeKey, BackgroundBlendModes.value(), target)

    fun image(value: BackgroundContext.() -> Property) =
            property(backgroundImageKey, "url(${value()})", target)

    fun noImage() = property(backgroundImageKey, "none", target)

    fun position(value: BackgroundPositions.() -> BackgroundPositionProperty) =
            property(backgroundPositionKey, BackgroundPositions.value(), target)

    fun positions(value: BackgroundPositionContext.() -> Unit) {
        val properties: MutableList<Property> = mutableListOf()
        BackgroundPositionContext(this, properties).value()
        property(backgroundPositionKey, properties.joinToString(" ").trim(), target)
    }

    fun size(value: BackgroundSizes.() -> BackgroundSizeProperty) =
            property(backgroundSizeKey, BackgroundSizes.value(), target)

    fun sizes(value: BackgroundSizeContext.() -> Unit) {
        val properties: MutableList<Property> = mutableListOf()
        BackgroundSizeContext(this, properties).value()
        property(backgroundSizeKey, properties.joinToString(" ").trim(), target)
    }

    fun repeat(value: BackgroundRepeats.() -> BackgroundRepeatProperty) =
            property(backgroundRepeatKey, BackgroundRepeats.value(), target)

    fun origin(value: BackgroundBoxValues.() -> BackgroundBoxProperty) =
            property(backgroundOriginKey, BackgroundBoxValues.value(), target)

    fun clip(value: BackgroundBoxValues.() -> BackgroundBoxProperty) =
            property(backgroundClipKey, BackgroundBoxValues.value(), target)

    fun attachment(value: BackgroundAttachments.() -> BackgroundAttachmentProperty) =
            property(backgroundAttachmentKey, BackgroundAttachments.value(), target)

    fun color(value: BackgroundContext.() -> Property) = property(backgroundColorKey, value(), target)
}

interface Background : StyleParams {

    /*
    * background
    */
    fun background(value: BackgroundContext.() -> Unit) = BackgroundContext(this, smProperties).value()

    fun background(
        sm: (BackgroundContext.() -> Unit)? = null,
        md: (BackgroundContext.() -> Unit)? = null,
        lg: (BackgroundContext.() -> Unit)? = null,
        xl: (BackgroundContext.() -> Unit)? = null,
    ) {
        if(sm != null) BackgroundContext(this, smProperties).sm()
        if(md != null) BackgroundContext(this, mdProperties).md()
        if(lg != null) BackgroundContext(this, lgProperties).lg()
        if(xl != null) BackgroundContext(this, xlProperties).xl()
    }
}
