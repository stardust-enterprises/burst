package fr.stardustenterprises.burst.meta.pom

/**
 * A POM-compliant Developer object.
 */
data class DeveloperMetadata(
    val name: String,
    val id: String,
    val email: String?,
) {
    constructor(
        id: String,
        email: String
    ) : this(id, id, email)

    constructor(
        id: String,
    ) : this(id, id, null)
}
