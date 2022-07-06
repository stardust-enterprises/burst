package fr.stardustenterprises.burst.meta.project

import fr.stardustenterprises.burst.gradle.expect
import fr.stardustenterprises.burst.meta.pom.PomMetadata
import fr.stardustenterprises.burst.meta.scm.ScmMetadata
import fr.stardustenterprises.burst.meta.scm.ScmMetadataBuilder
import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra

/**
 * @author xtrm
 * @since 4.0.0
 */
data class ProjectMetadata(
    val name: String?,
    val version: String,
    val description: String?,

    val group: String,
    val vendor: String?,

    val scmMetadata: ScmMetadata?,
    val pomMetadata: PomMetadata?,
) {
    fun applyTo(project: Project) {
        project.version = version
        project.group = group
        project.description = description

        project.extra["name"] = name ?: project.name
        project.extra["vendor"] = vendor

        project.extra["scm"] = scmMetadata
        project.extra["pom"] = pomMetadata
    }

    companion object {
        fun build(block: ProjectMetadataBuilder.() -> Unit): ProjectMetadata =
            ProjectMetadataBuilder().apply(block).build()
    }
}

class ProjectMetadataBuilder {
    var name: String? = null
    var version: String? = null
    var description: String? = null

    var group: String? = null
    var vendor: String? = null

    var pom: PomMetadata? = null
    var scm: ScmMetadata? = null

//    fun pom(block: PomMetadataBuilder.() -> Unit): PomMetadata =
//        PomMetadata.builder(block)

    fun scm(block: ScmMetadataBuilder.() -> Unit) =
        ScmMetadata.builder(block).also { scm = it }

    fun build(): ProjectMetadata = run {
        expect(version, "version")
        expect(group, "group")

        ProjectMetadata(name, version!!, description, group!!, vendor, scm, pom)
    }
}
