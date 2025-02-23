package eu.chrost.kotlinbooking

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class KotlinBookingService {
    enum class TripType {
        THERE,
        BACK
    }

    fun book(destination: String): String {
        return listOf(
            book(destination, TripType.THERE),
            book(destination, TripType.BACK)
        ).joinToString("\n")
    }

    private fun book(destination: String, tripType: TripType): String {
        logger.info { "[$destination $tripType] Booking start" }
        Thread.sleep(3000)
        logger.info { "[$destination $tripType] Booking end" }
        return "Booked $tripType travel to:  $destination"
    }
}