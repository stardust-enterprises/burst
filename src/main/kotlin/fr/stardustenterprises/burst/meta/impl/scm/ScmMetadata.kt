package fr.stardustenterprises.burst.meta.impl.scm

import fr.stardustenterprises.burst.meta.Data
import fr.stardustenterprises.burst.meta.impl.project.ProjectMetadata
import fr.stardustenterprises.burst.property.data

class ScmMetadata : Data<ProjectMetadata>() {
    var connection by data { "scm:git:git://$serviceHost/$repositoryId.git" }
    var developerConnection by data { "scm:git:ssh://$serviceHost/$repositoryId.git" }
    var url by data { "https://$serviceHost/$repositoryId.git" }

    // not actually required, but required for other properties' fallback values
    var serviceHost by data(true) { "github.com" }
    var repositoryId by data<String>(true)

    override fun mutate(target: ProjectMetadata) {
        target.scm = this
    }
}
