package enterprises.stardust.burst.meta.impl.pom

import enterprises.stardust.burst.meta.Data
import enterprises.stardust.burst.property.data
import org.gradle.kotlin.dsl.provideDelegate
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

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

@Suppress("PropertyName")
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

    // Curated list of most-known-used-popular(?) licenses.
    // From https://spdx.org/licenses/

    val bsd0 by LicenseDelegate("0BSD")
    val agpl3 by LicenseDelegate("AGPL-3.0-only")
    val `agpl3+` by LicenseDelegate("AGPL-3.0-or-later")
    val apache1 by LicenseDelegate("Apache-1.0")
    val apache11 by LicenseDelegate("Apache-1.1")
    val apache2 by LicenseDelegate("Apache-2.0")

    //TODO: add BSD
    //TODO: add (full) Creative Commons?
    val ccbyncsa4 by LicenseDelegate("CC-BY-NC-SA-4.0")

    val gpl1 by LicenseDelegate("GPL-1.0-only")
    val `gpl1+` by LicenseDelegate("GPL-1.0-or-later")
    val gpl2 by LicenseDelegate("GPL-2.0-only")
    val `gpl2+` by LicenseDelegate("GPL-2.0-or-later")
    val gpl3 by LicenseDelegate("GPL-3.0-only")
    val `gpl3+` by LicenseDelegate("GPL-3.0-or-later")
    val isc by LicenseDelegate("ISC")
    val lgpl2 by LicenseDelegate("LGPL-2.0-only")
    val `lgpl2+` by LicenseDelegate("LGPL-2.0-or-later")
    val lgpl21 by LicenseDelegate("LGPL-2.1-only")
    val `lgpl21+` by LicenseDelegate("LGPL-2.1-or-later")
    val lgpl3 by LicenseDelegate("LGPL-3.0-only")
    val `lgpl3+` by LicenseDelegate("LGPL-3.0-or-later")
    val mit by LicenseDelegate("MIT")
    val mit0 by LicenseDelegate("MIT-0")
    val mpl1 by LicenseDelegate("MPL-1.0")
    val mpl11 by LicenseDelegate("MPL-1.1")
    val mpl2 by LicenseDelegate("MPL-2.0")
    val osl1 by LicenseDelegate("OSL-1.0")
    val osl11 by LicenseDelegate("OSL-1.1")
    val osl2 by LicenseDelegate("OSL-2.0")
    val osl21 by LicenseDelegate("OSL-2.1")
    val osl3 by LicenseDelegate("OSL-3.0")
    val x11 by LicenseDelegate("X11")
}

class LicenseDelegate(
    val name: String,
    val url: String = "https://spdx.org/licenses/$name.html",
) : ReadOnlyProperty<LicenseMetaHolder, LicenseMetadata> {
    override fun getValue(thisRef: LicenseMetaHolder, property: KProperty<*>): LicenseMetadata {
        val metadata = LicenseMetadata().apply {
            this.name = this@LicenseDelegate.name
            this.url = this@LicenseDelegate.url
        }

        thisRef.licenses += metadata
        return metadata
    }
}
