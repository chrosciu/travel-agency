package eu.chrost.kotlinbooking

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class KotlinBookingController(private val bookingService: KotlinBookingService) {
    @PostMapping("/booking")
    fun book(@RequestParam(value = "destination") destination: String): String {
        return bookingService.book(destination)
    }
}