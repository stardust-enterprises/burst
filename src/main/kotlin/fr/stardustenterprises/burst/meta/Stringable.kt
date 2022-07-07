package fr.stardustenterprises.burst.meta

import kotlin.reflect.full.declaredMemberProperties

/**
 * A reflective [Any.toString] implementation.
 *
 * @author xtrm
 * @since 4.0.0
 */
open class Stringable {
    override fun toString(): String =
        "${javaClass.simpleName}(${
            javaClass.kotlin.declaredMemberProperties.joinToString(", ") {
                "${it.name}=${
                    it.get(
                        this
                    )
                }"
            }
        })"
}
