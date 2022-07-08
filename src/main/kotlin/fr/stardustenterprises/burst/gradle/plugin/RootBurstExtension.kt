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

    fun project(block: ProjectMetadata.() -> Unit): ProjectMetadata =
        (project.burstRegistry["project.metadata"]!! as ProjectMetadata)
            .apply(block)
            .mutateAndGet(project)

    fun features(block: FeatureRootRegistry.() -> Unit): FeatureRootRegistry =
        (project.burstRegistry["features"]!! as FeatureRootRegistry)
            .apply(block)
            .mutateAndGet(project)
}
