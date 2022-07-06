package fr.stardustenterprises.burst.meta.scm

import fr.stardustenterprises.burst.gradle.expect

data class ScmMetadata(
    val connection: String,
    val developerConnection: String,
    val url: String,
) {
    companion object {
        fun builder(block: ScmMetadataBuilder.() -> Unit) =
            ScmMetadataBuilder().apply(block).build()
    }
}

class ScmMetadataBuilder {
    var connection: String? = null
    var developerConnection: String? = null
    var url: String? = null

    var serviceHost: String = "github.com"
    var repositoryId: String? = null

    fun build(): ScmMetadata {
        if (repositoryId != null) {
            connection = connection
                ?: "scm:git:git://$serviceHost/$repositoryId.git"
            developerConnection = developerConnection
                ?: "scm:git:ssh://$serviceHost/$repositoryId.git"
            url = url
                ?: "https://$serviceHost/$repositoryId.git"
        }

        expect(connection, "scm.connection")
        expect(developerConnection, "scm.developerConnection")
        expect(url, "scm.url")

        return ScmMetadata(connection!!, developerConnection!!, url!!)
    }
}
