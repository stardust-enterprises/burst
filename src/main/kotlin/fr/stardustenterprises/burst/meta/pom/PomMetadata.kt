package fr.stardustenterprises.burst.meta.pom

/**
 * @author xtrm
 * @since 4.0.0
 */
data class PomMetadata(
    val licenses: List<LicenseMetadata>,
    val developers: List<DeveloperMetadata>,
) {
    companion object {
        fun builder(block: PomMetadataBuilder.() -> Unit): PomMetadata {
            TODO()
        }
    }
}

class PomMetadataBuilder {
//    fun license(block: LicenseBuilder)
}
