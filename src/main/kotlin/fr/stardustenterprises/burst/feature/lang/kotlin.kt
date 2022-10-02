package fr.stardustenterprises.burst.feature.lang

import fr.stardustenterprises.burst.burstRegistry
import fr.stardustenterprises.burst.feature.Feature
import fr.stardustenterprises.burst.feature.FeatureRootRegistry
import fr.stardustenterprises.burst.property.data
import org.gradle.api.Project
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper

class KotlinFeature: Feature() {
    var jvmArgs by data<List<String>>() { emptyList() }

    override fun mutate(target: Project) {
        val featureRootRegistry = target.burstRegistry["features"]!! as FeatureRootRegistry
        val javaFeature = featureRootRegistry.features
            .firstOrNull { it is JavaFeature } as JavaFeature?
            ?: featureRootRegistry.java {  }

        target.apply<KotlinPluginWrapper>()
        target.configure<KotlinJvmProjectExtension>() {
            jvmToolchain {
                languageVersion.set(javaFeature.javaVersion!!)
            }
            kotlinDaemonJvmArgs = jvmArgs!!
        }
    }
}

inline fun FeatureRootRegistry.kotlin(crossinline block: KotlinFeature.() -> Unit) =
    KotlinFeature()
        .apply(block)
        .apply(this.features::add)
