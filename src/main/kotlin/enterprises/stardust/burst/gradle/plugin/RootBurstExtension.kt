package enterprises.stardust.burst.gradle.plugin

import enterprises.stardust.burst.burstRegistry
import enterprises.stardust.burst.feature.FeatureRegistry
import enterprises.stardust.burst.meta.impl.ProjectMetadata
import enterprises.stardust.burst.meta.mutateAndGet
import enterprises.stardust.stargrad.ext.Extension
import enterprises.stardust.stargrad.ext.StargradExtension
import org.gradle.api.Project

/**
 * Root Burst extension.
 *
 * @author xtrm
 * @since 4.0.0
 */
@Extension("burst")
open class RootBurstExtension(
    project: Project,
    plugin: RootPlugin
) : StargradExtension<RootPlugin>(project, plugin) {
    init {
        project.burstRegistry["project.metadata"] = ProjectMetadata()
        project.burstRegistry["features"] = FeatureRegistry
    }

    fun project(block: ProjectMetadata.() -> Unit): ProjectMetadata =
        (project.burstRegistry["project.metadata"]!! as ProjectMetadata)
            .apply(block)
            .mutateAndGet(project)

    fun features(block: FeatureRegistry.() -> Unit): FeatureRegistry =
        (project.burstRegistry["features"]!! as FeatureRegistry)
            .apply(block)
            .mutateAndGet(project)

    val gradleProject: Project
        get() = this.project
}
