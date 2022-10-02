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

    val version by data() { JavaVersion.VERSION_1_8 }
    val javadoc by data { false }
    val sources by data { false }

    override fun mutate(target: Project) {
        target.apply<JavaLibraryPlugin>()
        target.configure<JavaPluginExtension> {
            targetCompatibility = version!!
            sourceCompatibility = version!!
            if(javadoc!!) withJavadocJar()
            if(sources!!) withSourcesJar()
        }
    }

}

inline fun FeatureRootRegistry.java(crossinline block: JavaFeature.() -> Unit) {
    features += JavaFeature().apply(block)
}
