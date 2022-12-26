package enterprises.stardust.burst

import org.gradle.api.Project

private val registryMap: MutableMap<String, Any> by lazy {
    mutableMapOf()
}

val Project.burstRegistry: MutableMap<String, Any>
    get() = registryMap
