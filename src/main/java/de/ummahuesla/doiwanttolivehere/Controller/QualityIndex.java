package de.ummahuesla.doiwanttolivehere.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.ummahuesla.doiwanttolivehere.model.QiResult;
import de.ummahuesla.doiwanttolivehere.service.QualityIndexService;

@RestController
public class QualityIndex {

	@Autowired
	QualityIndexService qiService;
    

    @RequestMapping(value = "/", produces = "application/json")
    String index(@RequestParam String lon, @RequestParam String lat) throws JsonProcessingException {
    	ObjectMapper objectMapper = new ObjectMapper();
    	QiResult result = qiService.fetch(Double.parseDouble(lat), Double.parseDouble(lon));
		return objectMapper.writeValueAsString(result);
    }

    @RequestMapping("/test")
    String test() {
        return "Test";
    }

}
