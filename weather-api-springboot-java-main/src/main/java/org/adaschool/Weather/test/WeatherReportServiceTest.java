import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.weather.WeatherReportService;
import com.example.weather.WeatherClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class WeatherReportServiceTest {

    @InjectMocks
    private WeatherReportService weatherReportService;

    @Mock
    private WeatherClient weatherClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWeather() {
        // Arrange
        String location = "London";
        String expectedWeather = "Sunny";
        when(weatherClient.fetchWeather(location)).thenReturn(expectedWeather);

        // Act
        String actualWeather = weatherReportService.getWeather(location);

        // Assert
        assertEquals(expectedWeather, actualWeather);
    }

    @Test
    void testGetWeatherWithException() {
        // Arrange
        String location = "UnknownLocation";
        when(weatherClient.fetchWeather(location)).thenThrow(new RuntimeException("Location not found"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> weatherReportService.getWeather(location));
    }
}
