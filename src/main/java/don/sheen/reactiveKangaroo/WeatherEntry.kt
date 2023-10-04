package don.sheen.reactiveKangaroo

import lombok.Data

@Data
data class WeatherEntry(val day_name: String?, val temp_high_celsius: Int?, val forcast_blurp: String?)
