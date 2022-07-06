package fr.stardustenterprises.burst.gradle.root

import fr.stardustenterprises.stargrad.ext.Extension
import fr.stardustenterprises.stargrad.ext.StargradExtension
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input

@Extension("burst")
open class RootBurstExtension(project: Project) : StargradExtension(project) {
    @Input
    val name: Property<String> = objects.property(String::class.java)

    @Input
    var test: String =
        "test"
}
