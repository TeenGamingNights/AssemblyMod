package net.teengamingnights.assemblymod.utils

import kotlinx.coroutines.*
import java.lang.Runnable
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

fun launch(block: suspend CoroutineScope.() -> Unit) = GlobalScope.launch(block = block)

fun<T> async(block: suspend CoroutineScope.() -> T) = GlobalScope.async(block = block)

suspend fun CoroutineScope.delay(amount: Int, unit: TimeUnit = TimeUnit.MILLISECONDS) = kotlinx.coroutines.delay(unit.toMillis(amount.toLong()))

suspend fun CoroutineScope.delay(amount: Long, unit: TimeUnit = TimeUnit.MILLISECONDS) = kotlinx.coroutines.delay(unit.toMillis(amount))

@ExperimentalCoroutinesApi
fun<T> Deferred<T>.onComplete(block: (T) -> Unit) = invokeOnCompletion { block(getCompleted()) }

fun<T> Consumer<T>.toCallback() : Callback<T> = { this.accept(it) }

fun<V> Callback<V>.toConsumer() : Consumer<V> = Consumer(this)
