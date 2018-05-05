package de.ummahuesla.doiwanttolivehere.repository;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import de.ummahuesla.doiwanttolivehere.model.Sunlight;
import de.ummahuesla.doiwanttolivehere.util.BoundingBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.UnknownHostException;
import java.util.*;

@Repository
@EnableCaching
public class SunlightRepository {

    @Autowired
    private BoundingBox boundingBox;

    public Optional<Sunlight> fetch(Double lat, Double lng) {
        String endpoint = "http://wirtschaft-risby.bayern.de/RisGate/servlet/Sonnenscheindauer";

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> params = new Request().create(lat, lng);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(endpoint);
        builder.queryParams(params);

        try {
            Sunlight result = restTemplate.getForObject(builder.toUriString(), Sunlight.class);
            return Optional.of(result);
        } catch (RestClientException e) {
            return Optional.empty();
        }
    }

    private class Request {
        public MultiValueMap<String, String> create(Double lat, Double lng) {

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.put("version", Collections.singletonList("1.1.1"));
            map.put("request", Collections.singletonList("GetFeatureInfo"));
            map.put("layers", Collections.singletonList("sd_jahr"));
            map.put("styles", Collections.singletonList("default"));
            map.put("srs", Collections.singletonList("EPSG:4326"));
            map.put("bbox", Collections.singletonList(boundingBox.create(lng, lat)));
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
