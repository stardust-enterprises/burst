package enterprises.stardust.burst.preset

import enterprises.stardust.burst.gradle.plugin.RootBurstExtension
import org.gradle.api.Project

fun interface Preset {
    fun apply(project: Project, burstExtension: RootBurstExtension)
}

inline fun <reified T : Preset> RootBurstExtension.preset() {
    T::class.java.getConstructor().newInstance()
        .apply(this.gradleProject, this)
}
