package de.ummahuesla.doiwanttolivehere.repository;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import de.ummahuesla.doiwanttolivehere.model.Sunlight;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Repository
@EnableCaching
public class SunlightRepository {

    public Optional<Sunlight> fetch(Double lat, Double lng) {
        String endpoint = "http://wirtschaft-risby.bayern.de/RisGate/servlet/Sonnenscheindauer";

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> params = new Request().create(lat, lng);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(endpoint);
        builder.queryParams(params);
        String s = builder.toUriString();
        Sunlight result = restTemplate.getForObject(builder.toUriString(), Sunlight.class);

        return Optional.of(result);
    }

    private class Request {
        public MultiValueMap<String, String> create(Double lat, Double lng) {

            double meters = 50;

            // number of km per degree = ~111km (111.32 in google maps, but range varies between 110.567km at the equator and 111.699km at the poles)
            // 1km in degree = 1 / 111.32km = 0.0089
            // 1m in degree = 0.0089 / 1000 = 0.0000089
            double coef = meters * 0.0000089;

            double new_latitude = lat + coef;

            // pi / 180 = 0.018
            double new_longitude = lng + coef / Math.cos(lat * 0.018);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.put("version", Collections.singletonList("1.1.1"));
            map.put("request", Collections.singletonList("GetFeatureInfo"));
            map.put("layers", Collections.singletonList("sd_jahr"));
            map.put("styles", Collections.singletonList("default"));
            map.put("srs", Collections.singletonList("EPSG:4326"));
            map.put("bbox", Collections.singletonList(lng + "," + lat + "," + new_longitude + "," + new_latitude));
            map.put("width", Collections.singletonList("1044"));
            map.put("height", Collections.singletonList("906"));
            map.put("format", Collections.singletonList("text/html"));
            map.put("x", Collections.singletonList("500"));
            map.put("y", Collections.singletonList("400"));
            map.put("query_layers", Collections.singletonList("sd_jahr"));
            return map;
        }
    }

}
