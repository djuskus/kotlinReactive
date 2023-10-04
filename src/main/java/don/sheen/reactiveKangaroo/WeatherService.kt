package don.sheen.reactiveKangaroo

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class WeatherService {

    val webClient = WebClient.create("https://api.weather.gov/gridpoints/MLB/33,70/")


    fun getDailyWeather() : Mono<Daily> {
        return webClient
            .get()
            .uri("forecast")
            .retrieve()
            .bodyToMono(/* elementClass = */ HashMap::class.java) // Map to represent the API response
            .map { responseMap ->
                // Explore the structure of the response here
                println("API Response Structure: $responseMap")

                // You can access specific fields within the response
                val propertiesMap: LinkedHashMap<String, Any> =
                    responseMap.get("properties") as LinkedHashMap<String, Any>
                val periodsList: ArrayList<LinkedHashMap<String, Any>> =
                    propertiesMap.get("periods") as ArrayList<LinkedHashMap<String, Any>>
                val DayPredictionCurrentDay: LinkedHashMap<String, Any> = periodsList[1] as LinkedHashMap<String, Any>

                val shortName: String? = DayPredictionCurrentDay.get("name") as String?
                val temperature: Int? = DayPredictionCurrentDay.get("temperature") as Int?
                val temperatureUnit: String? = DayPredictionCurrentDay.get("temperatureUnit") as String?
                val tempCelsius: Int? = if(temperatureUnit == "F") (((temperature?.minus(32))?.times(5) ?: -10000) /9) else temperature
                val shortForcast: String? = DayPredictionCurrentDay.get("shortForecast") as String?
                val report = WeatherEntry(shortName, tempCelsius, shortForcast)
                Daily(report)
            }
            .onErrorResume { error ->
                // Handle errors and return a fallback response if needed
                println("An error occurred: ${error.message}")
                Mono.just(Daily(WeatherEntry("Fallback Data",3,"333")))
            }
    }

    fun cheese() : Mono<Daily> {

        return Mono.justOrEmpty(Daily(WeatherEntry("3",33,"3")))
    }
}