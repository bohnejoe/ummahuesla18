package de.ummahuesla.doiwanttolivehere.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import de.ummahuesla.doiwanttolivehere.model.Sunlight;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.Scanner;

@Repository
@EnableCaching
public class SunlightRepository {

    public Optional<Sunlight> fetch() {
        //String s = "http://wirtschaft-risby.bayern.de/RisGate/servlet/Sonnenscheindauer?version=1.1.1&request=GetFeatureInfo&layers=sd_jahr&styles=default&SRS=EPSG:4326&bbox=11.57549,48.1374283,11.57559,48.1374293&width=1044&height=906&format=text/html&X=500&Y=400&query_layers=sd_jahr";
        String s = "http://localhost:8000/Sonnenscheindauer.xml";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Sunlight> result = restTemplate.execute(
                s,
                HttpMethod.GET,
                clientHttpRequest -> {
                },
                clientHttpResponse -> {
                    Scanner s12 = new Scanner(clientHttpResponse.getBody()).useDelimiter("\\A");
                    String s1 = s12.hasNext() ? s12.next() : "";

                    XmlMapper xmlMapper = new XmlMapper();
                    Sunlight readValue = xmlMapper.readValue(s1, Sunlight.class);

                    return new ResponseEntity<Sunlight>(readValue, HttpStatus.OK);
                });

        return Optional.of(result.getBody());
    }

}
