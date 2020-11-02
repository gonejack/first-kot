package main

import java.io.File
import java.time.*
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit
import java.util.*
import kotlin.math.truncate

fun main() {
    val dir = File("./")

    val list = when {
        dir.canRead() -> {
            File(dir.canonicalPath).walkTopDown().filter {
//                f -> f.isFile && f.name.endsWith(".xml")

                f -> f.isAbsolute
            }.toList()
        }
        else -> emptyList()
    }

    println(System.currentTimeMillis().div(1e3).toInt())
    println(OffsetDateTime.now().withOffsetSameInstant(ZoneOffset.of("+04:00")))
    println(ZonedDateTime.now().withZoneSameInstant(ZoneId.of("+8")))

    val now = ZonedDateTime.now()
    val tomorrow = now.plusDays(1).truncatedTo(ChronoUnit.DAYS)
    println(now.until(tomorrow, ChronoUnit.SECONDS))

    println(May("qq").qq)
    Thread.sleep(Duration.ofSeconds(1).toMillis())
    println(May("qq").qq)

    list.forEach(::println)
}

fun test(): List<String> = listOf("abc", "def")
