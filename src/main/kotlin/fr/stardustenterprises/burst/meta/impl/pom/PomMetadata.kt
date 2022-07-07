package fr.stardustenterprises.burst.meta.impl.pom

import fr.stardustenterprises.burst.meta.Data
import fr.stardustenterprises.burst.meta.impl.ProjectMetadata
import fr.stardustenterprises.burst.meta.mutateAndGet

/**
 * @author xtrm
 * @since 4.0.0
 */
class PomMetadata : Data<ProjectMetadata>() {
    internal val developers: MutableList<DeveloperMetadata> =
        mutableListOf()
    internal val licences: MutableList<LicenseMetadata> =
        mutableListOf()

    fun licenses(block: LicenseMetaHolder.() -> Unit) {
        LicenseMetaHolder()
            .apply(block)
            .mutateAndGet(this)
    }

    fun developers(block: DeveloperMetaHolder.() -> Unit) {
        DeveloperMetaHolder()
            .apply(block)
            .mutateAndGet(this)
    }

    override fun mutate(target: ProjectMetadata) {
        target.pom = this
    }
}
