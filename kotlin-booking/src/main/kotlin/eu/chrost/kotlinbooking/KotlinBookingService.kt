package eu.chrost.kotlinbooking

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

import org.springframework.stereotype.Service
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

private val logger = KotlinLogging.logger {}

object VirtualThreadDispatcher : CoroutineDispatcher() {
    private val executor = Executors.newVirtualThreadPerTaskExecutor()

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        executor.execute(block)
    }
}

@Service
class KotlinBookingService {
    enum class TripType {
        THERE,
        BACK
    }

    suspend fun book(destination: String): String = coroutineScope {
        listOf(
            async(VirtualThreadDispatcher) { book(destination, TripType.THERE) },
            async(VirtualThreadDispatcher) { book(destination, TripType.BACK) }
        ).awaitAll().joinToString("\n")
    }

    private suspend fun book(destination: String, tripType: TripType): String {
        logger.info { "[$destination $tripType] Booking start" }
        Thread.sleep(3000)
        logger.info { "[$destination $tripType] Booking end" }
        return "Booked $tripType travel to:  $destination"
    }
}