package de.ummahuesla.doiwanttolivehere.repository;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import de.ummahuesla.doiwanttolivehere.model.Sunlight;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

@Repository
@EnableCaching
public class SunlightRepository {

    public Optional<Sunlight> fetch(Double lng, Double lat) {
        //String s = "http://wirtschaft-risby.bayern.de/RisGate/servlet/Sonnenscheindauer?version=1.1.1&request=GetFeatureInfo&layers=sd_jahr&styles=default&SRS=EPSG:4326&bbox=11.57549,48.1374283,11.57559,48.1374293&width=1044&height=906&format=text/html&X=500&Y=400&query_layers=sd_jahr";

        String endpoint = "http://wirtschaft-risby.bayern.de/RisGate/servlet/Sonnenscheindauer";
//        endpoint = "http://localhost:8000/Sonnenscheindauer.xml";

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new Request().create(lng, lat);
        ResponseEntity<Sunlight> result = restTemplate.execute(
                endpoint,
                HttpMethod.GET,
                clientHttpRequest -> {
                },
                clientHttpResponse -> {
                    Scanner s12 = new Scanner(clientHttpResponse.getBody()).useDelimiter("\\A");
                    String s1 = s12.hasNext() ? s12.next() : "";

                    XmlMapper xmlMapper = new XmlMapper();
                    Sunlight readValue = xmlMapper.readValue(s1, Sunlight.class);

                    return new ResponseEntity<Sunlight>(readValue, HttpStatus.OK);
                }, params);

        return Optional.of(result.getBody());
    }

    private class Request {
        public Map<String, String> create(Double lng, Double lat) {
            HashMap<String, String> map = new HashMap<>();
            map.put("version", "1.1.1");
            map.put("request", "GetFeatureInfo");
            map.put("layers", "sd_jahr");
            map.put("styles", "default");
            map.put("srs", "EPSG:4326");
            map.put("bbox", lng + "," + lat + "," + lng + "," + lat);
            //map.put("bbox", "11.57549,48.1374283,11.57559,48.1374293");
            map.put("width", "1044");
            map.put("height", "906");
            map.put("format", "text/html");
            map.put("x", "500");
            map.put("y", "400");
            map.put("query_layers", "sd_jahr");
            return map;
        }
    }

}
