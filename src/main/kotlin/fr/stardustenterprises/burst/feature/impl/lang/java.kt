package fr.stardustenterprises.burst.feature.impl.lang

import fr.stardustenterprises.burst.feature.Feature
import fr.stardustenterprises.burst.property.data
import org.gradle.api.Project
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.kotlin.dsl.apply

class JavaFeature: Feature() {
    var targetVersion by data(true) { "1.8" }

    fun applyFeature(target: Project) {
        target.apply<JavaLibraryPlugin>()
    }
}
