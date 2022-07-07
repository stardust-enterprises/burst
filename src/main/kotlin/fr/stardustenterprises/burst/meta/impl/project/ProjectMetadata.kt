package fr.stardustenterprises.burst.meta.impl.project

import fr.stardustenterprises.burst.meta.Data
import fr.stardustenterprises.burst.meta.impl.pom.PomMetadata
import fr.stardustenterprises.burst.meta.impl.scm.ScmMetadata
import fr.stardustenterprises.burst.meta.mutateAndGet
import fr.stardustenterprises.burst.property.MissingPropertyException
import fr.stardustenterprises.burst.property.data
import org.gradle.api.Project

/**
 * @author xtrm
 * @since 4.0.0
 */
class ProjectMetadata : Data<Project>() {
    var name by data<String>()
    var version by data<String>(true)
    var description by data<String>()

    var group by data<String>(true)
    var vendor by data<String>()

    // both required for publication extension
    var pom by data<PomMetadata>(true)
    var scm by data<ScmMetadata>(true)

    fun pom(block: PomMetadata.() -> Unit): PomMetadata =
        try {
            pom!!
        } catch (ignored: MissingPropertyException) {
            PomMetadata()
        }.apply(block).mutateAndGet(this)

    fun scm(block: ScmMetadata.() -> Unit): ScmMetadata =
        try {
            scm!!
        } catch (ignored: MissingPropertyException) {
            ScmMetadata()
        }.apply(block).mutateAndGet(this)

    override fun mutate(target: Project) {
        target.version = version!!
        target.group = group!!
        target.description = description
    }
}
