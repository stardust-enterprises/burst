package fr.stardustenterprises.burst.feature

import fr.stardustenterprises.burst.meta.Stringable

// this file is currently a POC

open class Feature(
    var enabled: Boolean = false
): Stringable() {
    operator fun invoke(context: FeatureContext.() -> Unit = {}) {
        val ctx = FeatureContext().apply(context)
        this.enabled = ctx.enabled
    }
}

open class FeatureContext: Stringable() {
    var enabled: Boolean = true
}
