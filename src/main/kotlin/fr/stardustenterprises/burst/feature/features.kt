package fr.stardustenterprises.burst.feature

import fr.stardustenterprises.burst.meta.Data
import org.gradle.api.Project

abstract class Feature: Data<Project>()

class FeatureRootRegistry: Data<Project>() {
    val features: MutableList<Feature> = mutableListOf()

    override fun mutate(target: Project) {
        features
            .toList()
            .forEach { f -> f.mutate(target) }
    }
}
