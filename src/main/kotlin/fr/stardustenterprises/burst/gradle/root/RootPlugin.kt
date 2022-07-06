package fr.stardustenterprises.burst.gradle.root

import fr.stardustenterprises.stargrad.StargradPlugin
import fr.stardustenterprises.stargrad.ext.Extension
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
        println("RootPlugin.applyPlugin()")

        project.repositories {
            mavenCentral()
            gradlePluginPortal()
            google()
        }

        rootExtension = this.project.extensions.create(
            RootBurstExtension::class.java
                .getDeclaredAnnotation(Extension::class.java)
                ?.name
                ?: throw RuntimeException(
                    "Extension class missing @Extension annotation!"
                ),
            RootBurstExtension::class.java,
        )

        printStuff()
        println("end state")
    }

    override fun afterEvaluate() {
        println("RootPlugin.afterEvaluate()")
        printStuff()
        println("end state")
    }

    private fun printStuff() {
        println("java plugin state: ${rootExtension.java.enabled}")

        println("project: ${rootExtension.project.projectMetadata}")
    }
}
