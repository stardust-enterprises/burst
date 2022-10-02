package fr.stardustenterprises.burst.feature

import fr.stardustenterprises.burst.feature.lang.JavaFeature
import fr.stardustenterprises.burst.feature.lang.KotlinFeature
import fr.stardustenterprises.burst.meta.Data
import org.gradle.api.Project

abstract class Feature: Data<Project>()

class FeatureRootRegistry: Data<Project>() {
    val features: MutableList<Feature> = mutableListOf()

    inline fun java(crossinline block: JavaFeature.() -> Unit) =
        JavaFeature()
            .apply(block)
            .apply(features::add)

    inline fun kotlin(crossinline block: KotlinFeature.() -> Unit) =
        KotlinFeature()
            .apply(block)
            .apply(features::add)

    override fun mutate(target: Project) {
        features
            .toList()
            .forEach { f -> f.mutate(target) }
    }
}
