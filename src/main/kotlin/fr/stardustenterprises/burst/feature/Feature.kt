package fr.stardustenterprises.burst.feature

import kotlin.properties.Delegates
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

open class Feature {
    var enabled: Boolean by Delegates.observable(false) { _, oldValue, newValue ->
        println("change from $oldValue to $newValue")
    }

    operator fun invoke(context: FeatureContext.() -> Unit = {}) {
        val ctx = FeatureContext().apply(context)
        this.enabled = ctx.enabled
    }
}

data class FeatureDelegation<T : Feature>(
    val feature: T,
) : ReadOnlyProperty<Any?, T> {
    private var gradleInitialization: Boolean = false

    override operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return feature
    }
}

class FeatureContext {
    var enabled: Boolean = true
}
