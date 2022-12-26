package enterprises.stardust.burst.property

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun interface ClassContextAccessor {
    fun getCallerClass(): Class<*>
}

@Suppress("DEPRECATION", "removal", "KotlinConstantConditions")
private val classContextAccessor: ClassContextAccessor = run {
    // Check if SecurityManager exists (future proofing)
    val securityManager = try {
        SecurityManager::class.java
        true
    } catch (_: Throwable) {
        false
    }

    if (securityManager) {
        object : SecurityManager(), ClassContextAccessor {
            override fun getCallerClass(): Class<*> =
                classContext[3]
        }
    } else {
        ClassContextAccessor {
            Thread.currentThread().stackTrace[4].let { stackTraceElement ->
                Class.forName(stackTraceElement.className)
            }
        }
    }
}

interface DataProperty<T> : ReadOnlyProperty<Any?, T?> {
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?)
}

fun <T> data(
    required: Boolean = false,
    fallback: (() -> T)? = null,
): DataProperty<T> {
    val caller = classContextAccessor.getCallerClass()

    return object : DataProperty<T> {
        private var value: T? = null

        override fun getValue(thisRef: Any?, property: KProperty<*>): T? =
            if (required) {
                value
                    ?: fallback?.invoke()
                    ?: throw MissingPropertyException(caller, property)
            } else value ?: fallback?.invoke()

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
            println("Setting ${caller.simpleName}.${property.name} to $value")
            this.value = value
                ?: throw IllegalArgumentException(
                    "Cannot set ${caller.simpleName}.${property.name} to null!"
                )
        }
    }
}
