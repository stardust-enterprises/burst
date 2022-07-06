package fr.stardustenterprises.burst.gradle.root

import fr.stardustenterprises.stargrad.StargradPlugin

/**
 * Burst's entrypoint plugin.
 *
 * @author xtrm
 * @since 1.0.0
 */
class RootPlugin: StargradPlugin() {
    override val id: String = "fr.stardustenterprises.burst.root"
    lateinit var rootExtension: RootBurstExtension

    override fun applyPlugin() {
        println("RootPlugin.applyPlugin()")

        rootExtension = registerExtension(RootBurstExtension::class.java)

        printStuff()
    }

    override fun afterEvaluate() {
        println("RootPlugin.afterEvaluate()")
        printStuff()
    }

    private fun printStuff() {
        println("Name: ${rootExtension.name.getOrElse("DEFAULT")}")
        println("Test: ${rootExtension.test}")
    }
}
