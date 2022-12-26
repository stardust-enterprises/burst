package enterprises.stardust.burst.property

import kotlin.reflect.KProperty
import kotlin.reflect.javaType

/**
 *
 * @author xtrm
 * @since 4.0.0
 */
@OptIn(ExperimentalStdlibApi::class)
class MissingPropertyException(
    owner: Class<*>,
    prop: KProperty<*>,
) : RuntimeException(
    "Missing ${prop.returnType.javaType.typeName} value of " +
        "${owner.simpleName}.${prop.name}"
)
