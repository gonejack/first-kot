package main

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.selects.whileSelect
import java.sql.Timestamp
import java.time.Duration
import java.time.LocalDateTime
import kotlin.concurrent.thread

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
fun main(args: Array<String>) {
    Abc(100)
    Abc("name")

    val qq = Channel<Int>()

    thread(name = "线程") {
        Thread.sleep(10 * 1000)
        println("线程")
    }

    runBlocking {
        launch {
            send(qq)
        }
        launch {
            receive(qq)
        }
    }

    println("abc")
}

suspend fun send(ch: SendChannel<Int>) {
    val end = LocalDateTime.now().plusSeconds(3)

    while (end.isAfter(LocalDateTime.now())) {
        delay(1000)
        abc()
        ch.send(System.currentTimeMillis().div(1000).toInt())
    }

    ch.close()
}

fun abc() {
    runBlocking {
        launch {

        }
        launch {

        }
    }
}

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
suspend fun receive(ch: ReceiveChannel<Int>) {
    whileSelect {
        ch.onReceiveOrClosed {
            if (it.isClosed) {
                println("closed")
                false
            } else {
                println(it.value)
                true
            }
        }
    }
}

fun testAsync() = runBlocking {
    val result: Deferred<String> = async {
        println(this)
        println(this.javaClass.name)
        println(this@runBlocking)
        suspendPrint()
    }

    println("I will got the result ${result.await()}")
}

suspend fun suspendPrint(): String {
    delay(1000)

    return if (1 > 0) "abc" else "def"
}

class Abc {
    init {
        println("init")
    }
    constructor(name: String) {
        println("name$name")
    }
    constructor(num: Int) {
        println(num)
    }
}
