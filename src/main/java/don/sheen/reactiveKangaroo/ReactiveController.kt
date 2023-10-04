package don.sheen.reactiveKangaroo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api")
class ReactiveController(private val weatherService: WeatherService) {

    @GetMapping("/data")
    fun getSingleData(): Mono<Daily> {
        // Create and return a Mono with a single value
        return weatherService.getDailyWeather()// Limit the stream to emit 10 items
    }
}