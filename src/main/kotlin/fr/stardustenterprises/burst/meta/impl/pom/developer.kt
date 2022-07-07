package fr.stardustenterprises.burst.meta.impl.pom

import fr.stardustenterprises.burst.meta.Data
import fr.stardustenterprises.burst.property.data

/**
 * A POM-compliant Developer object.
 */
class DeveloperMetadata : Data<PomMetadata>() {
    var name by data<String>()
    var id by data(required = true) { name }
    var email by data<String>()

    override fun mutate(target: PomMetadata) {
        this.name = name ?: id
        this.email?.let {
            this.name += " <$it>"
        }

        target.developers += this
    }
}

class DeveloperMetaHolder : Data<PomMetadata>() {
    private val developers: MutableList<DeveloperMetadata> =
        mutableListOf()

    fun developer(block: DeveloperMetadata.() -> Unit): DeveloperMetadata =
        DeveloperMetadata()
            .apply(block)
            .also { developers.add(it) }

    operator fun String.invoke(block: DeveloperMetadata.() -> Unit = {}): DeveloperMetadata =
        DeveloperMetadata()
            .apply { this.id = this@invoke }
            .apply(block)

    override fun mutate(target: PomMetadata) {
        developers.forEach { it.mutate(target) }
    }
}
