package enterprises.stardust.burst.meta.impl

import enterprises.stardust.burst.meta.Data
import enterprises.stardust.burst.meta.impl.pom.PomMetadata
import enterprises.stardust.burst.meta.mutateAndGet
import enterprises.stardust.burst.property.MissingPropertyException
import enterprises.stardust.burst.property.data
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
