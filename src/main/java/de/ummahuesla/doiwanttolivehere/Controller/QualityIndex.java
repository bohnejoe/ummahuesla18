package de.ummahuesla.doiwanttolivehere.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QualityIndex {

    @RequestMapping("/")
    String index(@RequestParam String lon, @RequestParam String lat){
        return lon + lat;
    }

    @RequestMapping("/test")
    String test() {
        return "Test";
    }

}
