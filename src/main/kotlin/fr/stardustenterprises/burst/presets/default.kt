package fr.stardustenterprises.burst.presets

import fr.stardustenterprises.burst.gradle.plugin.RootPlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

/**
 * Initialization function of all presets
 */
fun Project.initPreset() {
    apply<RootPlugin>()
}
