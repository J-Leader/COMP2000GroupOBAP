package rvb.weather;

public interface WeatherListener {
    void onWeather(WeatherRecord rec);
    default void onWeatherError(Exception e) {}
    default void onWeatherClosed() {}
}

