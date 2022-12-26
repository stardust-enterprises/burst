@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    `java-gradle-plugin`
    `maven-publish`
    alias(libs.plugins.kotlin.jvm)
    `kotlin-dsl`
    alias(libs.plugins.gradle.plugin.publish)
    alias(libs.plugins.versions)
    alias(libs.plugins.version.catalog.update)
}

group = "enterprises.stardust"
version = "4.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(gradleApi())

    implementation(libs.bundles.kotlin)
    implementation(libs.stargrad)
}

configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.group == "org.jetbrains.kotlin") {
            useVersion(libs.kotlin.jvm.get().versionConstraint.displayName)
        }
    }
}

tasks {
    val jvmTarget = "1.8"

    // Configure JVM versions
    compileKotlin {
        kotlinOptions.jvmTarget = jvmTarget
        kotlinOptions.languageVersion = "1.7"
        kotlinOptions.freeCompilerArgs += listOf(
            "-opt-in=kotlin.RequiresOptIn"
        )
    }
    compileJava {
        targetCompatibility = jvmTarget
        sourceCompatibility = jvmTarget
    }
}

gradlePlugin {
    plugins {
        create("burstRoot") {
            displayName = "Burst - root"
            description = "Missing."
            id = "enterprises.stardust.burst.root"
            implementationClass = "enterprises.stardust.burst.gradle.plugin.RootPlugin"
        }
    }
}

pluginBundle {
    vcsUrl = "https://github.com/stardust-enterprises/burst"
    website = "https://github.com/stardust-enterprises/burst"
    tags = listOf()
}
