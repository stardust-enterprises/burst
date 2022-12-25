package fr.stardustenterprises.burst.feature.lang

import fr.stardustenterprises.burst.feature.Feature
import fr.stardustenterprises.burst.property.data
import org.gradle.api.Project
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper

class KotlinFeature: Feature() {
    var jvmVersion by data() { JavaLanguageVersion.of(8) }
    var jvmArgs by data<List<String>>() { emptyList() }

    override fun mutate(target: Project) {
        target.apply<KotlinPluginWrapper>()
        target.configure<KotlinJvmProjectExtension>() {
            jvmToolchain {
                languageVersion.set(jvmVersion)
            }
            kotlinDaemonJvmArgs = jvmArgs!!
        }
    }
}
