package enterprises.stardust.burst.preset.luminary

import enterprises.stardust.burst.gradle.plugin.RootBurstExtension
import enterprises.stardust.burst.preset.Preset
import org.gradle.api.Project

class LuminaryPreset : Preset {
    override fun apply(project: Project, burstExtension: RootBurstExtension) {
        burstExtension.apply {
            project {
                version = "0.0.1-SNAPSHOT"
                group = "net.luminarymc"
                vendor = "Luminary"

                scm {
                    serviceHost = "github.com"
                }
            }
        }
    }
}
