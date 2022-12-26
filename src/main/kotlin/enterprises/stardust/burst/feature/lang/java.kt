package enterprises.stardust.burst.feature.lang

import enterprises.stardust.burst.feature.Feature
import enterprises.stardust.burst.property.data
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class JavaFeature : Feature() {
    var jvmVersion by data {
        JavaLanguageVersion.of(JavaVersion.current().majorVersion)
    }
    var javadoc by data { false }
    var sources by data { false }

    fun javadoc() {
        javadoc = true
    }

    fun sources() {
        sources = true
    }

    override fun mutate(target: Project) {
        target.apply<JavaLibraryPlugin>()

        target.configure<JavaPluginExtension> {
            if (javadoc!!) withJavadocJar()
            if (sources!!) withSourcesJar()

            toolchain {
                languageVersion.set(jvmVersion)
            }
        }
    }
}
