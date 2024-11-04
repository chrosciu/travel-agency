package eu.chrost.booking;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

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
    private final ExecutorService executorService = Executors.newCachedThreadPool(threadFactory);

    @SneakyThrows
    public String book(String destination) {
        Future<String> there = executorService.submit(() -> book(destination, TripType.THERE));
        Future<String> back = executorService.submit(() -> book(destination, TripType.BACK));
        return String.join("\n",
            there.get(),
            back.get()
        );
    }

    @SneakyThrows
    private String book(String destination, TripType tripType) {
        log.info("[{} {}] Booking start", destination, tripType);
        Thread.sleep(3000);
        log.info("[{} {}] Booking end", destination, tripType);
        return String.format("Booked %s travel to:  %s", tripType, destination);
    }
}
