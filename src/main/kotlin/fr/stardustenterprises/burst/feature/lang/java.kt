package fr.stardustenterprises.burst.feature.lang

import fr.stardustenterprises.burst.feature.Feature
import fr.stardustenterprises.burst.feature.FeatureRootRegistry
import fr.stardustenterprises.burst.property.data
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class JavaFeature: Feature() {
    var version by data() { "1.8" }
    var javadoc by data { true }
    var sources by data { true }

    override fun mutate(target: Project) {
        target.apply<JavaLibraryPlugin>()
        target.configure<JavaPluginExtension> {
            targetCompatibility = JavaVersion.toVersion(version!!)
            sourceCompatibility = JavaVersion.toVersion(version!!)
            if(javadoc!!) withJavadocJar()
            if(sources!!) withSourcesJar()
        }
    }
}

inline fun FeatureRootRegistry.java(crossinline block: JavaFeature.() -> Unit) =
    JavaFeature()
        .apply(block)
        .apply(this.features::add)
