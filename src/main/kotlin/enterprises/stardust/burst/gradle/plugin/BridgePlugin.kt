package enterprises.stardust.burst.gradle.plugin

import enterprises.stardust.stargrad.StargradPlugin

class BridgePlugin : StargradPlugin() {
    override val id: String = "enterprises.stardust.burst.bridge"

    override fun applyPlugin() {
        with (project) {
            println("Applying bridge plugin as $name")
        }
    }
}
