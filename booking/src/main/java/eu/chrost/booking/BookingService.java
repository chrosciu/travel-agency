package eu.chrost.booking;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.StructuredTaskScope;
import java.util.function.Supplier;

@Service
@Slf4j
public class BookingService {
    enum TripType {
        THERE,
        BACK
    }

    /*
     Default ThreadFactory used by StructuredTaskScope does not set names for created threads
     which makes threads logging unusable
    */
    private final ThreadFactory threadFactory = Thread.ofVirtual().name("booking-", 0).factory();

    @SneakyThrows
    public String book(String destination) {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure("Booking", threadFactory)) {
            Supplier<String> there = scope.fork(() -> book(destination, TripType.THERE));
            Supplier<String> back = scope.fork(() -> book(destination, TripType.BACK));
            scope.join().throwIfFailed();
            return String.join("\n",there.get(), back.get());
        }
    }

    @SneakyThrows
    private String book(String destination, TripType tripType) {
        log.info("[{} {}] Booking start", destination, tripType);
        Thread.sleep(3000);
        log.info("[{} {}] Booking end", destination, tripType);
        return String.format("Booked %s travel to:  %s", tripType, destination);
    }
}
