package im.ggd.scode.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();

        corsConfig.setAllowCredentials(false);
        corsConfig.addAllowedOrigin("*");
        corsConfig.setAllowedOriginPatterns(Arrays.asList());
        corsConfig.addAllowedHeader("x-requested-with");
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS"));
        corsConfig.setExposedHeaders(Arrays.asList());
        corsConfig.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource urlConfigSource = new UrlBasedCorsConfigurationSource();
        urlConfigSource.registerCorsConfiguration("/**", corsConfig);

        return new CorsFilter(urlConfigSource);
    }

}
