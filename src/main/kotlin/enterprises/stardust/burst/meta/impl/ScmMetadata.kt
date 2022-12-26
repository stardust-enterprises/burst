package enterprises.stardust.burst.meta.impl

import enterprises.stardust.burst.meta.Data
import enterprises.stardust.burst.property.data

class ScmMetadata : Data<ProjectMetadata>() {
    var connection by data { "scm:git:git://$serviceHost/$repositoryPath.git" }
    var developerConnection by data { "scm:git:ssh://$serviceHost/$repositoryPath.git" }
    var url by data { "https://$serviceHost/$repositoryPath.git" }

    // not actually required, but required for other properties' fallback values
    var serviceHost by data(true) { "github.com" }
    var repositoryPath by data<String>(true)

    override fun mutate(target: ProjectMetadata) {
        target.scm = this
    }
}
