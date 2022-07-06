package fr.stardustenterprises.burst.meta.pom

/**
 * A POM-compliant License object.
 */
data class LicenseMetadata(
    val name: String,
    val url: String,
    val distribution: String = "repo",
)
