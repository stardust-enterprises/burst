package enterprises.stardust.burst.meta

/**
 * An abstract representation of applicable metadata.
 *
 * @param T the mutable target type.
 *
 * @author xtrm
 * @since 4.0.0
 */
abstract class Data<T> : Stringable() {
    /**
     * Applies the current metadata to the provided target.
     *
     * @param target the target instance.
     */
    abstract fun mutate(target: T)
}

fun <T: Data<U>, U> T.mutateAndGet(target: U): T = run {
    mutate(target)
    this
}
