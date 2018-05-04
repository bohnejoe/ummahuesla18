package de.ummahuesla.doiwanttolivehere.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.ummahuesla.doiwanttolivehere.model.QiResult;
import de.ummahuesla.doiwanttolivehere.model.Sunlight;
import de.ummahuesla.doiwanttolivehere.repository.SunlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class QualityIndex {

    @Autowired
    private SunlightRepository sunlightRepository;

    @RequestMapping(value = "/", produces = "application/json")
    String index(@RequestParam String lon, @RequestParam String lat) throws JsonProcessingException {

        Optional<Sunlight> fetch = sunlightRepository.fetch();

        ObjectMapper objectMapper = new ObjectMapper();
        QiResult result = QiResult.create(Double.parseDouble(lat), Double.parseDouble(lon));

        return objectMapper.writeValueAsString(result);
    }

    @RequestMapping("/test")
    String test() {
        return "Test";
    }

}
