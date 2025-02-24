package eu.chrost.kotlinbooking

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class KotlinBookingService {
    enum class TripType {
        THERE,
        BACK
    }

    suspend fun book(destination: String): String = coroutineScope {
        listOf(
            async { book(destination, TripType.THERE) },
            async { book(destination, TripType.BACK) }
        ).awaitAll().joinToString("\n")
    }

    private suspend fun book(destination: String, tripType: TripType): String {
        logger.info { "[$destination $tripType] Booking start" }
        delay(3000)
        logger.info { "[$destination $tripType] Booking end" }
        return "Booked $tripType travel to:  $destination"
    }
}