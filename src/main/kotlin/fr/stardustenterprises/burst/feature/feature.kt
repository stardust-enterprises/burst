package fr.stardustenterprises.burst.feature

// this file is currently a POC

open class Feature(
    var enabled: Boolean = false
) {
    operator fun invoke(context: FeatureContext.() -> Unit = {}) {
        val ctx = FeatureContext().apply(context)
        this.enabled = ctx.enabled
    }
}

open class FeatureContext {
    var enabled: Boolean = true
}
