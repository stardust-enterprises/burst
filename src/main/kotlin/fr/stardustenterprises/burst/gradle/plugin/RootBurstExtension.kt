package fr.stardustenterprises.burst.gradle.plugin

import fr.stardustenterprises.burst.burstRegistry
import fr.stardustenterprises.burst.feature.FeatureRootRegistry
import fr.stardustenterprises.burst.meta.impl.ProjectMetadata
import fr.stardustenterprises.burst.meta.mutateAndGet
import fr.stardustenterprises.stargrad.ext.Extension
import fr.stardustenterprises.stargrad.ext.StargradExtension
import org.gradle.api.Project

/**
 * Root Burst extension.
 *
 * @author xtrm
 * @since 4.0.0
 */
@Extension("burst")
open class RootBurstExtension(project: Project) : StargradExtension(project) {
    init {
        project.burstRegistry["project.metadata"] = ProjectMetadata()
        project.burstRegistry["features"] = FeatureRootRegistry()
    }

    inline fun Project.project(crossinline block: ProjectMetadata.() -> Unit): ProjectMetadata =
        (this.burstRegistry["project.metadata"]!! as ProjectMetadata)
            .apply(block)
            .mutateAndGet(this)

    inline fun Project.features(crossinline block: FeatureRootRegistry.() -> Unit): FeatureRootRegistry =
        (this.burstRegistry["features"]!! as FeatureRootRegistry)
            .apply(block)
            .mutateAndGet(this)
}
