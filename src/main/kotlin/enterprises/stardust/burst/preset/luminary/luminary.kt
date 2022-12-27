package enterprises.stardust.burst.preset.luminary

import enterprises.stardust.burst.gradle.plugin.RootBurstExtension
import enterprises.stardust.burst.meta.impl.pom.DeveloperMetaHolder
import enterprises.stardust.burst.meta.impl.pom.DeveloperMetadata
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

// xtrm
val DeveloperMetaHolder.xtrm: DeveloperMetadata
    get() = this.developer {
        id = "xtrm"
        email = "oss@xtrm.me"
    }

// wyvest
val DeveloperMetaHolder.wyvest: DeveloperMetadata
    get() = this.developer {
        id = "wyvest"
    }

// caledonian
val DeveloperMetaHolder.caledonian: DeveloperMetadata
    get() = this.developer {
        id = "caledonian"
    }

// pauliesnug
val DeveloperMetaHolder.pauliesnug: DeveloperMetadata
    get() = this.developer {
        id = "pauliesnug"
    }

// nextdaydelivery
val DeveloperMetaHolder.nextdaydelivery: DeveloperMetadata
    get() = this.developer {
        id = "nextdaydelivery"
    }
