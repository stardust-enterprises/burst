package fr.stardustenterprises.burst.gradle.plugin

import fr.stardustenterprises.stargrad.StargradPlugin
import org.gradle.kotlin.dsl.maven
import org.gradle.kotlin.dsl.repositories

/**
 * Burst's entrypoint plugin.
 *
 * @author xtrm
 * @since 1.0.0
 */
class RootPlugin : StargradPlugin() {
    override val id: String = "fr.stardustenterprises.burst.root"
    lateinit var rootExtension: RootBurstExtension

    override fun applyPlugin() {
        // TODO: Maybe remove this..? Seems like the plugin should not do this
        project.repositories {
            mavenCentral()
            gradlePluginPortal()
            google()
            maven("https://jitpack.io")
        }

        rootExtension = registerExtension()
    }
}
