package eu.chrost.rxbooking;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class RxBookingController {
    private final RxBookingService bookingService;

    @PostMapping("/booking")
    public Mono<String> book(@RequestParam(value = "destination") String destination) {
        return bookingService.book(destination);

    }
}
