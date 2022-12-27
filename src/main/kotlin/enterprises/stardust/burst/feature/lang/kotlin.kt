package enterprises.stardust.burst.feature.lang

import enterprises.stardust.burst.feature.Feature
import enterprises.stardust.burst.feature.FeatureRegistry
import enterprises.stardust.burst.property.data
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class KotlinFeature : Feature() {
    var jvmVersion by data<JavaLanguageVersion?>(required = false) { null }
    var jvmArgs by data<MutableList<String>> { mutableListOf() }
    var compilerArgs by data<MutableList<String>> { mutableListOf() }
    var languageVersion by data { "1.7" }

    override fun mutate(target: Project) {
        target.apply<KotlinPluginWrapper>()
        target.configure<KotlinJvmProjectExtension> {
            jvmToolchain {
                languageVersion.set(
                    jvmVersion
                        ?: FeatureRegistry.features.firstOrNull { it is JavaFeature }
                            ?.let { it as JavaFeature }
                            ?.jvmVersion
                        ?: JavaLanguageVersion.of(JavaVersion.current().majorVersion)
                )
            }
            kotlinDaemonJvmArgs = jvmArgs!!
        }
        target.tasks.withType(KotlinCompile::class.java) {
            kotlinOptions {
                jvmTarget = languageVersion
                freeCompilerArgs = compilerArgs!!
            }
        }
    }
}
