package fr.stardustenterprises.burst

/**
 * @author xtrm
 * @since 4.0.0
 */
fun <T : Any> invokeScaffold(
    consumer: (T.() -> Unit) -> Unit
): Function1<T.() -> Unit, Unit> = object : Function1<T.() -> Unit, Unit> {
    override operator fun invoke(block: T.() -> Unit) {
        consumer(block)
    }
}

