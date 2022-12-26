package enterprises.stardust.burst.gradle.plugin

import enterprises.stardust.stargrad.StargradPlugin
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.maven
import org.gradle.kotlin.dsl.repositories
import org.gradle.plugins.signing.SigningPlugin

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
        project.apply<MavenPublishPlugin>()
        project.apply<SigningPlugin>()

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
