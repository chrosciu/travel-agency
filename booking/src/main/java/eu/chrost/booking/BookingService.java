package eu.chrost.booking;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookingService {
    enum TripType {
        THERE,
        BACK
    }

    public String book(String destination) {
        return String.join("\n",
            book(destination, TripType.THERE),
            book(destination, TripType.BACK)
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
