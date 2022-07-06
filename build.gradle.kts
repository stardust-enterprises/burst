@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.gradle.plugin.publish)
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(gradleApi())

    implementation(libs.kotlin.stdlib)
    implementation(libs.stargrad)
}

fun plugin(id: String, version: String) = "$id:$id.gradle.plugin:$version"
