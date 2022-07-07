package fr.stardustenterprises.burst.meta.impl.pom

import fr.stardustenterprises.burst.meta.Data
import fr.stardustenterprises.burst.property.data

/**
 * A POM-compliant License object.
 */
class LicenseMetadata : Data<PomMetadata>() {
    var name by data<String>(required = true)
    var url by data<String>()
    var distribution by data { "repo" }

    override fun mutate(target: PomMetadata) {
        target.licences += this
    }
}

class LicenseMetaHolder : Data<PomMetadata>() {
    internal val licenses: MutableList<LicenseMetadata> =
        mutableListOf()

    fun license(block: LicenseMetadata.() -> Unit): LicenseMetadata =
        LicenseMetadata()
            .apply(block)
            .also { licenses.add(it) }

    operator fun String.invoke(block: LicenseMetadata.() -> Unit = {}): LicenseMetadata =
        LicenseMetadata()
            .apply { this.name = this@invoke }
            .apply(block)

    override fun mutate(target: PomMetadata) {
        licenses.forEach { it.mutate(target) }
    }
}
