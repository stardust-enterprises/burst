package fr.stardustenterprises.burst.feature.lang

import fr.stardustenterprises.burst.feature.Feature
import fr.stardustenterprises.burst.property.data
import org.gradle.api.Project
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class JavaFeature: Feature() {
    var jvmVersion by data() { JavaLanguageVersion.of(8) }
    private var javadoc by data { true }
    private var sources by data { true }

    fun javadoc() {
        javadoc = true
    }

    fun sources() {
        sources = true
    }

    override fun mutate(target: Project) {
        target.apply<JavaLibraryPlugin>()
        target.configure<JavaPluginExtension> {
            if(javadoc!!) withJavadocJar()
            if(sources!!) withSourcesJar()
            toolchain {
                languageVersion.set(jvmVersion)
            }
        }
    }
}
