package fr.stardustenterprises.burst.presets

import fr.stardustenterprises.burst.gradle.plugin.RootBurstExtension
import fr.stardustenterprises.burst.gradle.plugin.RootPlugin

apply<RootPlugin>()

configure<RootBurstExtension> {
    project {
        group = "fr.stardustenterprises"
        vendor = "Stardust Enterprises"
    }
}
