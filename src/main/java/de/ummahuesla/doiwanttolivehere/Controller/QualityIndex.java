package de.ummahuesla.doiwanttolivehere.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.ummahuesla.doiwanttolivehere.model.QiResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QualityIndex {

    @RequestMapping(value = "/", produces = "application/json")
    String index(@RequestParam String lon, @RequestParam String lat) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        QiResult result = QiResult.create(Double.parseDouble(lat), Double.parseDouble(lon));

        return objectMapper.writeValueAsString(result);
    }

    @RequestMapping("/test")
    String test() {
        return "Test";
    }

}
