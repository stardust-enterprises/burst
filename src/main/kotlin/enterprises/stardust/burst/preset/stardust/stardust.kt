package enterprises.stardust.burst.preset.stardust

import enterprises.stardust.burst.gradle.plugin.RootBurstExtension
import enterprises.stardust.burst.meta.impl.pom.DeveloperMetaHolder
import enterprises.stardust.burst.meta.impl.pom.DeveloperMetadata
import enterprises.stardust.burst.preset.Preset
import org.gradle.api.Project

class StardustPreset : Preset {
    override fun apply(project: Project, burstExtension: RootBurstExtension) {
        burstExtension.apply {
            project {
                version = "0.0.1-SNAPSHOT"
                group = "enterprises.stardust"
                vendor = "Stardust Enterprises"

                scm {
                    serviceHost = "github.com"
                }
            }
        }
    }
}

val DeveloperMetaHolder.xtrm: DeveloperMetadata
    get() = this.developer {
        id = "xtrm"
        email = "oss@xtrm.me"
    }

val DeveloperMetaHolder.lambdagg: DeveloperMetadata
    get() = this.developer {
        id = "lambdagg"
        email = "lambdagg@tuta.io"
    }

val DeveloperMetaHolder.shyrogan: DeveloperMetadata
    get() = this.developer {
        id = "Shyrogan"
        email = "sebastien.vial@etu.umontpellier.fr"
    }
