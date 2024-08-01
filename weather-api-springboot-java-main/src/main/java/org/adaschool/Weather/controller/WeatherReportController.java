import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.weather.WeatherReportController;
import com.example.weather.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilder;

@WebMvcTest(WeatherReportController.class)
public class WeatherReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private WeatherReportService weatherReportService;

    @InjectMocks
    private WeatherReportController weatherReportController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(weatherReportController).build();
    }

    @Test
    void testGetWeather() throws Exception {
        // Arrange
        String location = "London";
        String weatherInfo = "Sunny";
        when(weatherReportService.getWeather(location)).thenReturn(weatherInfo);

        // Act & Assert
        mockMvc.perform(get("/weather/{location}", location))
                .andExpect(status().isOk())
                .andExpect(content().string(weatherInfo));
    }
}
