package enterprises.stardust.burst.meta

import enterprises.stardust.burst.property.DataProperty
import java.lang.reflect.Modifier
import kotlin.reflect.KProperty
import kotlin.reflect.full.declaredMemberProperties

/**
 * A reflective [Any.toString] implementation.
 *
 * @author xtrm
 * @since 4.0.0
 */
open class Stringable {
    override fun toString(): String =
        buildString {
            val kotlinClass = this@Stringable::class
            val javaClass = kotlinClass.java

            append("(")

            val directProperties = mutableListOf<KProperty<*>>()
            val delegatedDataProperties = mutableListOf<DataProperty<*>>()

            directProperties.addAll(kotlinClass.declaredMemberProperties)

            delegatedDataProperties += javaClass.declaredFields.filter {
                it.name != "\$\$delegatedProperties"
                    && it.name.endsWith("\$delegate")
            }.filterNot { field ->
                directProperties.firstOrNull { property ->
                    field.name == property.name
                } != null
            }.map {
                it.also {
                    it.isAccessible = true
                }.get(
                    if (it.modifiers and Modifier.STATIC == Modifier.STATIC)
                        null
                    else
                        this@Stringable
                ) as DataProperty<*>
            }

            append("direct=$directProperties, ")
            append("deleg=$delegatedDataProperties")

            append(")")
        }
}
