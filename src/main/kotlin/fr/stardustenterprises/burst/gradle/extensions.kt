package fr.stardustenterprises.burst.gradle

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

inline fun <reified T: Plugin<Project>> Project.apply() =
    this.pluginManager.apply(T::class.java)

fun <T> expect(value: T?, missing: String): T =
    value ?: throw GradleException(
        "You must specify the project's '$missing'!"
    )
