package enterprises.stardust.burst.feature

import enterprises.stardust.burst.feature.lang.JavaFeature
import enterprises.stardust.burst.feature.lang.KotlinFeature
import enterprises.stardust.burst.meta.Data
import org.gradle.api.Project
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

abstract class Feature : Data<Project>() {
}

object FeatureRegistry : Data<Project>() {
    internal val features = mutableListOf<Feature>()

    val java by feature<JavaFeature>()
    val kotlin by feature<KotlinFeature>()

    override fun mutate(target: Project) {
        println("Mutating project with ${features.size} features")
        features.forEach {
            println(" > ${it::class.simpleName}")
            it.mutate(target)
        }
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T : Feature> T.invoke(block: T.() -> Unit) {
        this.apply(block)
    }
}

class FeatureDelegate<T : Feature>(
    val featureClass: KClass<T>,
) : ReadOnlyProperty<FeatureRegistry, T> {
    private var feature: T? = null

    override fun getValue(thisRef: FeatureRegistry, property: KProperty<*>): T {
        if (feature == null) {
            feature = initialize().also {
                FeatureRegistry.features.add(it)
            }
        }
        return feature!!
    }

    private fun initialize(): T =
        featureClass.java.getConstructor().newInstance()
}

inline fun <reified T : Feature> feature(): FeatureDelegate<T> =
    FeatureDelegate(T::class)
