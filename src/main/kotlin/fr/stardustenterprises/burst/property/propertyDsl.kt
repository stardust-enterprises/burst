package fr.stardustenterprises.burst.property

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

interface DataProperty<T> : ReadOnlyProperty<Any?, T?> {
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?)
}

fun <T> data(
    required: Boolean = false,
    fallback: (() -> T)? = null,
): DataProperty<T> {
    val caller = Class.forName(
        Thread.currentThread().stackTrace[3].className
    )

    return object : DataProperty<T> {
        private var value: T? = null

        override fun getValue(thisRef: Any?, property: KProperty<*>): T? =
            if (required) {
                value
                    ?: fallback?.invoke()
                    ?: throw MissingPropertyException(caller, property)
            } else value ?: fallback?.invoke()

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
            this.value = value
                ?: throw IllegalArgumentException(
                    "Cannot set ${caller.simpleName}.${property.name} to null!"
                )
        }
    }
}
