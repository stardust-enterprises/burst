package fr.stardustenterprises.burst.feature.lang

import fr.stardustenterprises.burst.burstRegistry
import fr.stardustenterprises.burst.feature.Feature
import fr.stardustenterprises.burst.feature.FeatureRootRegistry
import org.gradle.api.Project
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin

class KotlinFeature: Feature() {
    override fun mutate(target: Project) {
        val featureRootRegistry = target.burstRegistry["features"]!! as FeatureRootRegistry
        val javaFeature = featureRootRegistry.features
            .firstOrNull { it is JavaFeature } as JavaFeature?
            ?: featureRootRegistry.java {  }

        target.apply<KotlinPlatformJvmPlugin>()
        target.configure<KotlinJvmProjectExtension> {
            target {
                jvmToolchain {
                    languageVersion.set(JavaLanguageVersion.of(javaFeature.version!!))
                }
            }
        }
    }
}

inline fun FeatureRootRegistry.kotlin(crossinline block: KotlinFeature.() -> Unit) {
    features += KotlinFeature().apply(block)
}
