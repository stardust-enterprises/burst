package fr.stardustenterprises.burst.gradle.root

import fr.stardustenterprises.burst.feature.Feature
import fr.stardustenterprises.burst.feature.FeatureDelegation
import fr.stardustenterprises.burst.invokeScaffold
import fr.stardustenterprises.burst.meta.project.ProjectMetadataBuilder
import fr.stardustenterprises.stargrad.ext.Extension
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.tasks.Internal

@Extension("burst")
open class RootBurstExtension(project: Project) {
    val java by FeatureDelegation(Feature())

    @Internal
    protected val objects: ObjectFactory = project.objects

    val project = invokeScaffold<ProjectMetadataBuilder> {

    }
}
