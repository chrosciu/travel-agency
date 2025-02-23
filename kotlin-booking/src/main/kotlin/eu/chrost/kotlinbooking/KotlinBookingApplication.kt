package eu.chrost.kotlinbooking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinBookingApplication

fun main(args: Array<String>) {
    runApplication<KotlinBookingApplication>(*args)
}
