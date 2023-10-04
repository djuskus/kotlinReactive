package don.sheen.reactiveKangaroo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;

public class WebJavaWeatherClient {
    private WebClient webclient;

    public WebJavaWeatherClient(WebClient webclient) {
        this.webclient = webclient;
    }

    public Mono<HashMap> pricesFor(String symbol) {
        return webclient.get().uri("forecast").retrieve().bodyToMono(String.class).map(TeeMap -> {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode root = objectMapper.readTree(TeeMap);
                return null;
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } finally {
                return null;
            }
        });
    }
}
