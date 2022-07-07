@file:Suppress("ObjectPropertyName")

package fr.stardustenterprises.burst.meta.impl.pom

import org.gradle.kotlin.dsl.provideDelegate
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

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

// Curated list of most-known-used-popular(?) licenses.
// From https://spdx.org/licenses/

val LicenseMetaHolder.bsd0 by LicenseDelegate("0BSD")
val LicenseMetaHolder.agpl3 by LicenseDelegate("AGPL-3.0-only")
val LicenseMetaHolder.`agpl3+` by LicenseDelegate("AGPL-3.0-or-later")
val LicenseMetaHolder.apache1 by LicenseDelegate("Apache-1.0")
val LicenseMetaHolder.apache11 by LicenseDelegate("Apache-1.1")
val LicenseMetaHolder.apache2 by LicenseDelegate("Apache-2.0")

//TODO: add BSD

//TODO: add (full) Creative Commons?
val LicenseMetaHolder.ccbyncsa4 by LicenseDelegate("CC-BY-NC-SA-4.0")

val LicenseMetaHolder.gpl1 by LicenseDelegate("GPL-1.0-only")
val LicenseMetaHolder.`gpl1+` by LicenseDelegate("GPL-1.0-or-later")
val LicenseMetaHolder.gpl2 by LicenseDelegate("GPL-2.0-only")
val LicenseMetaHolder.`gpl2+` by LicenseDelegate("GPL-2.0-or-later")
val LicenseMetaHolder.gpl3 by LicenseDelegate("GPL-3.0-only")
val LicenseMetaHolder.`gpl3+` by LicenseDelegate("GPL-3.0-or-later")
val LicenseMetaHolder.isc by LicenseDelegate("ISC")
val LicenseMetaHolder.lgpl2 by LicenseDelegate("LGPL-2.0-only")
val LicenseMetaHolder.`lgpl2+` by LicenseDelegate("LGPL-2.0-or-later")
val LicenseMetaHolder.lgpl21 by LicenseDelegate("LGPL-2.1-only")
val LicenseMetaHolder.`lgpl21+` by LicenseDelegate("LGPL-2.1-or-later")
val LicenseMetaHolder.lgpl3 by LicenseDelegate("LGPL-3.0-only")
val LicenseMetaHolder.`lgpl3+` by LicenseDelegate("LGPL-3.0-or-later")
val LicenseMetaHolder.mit by LicenseDelegate("MIT")
val LicenseMetaHolder.mit0 by LicenseDelegate("MIT-0")
val LicenseMetaHolder.mpl1 by LicenseDelegate("MPL-1.0")
val LicenseMetaHolder.mpl11 by LicenseDelegate("MPL-1.1")
val LicenseMetaHolder.mpl2 by LicenseDelegate("MPL-2.0")
val LicenseMetaHolder.osl1 by LicenseDelegate("OSL-1.0")
val LicenseMetaHolder.osl11 by LicenseDelegate("OSL-1.1")
val LicenseMetaHolder.osl2 by LicenseDelegate("OSL-2.0")
val LicenseMetaHolder.osl21 by LicenseDelegate("OSL-2.1")
val LicenseMetaHolder.osl3 by LicenseDelegate("OSL-3.0")
val LicenseMetaHolder.x11 by LicenseDelegate("X11")
