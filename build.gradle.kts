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

group = "fr.stardustenterprises"
version = "4.0.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(gradleApi())

    implementation(libs.bundles.kotlin)
    implementation(libs.stargrad)
}

sourceSets {
    val main by sourceSets
    val test by sourceSets

    val api by creating {
        java.srcDir("src/$name/kotlin")
        resources.srcDir("src/$name/resources")

        this.compileClasspath += main.compileClasspath
        this.runtimeClasspath += main.runtimeClasspath
    }

    arrayOf(main, test).forEach {
        it.compileClasspath += api.output
        it.runtimeClasspath += api.output
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
            id = "fr.stardustenterprises.burst.root"
            implementationClass = "fr.stardustenterprises.burst.gradle.plugin.RootPlugin"
        }
    }
}

pluginBundle {
    vcsUrl = "https://github.com/stardust-enterprises/burst"
    website = "https://github.com/stardust-enterprises/burst"
    tags = listOf()
}

fun plugin(id: String, version: String) = "$id:$id.gradle.plugin:$version"
